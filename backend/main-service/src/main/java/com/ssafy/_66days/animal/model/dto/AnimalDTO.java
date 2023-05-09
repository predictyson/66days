package com.ssafy._66days.animal.model.dto;

import com.ssafy._66days.animal.model.entity.Animal;
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

    public AnimalDTO(Animal animal) {
        this.animalId = animal.getAnimalId();
        this.animalName = animal.getAnimalName();
        this.imagePath = animal.getImagePath();
    }
}
