package com.ssafy._66days.animal.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ssafy._66days.animal.model.entity.Animal;

public interface AnimalRepository extends JpaRepository<Animal, Long> {
}
