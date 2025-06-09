package com.reac_h2.demo.controller;

import com.reac_h2.demo.model.Tutorial;
import com.reac_h2.demo.repository.TutorialRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api")
public class TutorialController {

    @Autowired
    TutorialRepository tutorialRepository;

    @GetMapping("/tutorials")
    public Flux<Tutorial> getAllTutorials(@RequestParam(required = false) Mono<String> title) {
        return title.flatMapMany(titleVar -> {
            if(titleVar == null)
                return tutorialRepository.findAll();
            else
                return tutorialRepository.findByTitleContaining(titleVar);
        });
    }

    @PostMapping("/tutorials")
    public Mono<Tutorial> createTutorial(@RequestBody Tutorial tutorial) {
        return tutorialRepository.save(new Tutorial(tutorial.getTitle(), tutorial.getDescription(), true));
    }

    @GetMapping("/tutorials/{id}")
    public Mono<Tutorial> getTutorialById(@PathVariable("id") long id) {
        return tutorialRepository.findById(id);
    }

    @GetMapping("/tutorials/all")
    public Flux<Tutorial> getTutorialAll() {
        return tutorialRepository.findAll();
    }



}