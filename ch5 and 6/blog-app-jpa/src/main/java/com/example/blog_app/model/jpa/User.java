package com.example.blog_app.model.jpa;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Set;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString(exclude = {"posts"})
@EqualsAndHashCode(exclude = {"posts"})
@Entity
@Table(name = "`user`")
public class User implements Serializable {

    public User(String username, String password, String email)   {
        this.username = username;
        this.password = password;
        this.email = email;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long blogId;

    private String username;
    private String password;
    private String email;
    private Boolean isActive;

    @Column(insertable = false, updatable = false)
    private LocalDateTime activatedAt;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL,
            mappedBy = "user", orphanRemoval = true)
    private Set<Post> posts;

}
