package com.ssafy._66days.mono.badge.model.entity;

import lombok.*;

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
    @Column(name = "badge_id")
    private Long badgeId;

    @NotBlank
    @Column(name = "name")
    private String badgeName;
    @NotBlank
    @Column(name = "description")
    private String description;
    @NotBlank
    @Column(name = "image_path")
    private String imagePath;


}
