package com.korea.test;

import org.springframework.data.jpa.repository.JpaRepository;

public interface NoteBookRepository extends JpaRepository<NoteBook, Long> {
}
