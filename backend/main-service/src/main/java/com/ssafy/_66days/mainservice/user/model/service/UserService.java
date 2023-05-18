package com.ssafy._66days.mainservice.user.model.service;

import java.time.LocalDate;
import java.util.*;

import com.ssafy._66days.mainservice.challenge.model.entity.MyChallenge;
import com.ssafy._66days.mainservice.challenge.model.entity.mongodb.PersonalChallengeLog;
import com.ssafy._66days.mainservice.challenge.model.reposiotry.*;
import com.ssafy._66days.mainservice.page.model.dto.MainPageMyGroupResponseDTO;
import com.ssafy._66days.mainservice.page.model.dto.MainPageTodoResponseDTO;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ssafy._66days.mainservice.animal.model.dto.AnimalMainPageResponseDTO;
import com.ssafy._66days.mainservice.animal.model.entity.Animal;
import com.ssafy._66days.mainservice.animal.model.repository.AnimalRepository;
import com.ssafy._66days.mainservice.badge.model.entity.Badge;
import com.ssafy._66days.mainservice.challenge.model.entity.Challenge;
import com.ssafy._66days.mainservice.challenge.model.entity.GroupChallenge;
import com.ssafy._66days.mainservice.page.model.dto.MainPageGroupResponseDTO;
import com.ssafy._66days.mainservice.group.model.entity.Group;
import com.ssafy._66days.mainservice.group.model.entity.GroupMember;
import com.ssafy._66days.mainservice.group.model.repository.GroupMemberRepository;
import com.ssafy._66days.mainservice.tier.model.dto.TierMainPageResponseDTO;
import com.ssafy._66days.mainservice.tier.model.entity.Tier;
import com.ssafy._66days.mainservice.tier.model.repository.TierRepository;
import com.ssafy._66days.mainservice.user.model.dto.UserDetailResponseDTO;
import com.ssafy._66days.mainservice.user.model.dto.UserSignUpRequestDTO;
import com.ssafy._66days.mainservice.user.model.entity.User;
import com.ssafy._66days.mainservice.user.model.repository.UserRepository;
import com.ssafy._66days.mainservice.global.util.FileUtil;
import com.ssafy._66days.mainservice.animal.model.dto.AnimalDTO;
import com.ssafy._66days.mainservice.tier.model.dto.TierDTO;
import com.ssafy._66days.mainservice.user.model.dto.UserDetailDTO;


import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.multipart.MultipartFile;

@Service
@RequiredArgsConstructor
@Transactional
public class UserService {

    private final UserRepository userRepository;
    private final AnimalRepository animalRepository;
    private final TierRepository tierRepository;
    private final GroupMemberRepository groupMemberRepository;
    private final ChallengeRepository challengeRepository;
    private final GroupChallengeRepository groupChallengeRepository;
    private final MyChallengeRepository myChallengeRepository;
    private final PersonalChallengeLogRepository personalChallengeLogRepository;
    private final GroupChallengeMemberRepository groupChallengeMemberRepository;
    private final FileUtil fileUtil;
    @Value("${file.path.upload-images-users}")
    private String userImageFilePath;

    public void signup(UUID userId, UserSignUpRequestDTO userSignUpRequestDTO) {
        if (userRepository.findById(userId).isPresent()) {
            // should throw duplicated error
            throw new RuntimeException();
        }
        String nickname = userSignUpRequestDTO.getNickname();
        String email = userSignUpRequestDTO.getEmail();
        String profileImagePath = userSignUpRequestDTO.getProfileImagePath();
        if (nickname == null || email == null || profileImagePath == null) {
            throw new RuntimeException();
        }

        if (userRepository.findByNickname(nickname).isPresent()) {
            throw new RuntimeException();
        }

        User user = User.builder()
                .userId(userId)
                .nickname(nickname)
                .animalId(1L)
                .tierId(1L)
                .email(email)
                .profileImagePath(profileImagePath)
                .exp(0L)
                .point(0L)
                .build();
        userRepository.save(user);
    }

    @Transactional(readOnly = true)
    public boolean isNicknameAvailable(String nickname) {
        return userRepository.findByNickname(nickname).isEmpty();
    }

