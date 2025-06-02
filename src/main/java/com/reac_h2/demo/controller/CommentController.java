package com.reac_h2.demo.controller;

import com.reac_h2.demo.model.Comment;
import com.reac_h2.demo.repository.CommentRepository;
import com.reac_h2.demo.repository.TutorialRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api")
public class CommentController {

    @Autowired
    private TutorialRepository tutorialRepository;

    @Autowired
    private CommentRepository commentRepository;

    @GetMapping("/tutorials/{tutorialId}/comments")
    public Flux<Comment> getAllCommentsByTutorialId(@PathVariable(value = "tutorialId") Long tutorialId) {
        return commentRepository.findByTutorialId(tutorialId);
    }

    @GetMapping("/comments/{id}")
    public Mono<Comment> getCommentsById(@PathVariable(value = "id") Long id) {
        return commentRepository.findById(id);
    }

    @PostMapping("/tutorials/{tutorialId}/comments")
    public Mono<Comment> createComment(@PathVariable(value = "tutorialId") Long tutorialId,
                                       @RequestBody Comment commentRequest) {
        return tutorialRepository.findById(tutorialId).flatMap(tutorial -> {
            commentRequest.setTutorial(tutorial);
            return commentRepository.save(commentRequest);
        });
    }
}