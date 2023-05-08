package com.ssafy._66days.article.model.entity;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Entity
@Table(name = "animal")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Animal {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "animal_id")
    private Long animalId;

    @NotBlank
    @Column(name = "name")
    private String animalName;

    @NotBlank
    @Column(name = "image_path")
    private String imagePath;
}
