package ru.hogwarts.school.service;

import org.springframework.stereotype.Service;
import ru.hogwarts.school.exceptions.FacultyNotFoundException;
import ru.hogwarts.school.exceptions.StudentNotFoundException;
import ru.hogwarts.school.model.Faculty;
import ru.hogwarts.school.repositories.FacultyRepository;

import java.util.Collection;

@Service
public class FacultyService {
    private final FacultyRepository facultyRepository;

    public FacultyService(FacultyRepository facultyRepository) {
        this.facultyRepository = facultyRepository;
    }

    public Faculty addFaculty(Faculty faculty) {
        return this.facultyRepository.save(faculty);
    }

    public Faculty editFaculty(Long id, Faculty faculty) {
        Faculty dbFaculty = this.facultyRepository.findById(id).orElseThrow(FacultyNotFoundException::new);
        dbFaculty.setName(faculty.getName());
        dbFaculty.setColor(faculty.getColor());
        return this.facultyRepository.save(dbFaculty);
    }

    public Faculty findFaculty(Long id) {
        return this.facultyRepository.findById(id).get();
    }

    public Faculty deleteFaculty(Long id) {
        Faculty dbFaculty = this.facultyRepository.findById(id).orElseThrow(FacultyNotFoundException::new);
        this.facultyRepository.delete(dbFaculty);
        return dbFaculty;
    }

    public Collection<Faculty> getAllFaculties() {
        return this.facultyRepository.findAll();
    }

    public Collection<Faculty> findByColor(String color) {
        return this.facultyRepository.findByColor(color);
    }

    public Collection<Faculty> findFacultyByNameIgnoreCaseOrColorIgnoreCase(String name, String color) {
        return this.facultyRepository.findFacultyByNameIgnoreCaseOrColorIgnoreCase(name, color);
    }
}
