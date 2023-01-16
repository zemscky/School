package ru.hogwarts.school.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.hogwarts.school.model.Student;
import ru.hogwarts.school.service.StudentService;

import java.util.Collection;
import java.util.List;

@RestController
@RequestMapping("/student")
public class StudentController {

    private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping("/{id}") // GET http://localhost:8080/student/1
    @Operation(
            summary = "Returns a student by id",
            tags = "student")
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Student model",
                    content = @Content(schema = @Schema(implementation = Student.class))),
            @ApiResponse(
                    responseCode = "404",
                    description = "Student not found",
                    content = @Content())}
    )
    public Student getById(@PathVariable("id") Long id) {
        return this.studentService.findStudent(id);
    }

    @PostMapping // POST http://localhost:8080/student
    @Operation(summary = "Add student",
            tags = "student")
    public Student addStudent(@RequestBody Student student) {
        return this.studentService.addStudent(student);
    }

    @PutMapping("/{id}") // PUT http://localhost:8080/student/1
    @Operation(summary = "Edit student by id",
            tags = "student")
    public Student editStudent(@PathVariable("id") Long id, @RequestBody Student student) {
        return this.studentService.editStudent(id, student);
    }

    @DeleteMapping("/{id}") // DELETE http://localhost:8080/student/1
    @Operation(summary = "Remove student by id",
            tags = "student")
    public ResponseEntity<Void> deleteStudent(@PathVariable("id") Long id) {
        this.studentService.deleteStudent(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping // GET http://localhost:8080/student
    @Operation(summary = "Returns list of all students",
            tags = "student")
    public Collection<Student> getAllStudent() {
        return this.studentService.getAllStudents();
    }

    @GetMapping("/age/{age}") // GET http://localhost:8080/student/age/18
    @Operation(summary = "Returns list students by age",
            tags = "student")
    public Collection<Student> getStudentsByAge(@PathVariable("age") int age) {
        return this.studentService.findByAge(age);
    }

    @GetMapping("/between-age") // GET http://localhost:8080/student/between-age
    @Operation(summary = "Returns list students by age limits",
            tags = "student")
    public ResponseEntity<Collection<Student>> getStudentsByAgeLimits(@RequestParam(required = false) Integer minAge,
                                                                      @RequestParam(required = false) Integer maxAge) {
        if (minAge != null && maxAge != null) {
            return ResponseEntity.ok(studentService.findByAgeBetween(minAge, maxAge));
        }
        return ResponseEntity.ok(studentService.getAllStudents());
    }

    @GetMapping("/count")  // GET http://localhost:8080/student/count
    @Operation(summary = "Returns students count",
            tags = "student")
    public ResponseEntity getStudentsCount() {
        return ResponseEntity.ok(studentService.getStudentCount());
    }

    @GetMapping("/age/average")  // GET http://localhost:8080/student/age/average
    @Operation(summary = "Returns students by average age",
            tags = "student")
    public ResponseEntity getStudentByAverageAge() {
        return ResponseEntity.ok(studentService.getStudentByAverageAge());
    }

    @GetMapping("/lastFive")  // GET http://localhost:8080/student/lastFive
    @Operation(summary = "Returns the last five students",
            tags = "student")
    public ResponseEntity<Collection<Student>> getTheLastFiveStudents() {
        return ResponseEntity.ok(studentService.getTheLastFiveStudents());
    }

    @GetMapping("/filterNameStudent") // GET http://localhost:8080/student/filterNameStudent
    @Operation(summary = "Returns the sorted student list",
            tags = "student")
    public ResponseEntity<List<String>> getSortedNameStudent() {
        return ResponseEntity.ok(studentService.getSortedNameStudent());
    }

    @GetMapping("/filterAverageAgeStudent") // GET http://localhost:8080/student/filterAverageAgeStudent
    @Operation(summary = "Returns average age student",
            tags = "student")
    public ResponseEntity getAverageAge() {
        return ResponseEntity.ok(studentService.getAverageAge());
    }

    @GetMapping("/studentInThread")
    @Operation(summary = "Returns list student int different thread",
            tags = "student")
    public void getStudentInThread() {
        this.studentService.getStudentInThread();
    }

    @GetMapping("/studentInThreadSyn")
    @Operation(summary = "Returns list student int different thread synchronously"
            , tags = "student")
    public void getStudentInThreadSynchronously() {
        this.studentService.getStudentInThreadSynchronously();
    }
}
