package com.korea.test;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class NoteBookService {

    private final NoteBookRepository noteBookRepository;

    public NoteBook createNoteBook() {
        NoteBook noteBook = new NoteBook();
        noteBook.setName("새 노트");
        noteBook.setCreateDate(LocalDateTime.now());
        return this.noteBookRepository.save(noteBook);
    }

    public NoteBook getNoteBook(Long id) {
        return this.noteBookRepository.findById(id).get();
    }

    public List<NoteBook> getList() {
        return this.noteBookRepository.findAll();
    }
}
