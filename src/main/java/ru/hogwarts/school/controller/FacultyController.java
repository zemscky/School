package ru.hogwarts.school.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.hogwarts.school.model.Faculty;
import ru.hogwarts.school.service.FacultyService;

import java.util.Collection;

@RestController
@RequestMapping("/faculty")

public class FacultyController {
    private final FacultyService facultyService;

    public FacultyController(FacultyService facultyService) {
        this.facultyService = facultyService;
    }

    @GetMapping
    public Collection<Faculty> getAll(){
        return facultyService.getAll();
    }

    @GetMapping("/{id}")
    public Faculty getFaculty(@PathVariable("id") long id){
        return facultyService.getFaculty(id);
    }

    @PostMapping
    public Faculty createFaculty(@RequestBody Faculty Faculty){
        return facultyService.addFaculty(Faculty);
    }

    @PutMapping("/{id}")
    public Faculty editFaculty(@PathVariable("id") Long id, @RequestBody Faculty Faculty){
        return facultyService.editFaculty(id, Faculty);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteFaculty(@PathVariable("id") Long id){
        facultyService.removeFaculty(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/get")
    public Collection<Faculty> getFaculties(@RequestParam("age") String color){
        return facultyService.getFaculties(color);
    }
}