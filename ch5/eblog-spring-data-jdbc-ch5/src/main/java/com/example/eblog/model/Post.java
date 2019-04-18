package com.example.eblog.model;

import lombok.*;
import org.springframework.data.annotation.Id;

import java.io.Serializable;

@Data
@Builder
@AllArgsConstructor
@ToString(exclude = {"author"})
@EqualsAndHashCode(exclude = {"author"})
public class Post implements Serializable {

    @Id
    private Long id;

    private String title;
    private String content;

}
