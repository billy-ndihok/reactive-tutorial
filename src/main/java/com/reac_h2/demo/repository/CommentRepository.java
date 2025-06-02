package com.reac_h2.demo.repository;

import java.util.List;

import com.reac_h2.demo.model.Comment;
import jakarta.transaction.Transactional;
import org.springframework.data.r2dbc.repository.R2dbcRepository;
import reactor.core.publisher.Flux;


public interface CommentRepository extends R2dbcRepository<Comment, Long> {
    Flux<Comment> findByTutorialId(Long postId);

    @Transactional
    void deleteByTutorialId(long tutorialId);
}
