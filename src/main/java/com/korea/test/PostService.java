package com.korea.test;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PostService {

    private final PostRepository postRepository;

    public Post createPost(NoteBook noteBook) {
        Post post = new Post();
        post.setTitle("new title..");
        post.setContent("");
        post.setCreateDate(LocalDateTime.now());
        post.setNoteBook(noteBook);

        return this.postRepository.save(post);
    }

    public List<Post> getList() {
        return this.postRepository.findAll();
    }

    public Post getPost(Long id) {
        return this.postRepository.findById(id).get();
    }

    public void updatePost(Post post, String title, String content) {
        post.setTitle(title);
        post.setContent(content);
        this.postRepository.save(post);
    }

    public void deletePost(Post post) {
        this.postRepository.delete(post);
    }

    public List<Post> getListByNoteBook(NoteBook noteBook) {
        return this.postRepository.findAllByNoteBook(noteBook);
    }
}
