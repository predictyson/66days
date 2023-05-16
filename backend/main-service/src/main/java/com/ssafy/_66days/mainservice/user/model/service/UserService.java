package com.ssafy._66days.mainservice.user.model.service;

//import com.ssafy._66days.animal.model.dto.AnimalMainPageResponseDTO;
import com.ssafy._66days.mainservice.animal.model.repository.AnimalRepository;
import com.ssafy._66days.mainservice.global.util.FileUtil;
import com.ssafy._66days.mainservice.group.model.repository.GroupMemberRepository;
//import com.ssafy._66days.tier.model.dto.TierMainPageResponseDTO;
import com.ssafy._66days.mainservice.user.model.dto.UserDetailDTO;
import com.ssafy._66days.mainservice.user.model.entity.User;
import com.ssafy._66days.mainservice.user.model.repository.UserRepository;
import com.ssafy._66days.mainservice.tier.model.repository.TierRepository;
//import com.ssafy._66days.user.model.dto.UserDetailResponseDTO;
//import com.ssafy._66days.user.model.dto.UserSignUpRequestDTO;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.InputMismatchException;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.UUID;

import lombok.RequiredArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

@Service
@RequiredArgsConstructor
@Transactional
public class UserService {

    private final UserRepository userRepository;
    private final AnimalRepository animalRepository;
    private final TierRepository tierRepository;
    private final GroupMemberRepository groupMemberRepository;
    private final FileUtil fileUtil;
    @Value("${file.path.upload-images-users}")
    private String userImageFilePath;

//    public void createUser(UUID uuid, UserSignUpRequestDTO userSignUpRequestDto) {
//        if (userRepository.findById(uuid).isPresent()) {
//            // should throw duplicated error
//            throw new RuntimeException();
//        }
//        User user = userSignUpRequestDto.toEntity();
//        userRepository.save(user);
//    }

    public boolean isNicknameAvailable(String nickname) {
        return userRepository.findByNickname(nickname).isPresent();
    }

    public UUID getUserUuidByNickname(String nickname) {
        Optional<UUID> optionalUUID = userRepository.findUserIdByNickname(nickname);
        return optionalUUID.orElse(null);
    }

    public UserDetailDTO findUserById(UUID userId) throws Exception {
        User user = userRepository.findById(userId).orElseThrow(() -> new NoSuchElementException("존재하지 않는 유저입니다."));
        UserDetailDTO userDetailDTO = UserDetailDTO.of(user);
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
