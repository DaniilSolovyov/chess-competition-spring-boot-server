package app.controller;

import com.gmail.solovyov.daniil.domain.Monitoring;
import com.gmail.solovyov.daniil.repository.MonitoringRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/monitorings")
public class MonitoringController {

    private MonitoringRepository monitoringRepository;

    public MonitoringController(MonitoringRepository monitoringRepository) {
        this.monitoringRepository = monitoringRepository;
    }

    @GetMapping
    public List<Monitoring> findAll(){
        return monitoringRepository.findAll();
    }
}
