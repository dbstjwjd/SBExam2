package com.korea.test;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.web.bind.annotation.GetMapping;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@Entity
public class NoteBook {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private LocalDateTime createDate;

    @OneToMany(mappedBy = "noteBook", cascade = CascadeType.REMOVE)
    private List<Post> postList;
}
