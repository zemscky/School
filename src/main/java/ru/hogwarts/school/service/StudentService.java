package ru.hogwarts.school.service;

import org.springframework.stereotype.Service;
import ru.hogwarts.school.exceptions.StudentNotFoundException;
import ru.hogwarts.school.model.Student;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class StudentService {
    private long count = 0;
    private final Map<Long, Student> students = new HashMap<>();

    private void checkExistsStudent(Long id){
        if (!students.containsKey(id)) {
            throw new StudentNotFoundException();
        }
    }

    public Student addStudent(Student student){
        long newId = count++;
        student.setId(newId);
        students.put(newId, student);
        return student;
    }

    public Student editStudent(Long id, Student student){
        checkExistsStudent(id);

        Student currentStudent = students.get(id);
        currentStudent.setAge(student.getAge());
        currentStudent.setName(student.getName());
        return currentStudent;
    }

    public Student getStudent(Long id){
        checkExistsStudent(id);
        return students.get(id);
    }

    public void removeStudent(Long id){
        checkExistsStudent(id);
        students.remove(id);
    }

    public Collection<Student> getAll(){
        return students.values();
    }

    public Collection<Student> getStudents(int age){
        return students.values().stream()
                .filter(e -> e.getAge() == age)
                .collect(Collectors.toList());
    }
}