    public UUID getUserUuidByNickname(String nickname) {
        Optional<UUID> optionalUUID = userRepository.findUserIdByNickname(nickname);
        return optionalUUID.orElse(null);
    }

    @Transactional(readOnly = true)
    public UserDetailDTO findUserById(UUID userId) throws Exception {
        User user = userRepository.findById(userId).orElseThrow(() -> new NoSuchElementException("존재하지 않는 유저입니다."));
        AnimalDTO animalDTO = AnimalDTO.of(animalRepository.findById(user.getAnimalId()).orElseThrow(()-> new NoSuchElementException("해당하는 동물이 없습니다.")));
        TierDTO tierDTO = TierDTO.of(tierRepository.findByTierId(user.getTierId()).orElseThrow(()->new NoSuchElementException("해당하는 티어 정보가 없습니다.")));
        UserDetailDTO userDetailDTO = UserDetailDTO.of(user, animalDTO, tierDTO);
        return userDetailDTO;
    }

    public void modifyNickname(UUID userId, String nickname) throws Exception{
        User user = userRepository.findById(userId).orElseThrow(() -> new NoSuchElementException("존재하지 않는 유저입니다."));
        user.updateNickname(nickname);
    }

    public void modifyPofileImage(UUID userId, MultipartFile image) throws Exception{
        if (image.isEmpty()) {
            throw new InputMismatchException("필요한 값이 들어오지 않았습니다.");
        }
        User user = userRepository.findById(userId).orElseThrow(() -> new NoSuchElementException("존재하지 않는 유저입니다."));
        String savePath = fileUtil.fileUpload(image, userImageFilePath);
        user.updateImage(savePath);
    }

    @Transactional(readOnly = true)
    public UserDetailResponseDTO getMainPage(UUID userId) {

        // -------------------- userDetail -------------------------------------
        Map<String, Object> userInfo = new HashMap<>();
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new InputMismatchException("존재하지 않는 유저입니다"));
        Tier tier = tierRepository.findByTierId(user.getTierId())
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 티어 정보입니다"));
        Animal animal = animalRepository.findById(user.getAnimalId())
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 동물 정보입니다"));

        AnimalDTO animalDTO = AnimalDTO.of(animal);
        TierDTO tierDTO = TierDTO.of(tier);
        UserDetailDTO userDetailDTO = UserDetailDTO.of(user, animalDTO, tierDTO);
        userInfo.put("userInfo", userDetailDTO);                                                        // ! userDetailDTO
        // ----------------------today's t0d0-------------------------------------
        Map<String, Object> todoInfo = new HashMap<>();
        List<MainPageTodoResponseDTO> todayTodos = new ArrayList<>();
        List<MyChallenge> myChallenges = myChallengeRepository.findByUserAndState(user, "ACTIVATED");
        if (myChallenges != null) {
            for (int i = 0; i < myChallenges.size(); i++) {
                MyChallenge myChallenge = myChallenges.get(i);
                Long myChallengeId = myChallenge.getMyChallengeId();
                LocalDate today = LocalDate.now();
                PersonalChallengeLog todayStreak = personalChallengeLogRepository.findByMyChallengeIdAndTime(myChallengeId, today);
                boolean state = false;
                if (todayStreak != null) {
                    state = true;
                }
                MainPageTodoResponseDTO todayTodoDTO = MainPageTodoResponseDTO.of(myChallenge, state);
                todayTodos.add(todayTodoDTO);
            }
        }
        todoInfo.put("todayTodoInfo", todayTodos);                                                  // ! todaytodo 정보
        //-----------------Groups--------------------------------------------
        Map<String, Object> groups = new HashMap<String, Object>();                             // 그룹 정보 담을 배열
        //----------------MyGroup-------------------------------------------
        String profileImagePath = user.getProfileImagePath();                                   // 유저 프로필사진
        List<String> challengeImagePaths = new ArrayList<>();                                   // 개인 챌린지 이미지 담을 배열
        if (myChallenges != null) {
            for (int i = 0; i < myChallenges.size(); i++) {
                challengeImagePaths.add(myChallenges.get(i).getChallenge().getBadgeImage());    // 챌린지 이미지 담는다
            }
        }
        MainPageMyGroupResponseDTO mainPageMyGroup = MainPageMyGroupResponseDTO.of(profileImagePath, challengeImagePaths);
        groups.put("myGroupInfo", mainPageMyGroup);                                                     // ! 개인 그룹DTO
        //---------------Group----------------------------------
        List<MainPageGroupResponseDTO> mainPageGroup = new ArrayList<>();                                       // 그룹 정보 담을 배열
        List<GroupMember> groupMemberList = groupMemberRepository.findByUserAndIsDeleted(user, false);      // 내가 참여중인 그룹들
        List<String> groupImagePath = new ArrayList<>();                                                        // 각 그룹의 진행중인 챌린지 이미지담을 배열
        for (int i = 0; i < groupMemberList.size(); i++) {
            Group group = groupMemberList.get(i).getGroup();
            List<GroupChallenge> groupChallenges = groupChallengeRepository.findByGroupAndState(group, "ACTIVATED");
            for (int j = 0; j < groupChallenges.size(); j++) {
                String image = groupChallenges.get(j).getChallenge().getBadgeImage();
                groupImagePath.add(image);
            }
            MainPageGroupResponseDTO mainPageGroupResponseDTO = MainPageGroupResponseDTO.of(group, groupImagePath);
            mainPageGroup.add(mainPageGroupResponseDTO);
        }
        groups.put("groupInfo", mainPageGroup);                                                         // ! 그룹정보 DTO








