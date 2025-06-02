package com.reac_h2.demo.repository;

import com.reac_h2.demo.model.Tutorial;
import org.springframework.data.r2dbc.repository.R2dbcRepository;
import reactor.core.publisher.Flux;


public interface TutorialRepository extends R2dbcRepository<Tutorial, Long> {
    Flux<Tutorial> findByPublished(boolean published);

    Flux<Tutorial> findByTitleContaining(String title);
}