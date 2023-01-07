package ru.hogwarts.school.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import ru.hogwarts.school.exceptions.FacultyNotFoundException;
import ru.hogwarts.school.exceptions.StudentNotFoundException;
import ru.hogwarts.school.model.Faculty;
import ru.hogwarts.school.repositories.FacultyRepository;

import java.util.Collection;

@Service
public class FacultyService {
    private final FacultyRepository facultyRepository;
    Logger logger = LoggerFactory.getLogger(FacultyService.class);

    public FacultyService(FacultyRepository facultyRepository) {
        this.facultyRepository = facultyRepository;
    }

    public Faculty addFaculty(Faculty faculty) {
        logger.debug("Was invoked method for add a faculty");
        return this.facultyRepository.save(faculty);
    }

    public Faculty editFaculty(Long id, Faculty faculty) {
        logger.debug("Was invoked method for edit a faculty");
        Faculty dbFaculty = this.facultyRepository.findById(id).orElseThrow(FacultyNotFoundException::new);
        dbFaculty.setName(faculty.getName());
        dbFaculty.setColor(faculty.getColor());
        return this.facultyRepository.save(dbFaculty);
    }

    public Faculty findFaculty(Long id) {
        logger.debug("Was invoked method for find a faculty by id");
        return this.facultyRepository.findById(id).get();
    }

    public Faculty deleteFaculty(Long id) {
        logger.debug("Was invoked method for delete a faculty by id");
        Faculty dbFaculty = this.facultyRepository.findById(id).orElseThrow(FacultyNotFoundException::new);
        this.facultyRepository.delete(dbFaculty);
        return dbFaculty;
    }

    public Collection<Faculty> getAllFaculties() {
        logger.debug("Was invoked method for get all faculty");
        return this.facultyRepository.findAll();
    }

    public Collection<Faculty> findByColor(String color) {
        logger.debug("Was invoked method for find a faculty by color");
        return this.facultyRepository.findByColor(color);
    }

    public Collection<Faculty> findFacultyByNameIgnoreCaseOrColorIgnoreCase(String name, String color) {
        logger.debug("Was invoked method for find a faculty by name");
        return this.facultyRepository.findFacultyByNameIgnoreCaseOrColorIgnoreCase(name, color);
    }
}