        if (!groupChallengeMemberList.isEmpty()) {
            for (int i = 0; i < groupChallengeMemberList.size(); i++) {

                GroupChallenge groupChallenge = groupChallengeRepository.findById(groupChallengeMemberList.get(i).getGroupChallenge().getGroupChallengeId()) // 그룹챌린지를 찾아서
                        .orElseThrow(() -> new IllegalArgumentException("알수없는 에러"));
                if (groupChallenge.getState().equals("ACTIVATED")) {                                 // 진행중인 챌린지라면
                    Long challengeId = groupChallenge.getChallenge().getChallengeId();          // 그것의 챌린지 아이디를 찾는다
                    temp.add(challengeId);                                                      // 그룹에서 참여하고 있는 challenge들의 id값을 temp에 저장
                }
            }
        }











        MainPageGroupResponseDTO myGroup = null;

        List<GroupMember> groupMembers = groupMemberRepository.findAllByUser(user);
        if (groupMembers.isEmpty()) {
            throw new RuntimeException();
        }

        List<Challenge> challenges = challengeRepository.findAll();

        for (GroupMember groupMember : groupMembers) {
            Group group = groupMember.getGroup();
            MainPageGroupResponseDTO groupDTO = Group.toGroupMainPageResponseDTO(group);
            List<Badge> badges = new ArrayList<>();
            for (Challenge challenge : challenges) {
                // Optional 변경 필요
                List<GroupChallenge> groupChallenge = groupChallengeRepository.findByGroupAndChallengeAndState(group, challenge, "SUCCESSFUL"); // 성공한게 아니고 진행중인거 가져와야함
                if (groupChallenge != null) {
                    badges.add(challenge.getBadge());
                }
            }
            List<String> badgeImagePathList = new ArrayList<>();        // 뱃지 이미지는 챌린지에서 바로 받을 수 잇음
            for (Badge badge : badges) {
                badgeImagePathList.add(badge.getImagePath());
            }
            groupDTO.setBadges(badgeImagePathList);
            if (groupMember.getUser().equals(user)) {
                myGroup = groupDTO;
            } else {
                groups.add(groupDTO);
            }
        }


        return UserDetailResponseDTO.builder()
                .userId(user.getUserId())
                .email(user.getEmail())
                .nickname(user.getNickname())
                .exp(user.getExp())
                .point(user.getPoint())
                .profileImagePath(user.getProfileImagePath())
                .tier(
                        TierMainPageResponseDTO.builder()
                                .imagePath(tier.getImagePath())
                                .title(tier.getTitle())
                                .build()
                ).animal(
                        AnimalMainPageResponseDTO.builder()
                                .animalId(animal.getAnimalId())
                                .name(animal.getAnimalName())
                                .imagePath(animal.getImagePath())
                                .build()
                ).myGroup(myGroup)
                .groups(groups)
                .build();
    }
}
