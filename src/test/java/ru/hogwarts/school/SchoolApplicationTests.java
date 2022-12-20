package ru.hogwarts.school;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import ru.hogwarts.school.controller.StudentController;
import ru.hogwarts.school.model.Student;
import ru.hogwarts.school.repositories.StudentRepository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class SchoolApplicationTests {
    @LocalServerPort
    private int port;

    @Autowired
    private StudentController studentController;

    @Autowired
    private TestRestTemplate restTemplate;
    @Autowired
    private StudentRepository studentRepository;

    @Test
    public void contextLoads() throws Exception {
        Assertions
                .assertThat(studentController)
                .isNotNull();
    }

    @Test
    public void getStudentInfoByIdTest() throws Exception { // Returns a student by id
        Assertions
                .assertThat(this.restTemplate.getForObject("http://localhost:" + port + "/student/1", String.class))
                .isNotEmpty();
    }

    @Test
    public void addStudentTest() throws Exception { // Add student
        Student student = new Student();
        student.setName("Ron");
        student.setAge(16);
        Assertions
                .assertThat(this.restTemplate.getForObject("http://localhost:" + port + "/student", String.class))
                .isNotEmpty();
    }

    @Test
    public void editStudentByIdTest() throws Exception { // Edit a student by id
        Assertions
                .assertThat(this.restTemplate.getForObject("http://localhost:" + port + "/student/1", String.class))
                .isNotEmpty();
    }

//    @Test
//    public void deleteStudentByIdTest() throws Exception { // Delete student
//        Student student = new Student();
//        student.setName("Ron");
//        student.setAge(16);
//        long id = this.studentRepository.save(student).getId();
//
//        assertEquals(1, this.studentRepository.findAll().size());
//        this.restTemplate.delete("http://localhost:" + port + "/student/" + id);
//        assertTrue( studentRepository.findAll().isEmpty());
//    }

    @Test
    public void getAllStudentsTest() throws Exception {   // Returns all students
        Assertions
                .assertThat(this.restTemplate.getForObject("http://localhost:" + port + "/student", String.class))
                .isNotEmpty();
    }


    @Test
    public void getStudentsByAge() throws Exception { // Returns list students by age
        Assertions
                .assertThat(this.restTemplate.getForObject("http://localhost:" + port + "/student/age/16", String.class))
                .isNotEmpty();
    }

}
