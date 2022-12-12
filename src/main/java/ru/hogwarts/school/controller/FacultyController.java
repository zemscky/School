package ru.hogwarts.school.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
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

    @GetMapping("/{id}") // GET http://localhost:8080/faculty/1
    @Operation(summary = "Returns a faculty by id", tags = "faculty")
    @ApiResponses({@ApiResponse(responseCode = "200", description = "Faculty model", content = @Content(schema = @Schema(implementation = Faculty.class))), @ApiResponse(responseCode = "404", description = "Faculty not found", content = @Content())})
    public Faculty getById(@PathVariable("id") Long id) {
        return this.facultyService.findFaculty(id);
    }

    @PostMapping  // POST http://localhost:8080/faculty
    @Operation(summary = "Add faculty", tags = "faculty")
    public Faculty addFaculty(@RequestBody Faculty faculty) {
        return this.facultyService.addFaculty(faculty);
    }

    @PutMapping("/{id}") // PUT http://localhost:8080/faculty/1
    @Operation(summary = "Edit faculty by id", tags = "faculty")
    public Faculty editFaculty(@PathVariable("id") Long id, @RequestBody Faculty faculty) {
        return this.facultyService.editFaculty(id, faculty);
    }

    @DeleteMapping("/{id}")  // DELETE http://localhost:8080/faculty/1
    @Operation(summary = "Remove faculty by id", tags = "faculty")
    public ResponseEntity<Void> deleteFaculty(@PathVariable Long id) {
        this.facultyService.deleteFaculty(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping // GET http://localhost:8080/faculty
    @Operation(summary = "Returns list of all faculty", tags = "faculty")
    public Collection<Faculty> getAllFaculty() {
        return this.facultyService.getAllFaculties();
    }

    @GetMapping("/color/{color}") // GET http://localhost:8080/faculty/color/red
    @Operation(summary = "Returns list faculty by color", tags = "faculty")
    public Collection<Faculty> getStudentsByAge(@PathVariable("color") String color) {
        return this.facultyService.findByColor(color);
    }

    @GetMapping("/filter")
    public ResponseEntity<Collection<Faculty>> findFaculty(@RequestParam(required = false) String name, @RequestParam(required = false) String color) {
        return ResponseEntity.ok(facultyService.findFacultyByNameIgnoreCaseOrColorIgnoreCase(name, color));
    }
}