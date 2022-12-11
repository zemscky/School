package ru.hogwarts.school.service;

import org.springframework.stereotype.Service;
import ru.hogwarts.school.exceptions.FacultyNotFoundException;
import ru.hogwarts.school.model.Faculty;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class FacultyService {
    private long count = 0;
    private final Map<Long, Faculty> faculties = new HashMap<>();

    private void checkExistsFaculty(Long id){
        if (!faculties.containsKey(id)) {
            throw new FacultyNotFoundException();
        }
    }

    public Faculty addFaculty(Faculty Faculty){
        long newId = count++;
        Faculty.setId(newId);
        faculties.put(newId, Faculty);
        return Faculty;
    }

    public Faculty editFaculty(Long id, Faculty Faculty){
        checkExistsFaculty(id);
        Faculty currentFaculty = faculties.get(id);
        currentFaculty.setColor(Faculty.getColor());
        currentFaculty.setName(Faculty.getName());
        return currentFaculty;
    }

    public Faculty getFaculty(Long id){
        checkExistsFaculty(id);
        return faculties.get(id);
    }

    public void removeFaculty(Long id){
        checkExistsFaculty(id);
        faculties.remove(id);
    }

    public Collection<Faculty> getAll(){
        return faculties.values();
    }

    public Collection<Faculty> getFaculties(String color){
        return faculties.values().stream()
                .filter(e -> e.getColor().equals(color))
                .collect(Collectors.toList());
    }
}