package com.example.blog_app.model.jpa;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.vladmihalcea.hibernate.type.json.JsonBinaryType;
import com.vladmihalcea.hibernate.type.json.JsonStringType;
import lombok.*;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;
import org.hibernate.annotations.TypeDefs;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString(exclude = {"blog", "user", "files"})
@EqualsAndHashCode(exclude = {"blog", "user", "files"})
@TypeDefs(
        {@TypeDef(name = "jsonb", typeClass = JsonBinaryType.class),
                @TypeDef(name = "json", typeClass = JsonStringType.class)})
@Entity
public class Post implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String content;

    @Type(type = "json")
    private String[] tags;

    @Column(name = "post_status")
    @Enumerated(EnumType.ORDINAL)
    PostStatus postStatus;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "blog_id")
    private Blog blog;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
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
