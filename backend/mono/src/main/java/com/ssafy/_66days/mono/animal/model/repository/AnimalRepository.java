package com.ssafy._66days.mono.animal.model.repository;

import com.ssafy._66days.mono.animal.model.entity.Animal;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AnimalRepository extends JpaRepository<Animal, Long> {
}
