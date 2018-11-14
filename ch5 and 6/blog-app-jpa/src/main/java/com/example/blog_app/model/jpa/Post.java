package com.example.blog_app.model.jpa;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString(exclude = {"blog", "user", "files"})
@EqualsAndHashCode(exclude = {"blog", "user", "files"})
@Entity
public class Post implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String content;

    @Column(name = "post_status")
    @Type(type = "com.example.blog_app.constants.PgEnumType",
            parameters = {@org.hibernate.annotations.Parameter(name = "enumClassName",
                    value = "com.example.blog_app.model.jpa.PostStatus")})
    PostStatus postStatus;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "blog_id")
    private Blog blog;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "author_id")
    private User user;

    @JsonIgnore
    @ManyToMany(cascade = {CascadeType.MERGE,CascadeType.REFRESH}, fetch = FetchType.LAZY)
    @JoinTable(
            name = "post_files",joinColumns = {
                    @JoinColumn(name = "post_id", referencedColumnName = "id")},
            inverseJoinColumns={
                    @JoinColumn(name = "file_id", referencedColumnName = "id")})
    private Set<File> files;
}
