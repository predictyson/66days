package com.ssafy._66days.mainservice.animal.model.dto;

import com.ssafy._66days.mainservice.animal.model.entity.Animal;
import lombok.*;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class AnimalDTO {
    private Long animalId;
    private String animalName;
    private String imagePath;

    public static AnimalDTO of(Animal animal) {
        return AnimalDTO.builder()
                .animalId(animal.getAnimalId())
                .animalName(animal.getAnimalName())
                .imagePath(animal.getImagePath())
                .build();
    }
}
