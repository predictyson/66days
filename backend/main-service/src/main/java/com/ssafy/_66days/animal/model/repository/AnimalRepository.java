package com.ssafy._66days.animal.model.repository;

import com.ssafy._66days.animal.model.entity.Animal;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AnimalRepository extends JpaRepository<Animal, Long> {
}
