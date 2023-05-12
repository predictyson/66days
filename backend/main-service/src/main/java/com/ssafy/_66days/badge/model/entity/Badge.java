package com.ssafy._66days.badge.model.entity;

import lombok.*;
import org.hibernate.annotations.GeneratorType;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Entity
@Table(name = "badge")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Badge {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "badge")
    private Long badgeId;

    @NotBlank
    @Column(name = "name", nullable = false)
    private String badgeName;
    @NotBlank
    @Column(name = "description", nullable = false)
    private String description;
    @NotBlank
    @Column(name = "image_path", nullable = false)
    private String imagePath;


}
