package com.ssafy._66days.mono.user.model.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import com.ssafy._66days.mono.challenge.model.entity.GroupChallengeMember;
import com.ssafy._66days.mono.challenge.model.entity.MyChallenge;
import com.ssafy._66days.mono.challenge.model.entity.mongodb.GroupChallengeLog;
import com.ssafy._66days.mono.challenge.model.entity.mongodb.PersonalChallengeLog;
import com.ssafy._66days.mono.challenge.model.reposiotry.*;
import com.ssafy._66days.mono.global.exception.DuplicatedInputException;
import com.ssafy._66days.mono.global.exception.InvalidInputException;
import com.ssafy._66days.mono.page.model.dto.MainPageGroupResponseDTO;
import com.ssafy._66days.mono.page.model.dto.MainPageMyGroupResponseDTO;
import com.ssafy._66days.mono.page.model.dto.MainPageResponseDTO;
import com.ssafy._66days.mono.page.model.dto.MainPageTodoResponseDTO;
import com.ssafy._66days.mono.user.model.dto.UserSocialRegistParamDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ssafy._66days.mono.animal.model.dto.AnimalMainPageResponseDTO;
import com.ssafy._66days.mono.animal.model.entity.Animal;
import com.ssafy._66days.mono.animal.model.repository.AnimalRepository;
import com.ssafy._66days.mono.badge.model.entity.Badge;
import com.ssafy._66days.mono.challenge.model.entity.Challenge;
import com.ssafy._66days.mono.challenge.model.entity.GroupChallenge;
import com.ssafy._66days.mono.group.model.dto.GroupMainPageResponseDTO;
import com.ssafy._66days.mono.group.model.entity.Group;
import com.ssafy._66days.mono.group.model.entity.GroupMember;
import com.ssafy._66days.mono.group.model.repository.GroupMemberRepository;
import com.ssafy._66days.mono.tier.model.dto.TierMainPageResponseDTO;
import com.ssafy._66days.mono.tier.model.entity.Tier;
import com.ssafy._66days.mono.tier.model.repository.TierRepository;
import com.ssafy._66days.mono.user.model.dto.UserDetailResponseDTO;
import com.ssafy._66days.mono.user.model.dto.UserSignUpRequestDTO;
import com.ssafy._66days.mono.user.model.entity.User;
import com.ssafy._66days.mono.user.model.repository.UserRepository;
import com.ssafy._66days.mono.global.util.FileUtil;
import com.ssafy._66days.mono.animal.model.dto.AnimalDTO;
import com.ssafy._66days.mono.tier.model.dto.TierDTO;
import com.ssafy._66days.mono.user.model.dto.UserDetailDTO;


import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.multipart.MultipartFile;

import java.util.InputMismatchException;
import java.util.NoSuchElementException;
import java.util.regex.Pattern;

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

    public void socialRegist(UserSocialRegistParamDTO userDTO) {
        checkSocialRegistrationValidation(userDTO);
        log.info("social regist :{}", userDTO);
        userRepository.save(UserSocialRegistParamDTO.of(userDTO));
    }

    private void checkSocialRegistrationValidation(UserSocialRegistParamDTO userDTO) {
        String emailRegexp = "^[a-zA-Z0-9+-\\_.]+@[a-zA-Z0-9-]+\\.[a-zA-Z0-9-.]+$";
        String nicknameRegexp = "^[a-zA-Z가-힇0-9]{2,16}$";

        User user = userRepository.findByEmail(userDTO.getEmail()).orElse(null);
        if (user != null) {
            throw new DuplicatedInputException("이미 가입된 이메일입니다.");
        }

        // null check
        if (userDTO.getEmail() == null) {
            throw new InvalidInputException("이메일이 null입니다.");
        }
        if (userDTO.getNickname() == null) {
            throw new InvalidInputException("닉네임이 null입니다.");
        }

        // email regexp check
        if (!Pattern.matches(emailRegexp, userDTO.getEmail())) {
            throw new InvalidInputException("이메일 입력값이 올바르지 않습니다.");
        }
        // nickname regexp check
        if (!Pattern.matches(nicknameRegexp, userDTO.getNickname())) {
            throw new InvalidInputException("닉네임 입력값이 올바르지 않습니다.");
        }
    }

	public User getUserById(UUID userId) {
        return userRepository.findById(userId).orElseThrow(() -> new NoSuchElementException("존재하지 않는 유저입니다."));
	}
}
