package ru.hogwarts.school.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import ru.hogwarts.school.exceptions.StudentNotFoundException;
import ru.hogwarts.school.model.Student;
import ru.hogwarts.school.repositories.StudentRepository;

import javax.transaction.Transactional;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class StudentService {

    private final StudentRepository studentRepository;
    Logger logger = LoggerFactory.getLogger(StudentService.class);

    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public Student addStudent(Student student) {
        logger.debug("Was invoked method for add a student");
        return this.studentRepository.save(student);
    }

    public Student editStudent(Long id, Student student) {
        logger.debug("Was invoked method for edit a student");
        Student dbStudent = this.studentRepository.findById(id).orElseThrow(StudentNotFoundException::new);
        dbStudent.setName(student.getName());
        dbStudent.setAge(student.getAge());
        return this.studentRepository.save(dbStudent);
    }

    public Student findStudent(Long id) {
        logger.debug("Was invoked method for find a student by id");
        return this.studentRepository
                .findById(id)
                .orElseThrow(StudentNotFoundException::new);
    }

    public void deleteStudent(Long id) {
        logger.debug("Was invoked method for delete a student by id");
        this.studentRepository.deleteById(id);
    }

    public Collection<Student> getAllStudents() {
        logger.debug("Was invoked method for get all students");
        return this.studentRepository.findAll();
    }

    public Collection<Student> findByAge(int age) {
        logger.debug("Was invoked method for find a student by age");
        return this.studentRepository.findByAge(age);
    }

    public Collection<Student> findByAgeBetween(int minAge, int maxAge) {
        logger.debug("Was invoked method for find a student by age between");
        return this.studentRepository.findByAgeBetween(minAge, maxAge);
    }

    public int getStudentCount() {
        logger.debug("Was invoked method for get a student count");
        return this.studentRepository.getStudent();
    }

    public int getStudentByAverageAge() {
        logger.debug("Was invoked method for get a student by average age");
        return this.studentRepository.getStudentsByAge();
    }

    public Collection<Student> getTheLastFiveStudents() {
        logger.debug("Was invoked method for get the last five students");
        return this.studentRepository.getTheLastFiveStudents();
    }

    public List<String> getSortedNameStudent() {
        logger.debug("Was invoked method for get the sorted name students");
        return studentRepository
                .findAll()
                .stream()
                .map(s -> s.getName().toUpperCase())
                .sorted()
                .collect(Collectors.toList());
    }

    public double getAverageAge() {
        logger.debug("Was invoked method for get the get average age students");
        return studentRepository
                .findAll()
                .stream()
                .mapToInt(Student::getAge)
                .average()
                .orElseThrow();
    }

    public void getStudentInThread() {
        logger.debug("Was invoked method for get students in thread");
        List<Student> students = this.studentRepository.findAll();
        System.out.println(students.get(0).getName());
        System.out.println(students.get(1).getName());
        new Thread(() -> {
            System.out.println(students.get(2).getName());
            System.out.println(students.get(3).getName());
        }).start();
        new Thread(() -> {
            System.out.println(students.get(4).getName());
            System.out.println(students.get(5).getName());
        }).start();
    }

    public void getStudentInThreadSynchronously() {
        logger.debug("Was invoked method for get the get students int thread synchronously");
        List<Student> students = this.studentRepository.findAll();
        printStudentSynchronously(students, 0,1);
        new Thread(() -> {
            printStudentSynchronously(students, 2,3);
        }).start();
        new Thread(() -> {
            printStudentSynchronously(students, 4,5);
        }).start();
    }

    public synchronized void printStudentSynchronously(List<Student> student, int index1, int index2) {
        System.out.println(student.get(index1).getName());
        System.out.println(student.get(index2).getName());
    }
}