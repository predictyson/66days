package com.ssafy._66days.mainservice.user.model.service;

import java.time.LocalDate;
import java.util.*;

import com.ssafy._66days.mainservice.challenge.model.entity.GroupChallengeMember;
import com.ssafy._66days.mainservice.challenge.model.entity.MyChallenge;
import com.ssafy._66days.mainservice.challenge.model.entity.mongodb.GroupChallengeLog;
import com.ssafy._66days.mainservice.challenge.model.entity.mongodb.PersonalChallengeLog;
import com.ssafy._66days.mainservice.challenge.model.reposiotry.*;
import com.ssafy._66days.mainservice.page.model.dto.MainPageMyGroupResponseDTO;
import com.ssafy._66days.mainservice.page.model.dto.MainPageResponseDTO;
import com.ssafy._66days.mainservice.page.model.dto.MainPageTodoResponseDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ssafy._66days.mainservice.animal.model.entity.Animal;
import com.ssafy._66days.mainservice.animal.model.repository.AnimalRepository;
import com.ssafy._66days.mainservice.challenge.model.entity.GroupChallenge;
import com.ssafy._66days.mainservice.page.model.dto.MainPageGroupResponseDTO;
import com.ssafy._66days.mainservice.group.model.entity.Group;
import com.ssafy._66days.mainservice.group.model.entity.GroupMember;
import com.ssafy._66days.mainservice.group.model.repository.GroupMemberRepository;
import com.ssafy._66days.mainservice.tier.model.entity.Tier;
import com.ssafy._66days.mainservice.tier.model.repository.TierRepository;
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
@Slf4j
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
    private final GroupChallengeLogRepository groupChallengeLogRepository;
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
    public MainPageResponseDTO getMainPage(UUID userId) {                                              // 메인페이지 API
        // -------------------- userDetail -------------------------------------
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new InputMismatchException("존재하지 않는 유저입니다"));                // 유저 객체 받아오기
        Tier tier = tierRepository.findByTierId(user.getTierId())
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 티어 정보입니다"));         // 티어 객체 받아오기
        Animal animal = animalRepository.findById(user.getAnimalId())
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 동물 정보입니다"));         // 동물 객체 받아오기

        AnimalDTO animalDTO = AnimalDTO.of(animal);                                                   // 동물 DTO로 변환
        TierDTO tierDTO = TierDTO.of(tier);                                                           // 티어 DTO로 변환
        UserDetailDTO userDetailDTO = UserDetailDTO.of(user, animalDTO, tierDTO);                     // 유저 정보 DTO로 변환
        // ----------------------today's t0d0-------------------------------------
        List<Object> todayTodos = new ArrayList<>();                                                  // 개인, 그룹 챌린지 t0d0 담을 배열
        // ----------------------myChallenge t0d0---------------------------------
        List<MainPageTodoResponseDTO> myChallengeTodo = new ArrayList<>();                            // 개인챌린지 t0d0 담을 배열
        List<MyChallenge> myChallenges = myChallengeRepository.findByUserAndState(user, "ACTIVATED");   // 진행중인 내 챌린지 받아오기
        if (myChallenges != null) {
            for (int i = 0; i < myChallenges.size(); i++) {                                           // 순회하며
                MyChallenge myChallenge = myChallenges.get(i);                                        // 내 챌린지 하나
                Long myChallengeId = myChallenge.getMyChallengeId();                                  // id값 추출
                LocalDate today = LocalDate.now();                                                    // 오늘 스트릭 찍었는지 비교하기 위한 today값
                PersonalChallengeLog todayStreak = personalChallengeLogRepository.findByMyChallengeIdAndTime(myChallengeId, today); // 오늘의 스트릭 정보 받아오기
                boolean state = false;
                if (todayStreak != null) {
                    state = true;                                                                    // 스트릭 찍었으면 true, 안찍었으면 false
                }
                MainPageTodoResponseDTO todayTodoDTO = MainPageTodoResponseDTO.my(myChallenge, state); // todayDTO로 변환
                myChallengeTodo.add(todayTodoDTO);                                                     // 리스트에 저장
            }
        }
        //----------------------groupChallenge t0d0--------------------------------
        List<MainPageTodoResponseDTO> groupChallengeTodo = new ArrayList<>();                               // 그룹 채린지 t0d0 담을 배열
        List<GroupChallengeMember> groupChallengeMembers = groupChallengeMemberRepository.findByUser(user); // 유저 객체로 그룹챌린지 멤버 객체들 받아오기
        if (groupChallengeMembers != null) {
            for (int i = 0; i < groupChallengeMembers.size(); i++) {
                Long groupChallengeId = groupChallengeMembers.get(i).getGroupChallenge().getGroupChallengeId(); // 거기서 그룹챌린지 id 추출
                GroupChallenge groupChallenge = 
                        groupChallengeRepository.findByGroupChallengeIdAndState(groupChallengeId, "ACTIVATED")
                                .orElse(null);                                                         // 진행 중인 것만 가져오기
                if (groupChallenge != null) {
                    Long activatedGroupChallengeId = groupChallenge.getGroupChallengeId();                  // 진행중인 챌린지의 id 가져오기
                    LocalDate today = LocalDate.now();                                                      // 오늘 스트릭 찍었는지 비교하기 위한 today값
                    GroupChallengeLog todayStreak = 
                            groupChallengeLogRepository.findByUserIdAndGroupChallengeIdAndTime(userId, activatedGroupChallengeId, today); // 오늘의 스트릭 정보 받아오기
                    boolean state = false;
                    if (todayStreak != null) {
                        state = true;                                                                       // 스트릭 찍었으면 true, 안찍었으면 false
                    }
                    MainPageTodoResponseDTO todayTodoDTO = MainPageTodoResponseDTO.group(groupChallenge, state);    // todayDTO로 변환
                    groupChallengeTodo.add(todayTodoDTO);                                                   // 리스트에 저장
                }

            }
        }
        todayTodos.add(myChallengeTodo);        //개인, 그룹 t0d0 두개 담는디
        todayTodos.add(groupChallengeTodo);
        //-----------------Groups--------------------------------------------
        //----------------MyGroup-------------------------------------------
        String profileImagePath = user.getProfileImagePath();                                        // 유저 프로필사진
        List<String> challengeImagePaths = new ArrayList<>();                                        // 개인 챌린지 이미지 담을 배열
        if (myChallenges != null) {
            for (int i = 0; i < myChallenges.size(); i++) {
                challengeImagePaths.add(myChallenges.get(i).getChallenge().getBadgeImage());         // 챌린지 이미지 담는다
            }
        }
        MainPageMyGroupResponseDTO mainPageMyGroup = MainPageMyGroupResponseDTO.of(profileImagePath, challengeImagePaths);
        //---------------Group----------------------------------
        List<MainPageGroupResponseDTO> mainPageGroup = new ArrayList<>();                                       // 그룹 정보 담을 배열
        List<GroupMember> groupMemberList = groupMemberRepository.findByUserAndIsDeleted(user, false);      // 내가 참여중인 그룹들
        for (int i = 0; i < groupMemberList.size(); i++) {
            Group group = groupMemberList.get(i).getGroup();
            List<String> groupImagePath = new ArrayList<>();                                                   // 각 그룹의 진행중인 챌린지 이미지담을 배열
            List<GroupChallenge> groupChallenges = groupChallengeRepository.findByGroupAndState(group, "ACTIVATED"); // 각 그룹에서 진행 중인 챌린지 가져오기
            for (int j = 0; j < groupChallenges.size(); j++) {
                String image = groupChallenges.get(j).getChallenge().getBadgeImage();                          // 해당 챌린지의 이미지 받아와서
                groupImagePath.add(image);                                                                     // 이미지 배열에 저장
            }
            MainPageGroupResponseDTO mainPageGroupResponseDTO = MainPageGroupResponseDTO.of(group, groupImagePath); // 그룹 정보 DTS로 변환
            mainPageGroup.add(mainPageGroupResponseDTO);                                                        // 리스트에 저장
        }

        return MainPageResponseDTO.of(userDetailDTO, todayTodos, mainPageMyGroup, mainPageGroup);
    }
}
