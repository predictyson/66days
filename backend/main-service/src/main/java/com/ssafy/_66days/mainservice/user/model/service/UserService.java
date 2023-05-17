package com.ssafy._66days.mainservice.user.model.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ctc.wstx.shaded.msv_core.reader.xmlschema.GroupState;
import com.ssafy._66days.mainservice.animal.model.dto.AnimalMainPageResponseDTO;
import com.ssafy._66days.mainservice.animal.model.entity.Animal;
import com.ssafy._66days.mainservice.animal.model.repository.AnimalRepository;
import com.ssafy._66days.mainservice.badge.model.entity.Badge;
import com.ssafy._66days.mainservice.challenge.model.entity.Challenge;
import com.ssafy._66days.mainservice.challenge.model.entity.GroupChallenge;
import com.ssafy._66days.mainservice.challenge.model.entity.GroupChallengeMember;
import com.ssafy._66days.mainservice.challenge.model.reposiotry.ChallengeRepository;
import com.ssafy._66days.mainservice.challenge.model.reposiotry.GroupChallengeMemberRepository;
import com.ssafy._66days.mainservice.challenge.model.reposiotry.GroupChallengeRepository;
import com.ssafy._66days.mainservice.group.model.dto.GroupMainPageResponseDTO;
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

import java.util.InputMismatchException;
import java.util.NoSuchElementException;

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
    public UserDetailResponseDTO getUserDetail(UUID uuid) {
        User user = userRepository.findById(uuid).orElseThrow(RuntimeException::new);
        Tier tier = tierRepository.findByTierId(user.getTierId()).orElseThrow(RuntimeException::new);
        Animal animal = animalRepository.findById(user.getAnimalId()).orElseThrow(RuntimeException::new);
        List<GroupMainPageResponseDTO> groups = new ArrayList<>();
        GroupMainPageResponseDTO myGroup = null;

        List<GroupMember> groupMembers = groupMemberRepository.findAllByUser(user);
        if (groupMembers.isEmpty()) {
            throw new RuntimeException();
        }

        List<Challenge> challenges = challengeRepository.findAll();

        for (GroupMember groupMember : groupMembers) {
            Group group = groupMember.getGroup();
            GroupMainPageResponseDTO groupDTO = Group.toGroupMainPageResponseDTO(group);
            List<Badge> badges = new ArrayList<>();
            for (Challenge challenge : challenges) {
                // Optional 변경 필요
                List<GroupChallenge> groupChallenge =
                        groupChallengeRepository
                                .findByGroupAndChallengeAndState(group, challenge, "SUCCESSFUL");
                if (groupChallenge != null) {
                    badges.add(challenge.getBadge());
                }
            }
            List<String> badgeImagePathList = new ArrayList<>();
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
