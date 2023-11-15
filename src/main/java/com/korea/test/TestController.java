package com.korea.test;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class TestController {

    private final PostService postService;

    private final NoteBookService noteBookService;

    @RequestMapping("/")
    public String main(Model model) {
        //1. DB에서 데이터 꺼내오기
        List<NoteBook> noteBookList = this.noteBookService.getList();
        if (noteBookList.isEmpty()) {
            this.noteBookService.createNoteBook();
        }
        List<Post> postList = this.postService.getListByNoteBook(noteBookList.get(0));
        if (postList.isEmpty()) {
            this.postService.createPost(noteBookList.get(0));
        }
        //2. 꺼내온 데이터를 템플릿으로 보내기
        model.addAttribute("noteBookList", noteBookList);
        model.addAttribute("targetNoteBook", noteBookList.get(0));
        model.addAttribute("postList", postList);
        model.addAttribute("targetPost", postList.get(0));

        return "main";
    }

    @PostMapping("/write/{id}")
    public String write(@PathVariable("id") Long id) {
        NoteBook noteBook = this.noteBookService.getNoteBook(id);
        Post post = this.postService.createPost(noteBook);
        return String.format("redirect:/detail/%s", post.getId());
    }

    @GetMapping("/detail/{id}")
    public String detail(Model model, @PathVariable Long id) {
        Post post = this.postService.getPost(id);
        NoteBook noteBook = post.getNoteBook();
        List<NoteBook> noteBookList = this.noteBookService.getList();
        model.addAttribute("targetPost", post);
        model.addAttribute("postList", this.postService.getListByNoteBook(noteBook));
        model.addAttribute("noteBookList", noteBookList);
        model.addAttribute("targetNoteBook", noteBook);

        return "main";
    }

    @PostMapping("/update")
    public String update(Long id, String title, String content) {
        Post post = this.postService.getPost(id);
        if (title.trim().isEmpty())
            title = "제목없음";
        this.postService.updatePost(post, title, content);
        return "redirect:/detail/" + id;
    }

    @PostMapping("/delete")
    public String delete(Long id) {
        Post post = this.postService.getPost(id);
        this.postService.deletePost(post);
        return "redirect:/";
    }

    @PostMapping("/noteBook/create")
    public String createNoteBook() {
        NoteBook noteBook = this.noteBookService.createNoteBook();
        this.postService.createPost(noteBook);
        return "redirect:/";
    }

    @GetMapping("/noteBook/select/{id}")
    public String selectNoteBook(@PathVariable("id") Long id, Model model) {
        NoteBook noteBook = this.noteBookService.getNoteBook(id);
        List<NoteBook> noteBookList = this.noteBookService.getList();
        List<Post> postList = this.postService.getListByNoteBook(noteBook);
        if (postList.isEmpty()) {
            this.postService.createPost(noteBook);
        }
        model.addAttribute("noteBookList", noteBookList);
        model.addAttribute("targetNoteBook", noteBook);
        model.addAttribute("postList", postList);
        model.addAttribute("targetPost", postList.get(0));
        return "main";
    }
}
