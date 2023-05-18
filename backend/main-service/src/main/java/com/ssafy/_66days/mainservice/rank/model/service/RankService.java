package com.ssafy._66days.mainservice.rank.model.service;

import com.ssafy._66days.mainservice.animal.model.entity.Animal;
import com.ssafy._66days.mainservice.animal.model.repository.AnimalRepository;
import com.ssafy._66days.mainservice.rank.model.entity.BadgeRank;
import com.ssafy._66days.mainservice.rank.model.entity.ExpRank;
import com.ssafy._66days.mainservice.rank.model.repository.BadgeRankRepository;
import com.ssafy._66days.mainservice.rank.model.repository.ExpRankRepository;
import com.ssafy._66days.mainservice.tier.model.entity.Tier;
import com.ssafy._66days.mainservice.tier.model.repository.TierRepository;
import com.ssafy._66days.mainservice.user.model.entity.User;
import com.ssafy._66days.mainservice.user.model.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Transactional(readOnly = true)
@Slf4j
@Service
public class RankService {

    private final BadgeRankRepository badgeRankRepository;
    private final ExpRankRepository expRankRepository;
    private final UserRepository userRepository;
    private final TierRepository tierRepository;
    private final AnimalRepository animalRepository;

    public BadgeRank findBadgeRankByUserId(UUID id) {
        return badgeRankRepository.findByUserId(id).orElseThrow(RuntimeException::new);
    }

    public List<BadgeRank> findTop3BadgeRanks() {
        return badgeRankRepository.findTop3Rank();
    }

    public ExpRank findExpRankByUserId(UUID id) {
        return expRankRepository.findByUserId(id).orElseThrow(RuntimeException::new);
    }

    public List<ExpRank> findTop3ExpRanks() {
        return expRankRepository.findTop3Rank();
    }

    public void ExpRankToday() {
        List<User> userList = userRepository.findAllOrderByExp();
        Map<Long, String> animalNameMap =
                animalRepository.findAll()
                        .stream()
                        .collect(Collectors.toMap(Animal::getAnimalId, Animal::getAnimalName, (a, b) -> b));
        Map<Long, String> tierNameMap =
                tierRepository.findAll()
                        .stream()
                        .collect(Collectors.toMap(Tier::getTierId, Tier::getName, (a, b) -> b));
        LocalDate today = LocalDate.now();
        int numOfUsers = userList.size();

        log.debug("date : {}", today);
        for (int rank = 1; rank <= numOfUsers; rank++) {
            User user = userList.get(rank - 1);
            ExpRank expRank = ExpRank.builder()
                    .date(today)
                    .rank(rank)
                    .userId(user.getUserId())
                    .nickname(user.getNickname())
                    .tierName(tierNameMap.get(user.getTierId()))
                    .animalName(animalNameMap.get(user.getAnimalId()))
                    .exp(user.getExp())
                    .build();
            expRankRepository.save(expRank);
            log.debug("exp rank {} => id : {}, nickname : {}", rank, user.getUserId(), user.getNickname());
        }
    }

}
