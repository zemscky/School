package ru.hogwarts.school.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import ru.hogwarts.school.model.Student;

import java.util.Collection;

public interface StudentRepository extends JpaRepository<Student, Long> {
    Collection<Student> findByAge(int age);

    Collection<Student> findByAgeBetween(int minAge, int maxAge);

    @Query(value = "SELECT COUNT(*) FROM student ", nativeQuery = true)
    int getStudent();

    @Query(value = "SELECT AVG(age) FROM student ", nativeQuery = true)
    int getStudentsByAge();

    @Query(value = "SELECT * FROM student ORDER BY id DESC LIMIT 5", nativeQuery = true)
    Collection<Student> getTheLastFiveStudents();
}
