package com.example.eblog.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString(exclude = {"author"})
@EqualsAndHashCode(exclude = {"author"})
public class Post implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    private String content;
    private Status status;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnore
    private Author author;

}
