package com.ssafy._66days.group.model.entity;

import lombok.*;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.Entity;
import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Entity
@Table(name="group")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Group {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="group_id", nullable=false)
    private Long groupId;

    @NotBlank
    @Column(name = "name", nullable = false)
    private String groupName;
    @NotNull
    @Column(name = "max_member_count", nullable = false, columnDefinition = "TINYINT")
    @ColumnDefault("0")
    private int maxMemberCount;
    @Column(name = "image_path", nullable = true)
    private String imagePath;
    @NotNull
    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;
    @Column(name = "updated_at", nullable = true)
    private LocalDateTime updatedAt;
    @NotNull
    @Column(name = "is_deleted", nullable = false, columnDefinition = "TINYINT(1)")
    @ColumnDefault("false")
    private boolean isDeleted;
}
