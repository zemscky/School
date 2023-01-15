package ru.hogwarts.school.controller;

import io.swagger.v3.oas.annotations.Operation;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.hogwarts.school.service.HomeworkService;
@RestController
public class HomeworkController {
    private final HomeworkService parallelStreamsService;

    public HomeworkController(HomeworkService parallelStreamsService) {
        this.parallelStreamsService = parallelStreamsService;
    }

    @GetMapping("/parallelsStream")
    @Operation(summary = "Returns integer value")
    public ResponseEntity<Integer> integerValue() {
        return ResponseEntity.ok(parallelStreamsService.integerValue());
    }
}
