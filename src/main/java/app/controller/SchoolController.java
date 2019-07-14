package app.controller;

import app.domain.School;
import app.repository.SchoolRepository;
import com.gmail.solovyov.daniil.monitoring.Monitoring;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/schools")
public class SchoolController {

    private SchoolRepository schoolRepository;

    @Autowired
    public SchoolController(SchoolRepository schoolRepository) {
        this.schoolRepository = schoolRepository;
    }

    @Monitoring("FIND_ALL_SCHOOLS")
    @GetMapping
    public List<School> findAll() {
        return schoolRepository.findAll();
    }
}
