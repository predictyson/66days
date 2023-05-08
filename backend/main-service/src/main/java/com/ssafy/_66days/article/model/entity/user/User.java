package com.ssafy._66days.article.model.entity.user;

import com.ssafy._66days.article.model.entity.Animal;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.UUID;

@Entity
@Table(name = "user")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User implements Serializable {
    @Id
    @GeneratedValue
    @GenericGenerator(name="uuid", strategy="uuid4")
    @Column(name="user_id", nullable= false, columnDefinition = "BINARY(16)")
    private UUID userId;

    @NotBlank
    @Column(name="email", nullable = false)
    private String email;

    @Column(name="profile_image_path", nullable = true)
    private String profileImagePath;
    @NotBlank
    @Column(name="nickname", nullable = false)
    private String nickName;

    @NotNull
    @Column(name = "exp", nullable = false)
    private Long exp = 0L;


    @NotNull
    @Column(name = "point", nullable = false)
    private Long point = 0L;

    @Column(name = "github_url", nullable = true)
    private String githubUrl;

    @Column(name = "blog_url", nullable = true)
    private String blogUrl;

    @NotNull
    @OneToOne
    @JoinColumn(name = "animal_id", nullable = false)
    private Animal animalId;


}
