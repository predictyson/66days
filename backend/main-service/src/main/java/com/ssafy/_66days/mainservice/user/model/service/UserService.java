package com.ssafy._66days.mainservice.user.model.service;

//import com.ssafy._66days.animal.model.dto.AnimalMainPageResponseDTO;
import com.ssafy._66days.mainservice.animal.model.repository.AnimalRepository;
import com.ssafy._66days.mainservice.group.model.repository.GroupMemberRepository;
//import com.ssafy._66days.tier.model.dto.TierMainPageResponseDTO;
import com.ssafy._66days.mainservice.user.model.repository.UserRepository;
import com.ssafy._66days.mainservice.tier.model.repository.TierRepository;
//import com.ssafy._66days.user.model.dto.UserDetailResponseDTO;
//import com.ssafy._66days.user.model.dto.UserSignUpRequestDTO;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.UUID;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class UserService {

    private final UserRepository userRepository;
    private final AnimalRepository animalRepository;
    private final TierRepository tierRepository;
    private final GroupMemberRepository groupMemberRepository;
    // private final Challenge

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

//    public UserDetailResponseDTO getUserDetail(UUID uuid) {
//        // 에러 다 Custom 으로 바꿔야함. 일단 넘어가.
//        // 중간 중간에 memory leak 확인 필요할 것으로 생각됨.
//        User user = userRepository.findById(uuid).orElseThrow(RuntimeException::new);
//        Tier tier = tierRepository.findByTierId(user.getTierId()).orElseThrow(RuntimeException::new);
//        Animal animal = animalRepository.findById(user.getAnimalId()).orElseThrow(RuntimeException::new);
//        List<GroupMember> groupMembers = groupMemberRepository.findByUser(user);
//
//        if (groupMembers.isEmpty()) {
//            throw new RuntimeException();
//        }
//        // 챌린지 리스트를 가져온다.
//        // List<GroupMember> groupMembers 반복 돈다. 이때, 유저 아이디랑 그룹 아이디가 같으면 따로 빼서 저장한다.
//        // 단, List<Group> 하지 말고 바로 List<GroupMainPageResponseDTO>로 가는게 좋겠다.
//            // List<Badge> 만든다.
//            // 챌린지 반복 돌면서 (그룹 아이디, 챌린지 아이디)로 검색한다. 있으면 챌린지로부터 뱃지 추출해서 추가한다.
//            // Empty List면 그냥 null 넣자. 이거 프론트 단에서 에러날 수도 있다. 처리 못해서 곤란해 하는 경우를 봤다.
//        // LAZY 필수고, NULL CHECK 잘해야한다.
//
//
//       return UserDetailResponseDTO.builder()
//            .userId(user.getUserId())
//            .email(user.getEmail())
//            .nickname(user.getNickname())
//            .exp(user.getExp())
//            .point(user.getPoint())
//            .profileImagePath(user.getProfileImagePath())
//            .tier(
//                TierMainPageResponseDTO.builder()
//                    .imagePath(tier.getImagePath())
//                    .title(tier.getTitle())
//                    .build()
//            ).animal(
//                AnimalMainPageResponseDTO.builder()
//                    .animalId(animal.getAnimalId())
//                    .name(animal.getAnimalName())
//                    .imagePath(animal.getImagePath())
//                    .build()
//            ).build();
//    }

    public UUID getUserUuidByNickname(String nickname) {
        Optional<UUID> optionalUUID = userRepository.findUserIdByNickname(nickname);
        return optionalUUID.orElse(null);
    }
}
