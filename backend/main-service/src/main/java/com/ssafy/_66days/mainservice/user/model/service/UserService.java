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
}
