package ru.hogwarts.school.service;

import org.springframework.stereotype.Service;
import ru.hogwarts.school.exceptions.FacultyNotFoundException;
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
        return this.facultyRepository.save(faculty);
    }

    public Faculty findFaculty(Long id) {
        return this.facultyRepository.findById(id).get();
    }

    public void deleteFaculty(Long id) {
        this.facultyRepository.deleteById(id);
    }

    public Collection<Faculty> getAllFaculties() {
        return this.facultyRepository.findAll();
    }

    public Collection<Faculty> findByColor(String color) {
        return this.facultyRepository.findByColor(color);
    }
}
