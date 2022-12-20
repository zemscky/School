package ru.hogwarts.school.service;

import org.springframework.stereotype.Service;
import ru.hogwarts.school.exceptions.StudentNotFoundException;
import ru.hogwarts.school.model.Student;
import ru.hogwarts.school.repositories.StudentRepository;

import javax.transaction.Transactional;
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
        Student dbStudent = this.studentRepository.findById(id).orElseThrow(StudentNotFoundException::new);
        dbStudent.setName(student.getName());
        dbStudent.setAge(student.getAge());
        return this.studentRepository.save(dbStudent);
    }

    public Student findStudent(Long id) {
        return this.studentRepository
                .findById(id)
                .orElseThrow(StudentNotFoundException::new);
    }

    public void deleteStudent(Long id) {
        this.studentRepository.deleteById(id);
    }

    public Collection<Student> getAllStudents() {
        return this.studentRepository.findAll();
    }

    public Collection<Student> findByAge(int age) {
        return this.studentRepository.findByAge(age);
    }

    public Collection<Student> findByAgeBetween(int minAge, int maxAge) {
        return this.studentRepository.findByAgeBetween(minAge, maxAge);
    }
}