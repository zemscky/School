package ru.hogwarts.school.service;

import org.springframework.stereotype.Service;
import ru.hogwarts.school.model.Student;
import ru.hogwarts.school.repositories.StudentRepository;

import java.util.Collection;

@Service
public class StudentService {

    private final StudentRepository studentRepository;

    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public Student addStudent(Student student) {
        return this.studentRepository.save(student);
    }

    public Student editStudent(Long id, Student student) {
        return this.studentRepository.save(student);
    }

    public Student findStudent(Long id) {
        return this.studentRepository.findById(id).get();
    }

    public void deleteStudent(Long id) {
        this.studentRepository.deleteById(id);
    }

    public Collection<Student> getAllStudents() {
        return this.studentRepository.findAll();
    }

    public Collection<Student> getByAge(int age) {
        return this.studentRepository.getByAge(age);
    }
}