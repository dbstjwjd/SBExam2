package com.korea.test;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PostRepository extends JpaRepository<Post, Long>  {
    List<Post> findAllByNoteBook(NoteBook noteBook);
}
