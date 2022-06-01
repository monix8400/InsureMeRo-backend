package licenta.InsureMeRo.services;

import licenta.InsureMeRo.models.Incident;
import licenta.InsureMeRo.repository.IncidentsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class IncidentsService {

    private final IncidentsRepository incidentsRepository;

    @Autowired
    public IncidentsService(IncidentsRepository incidentsRepository) {
        this.incidentsRepository = incidentsRepository;
    }

    public List<Incident> getIncidentsByPersonalInfoId(Long personalInfoId) {
        List<Incident> incidentsList = new ArrayList<>();
        for (Incident incident : incidentsRepository.findAll()) {
            if (incident.getPersonalInfoId() == personalInfoId) {
                incidentsList.add(incident);
            }
        }
        return incidentsList;
    }
}
