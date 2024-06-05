package Services;

import java.util.List;
import repositories.CoursRepository;
import Entities.Cours;

public class CoursService {
    private CoursRepository coursRepository = new CoursRepository();

    public void ajouterCours(Cours cours) {
        coursRepository.insert(cours);
    }

    public List<Cours> listerTousLesCours() {
        return coursRepository.findAll();
    }

    public List<Cours> listerCoursParModuleEtProfesseur(int moduleId, int professeurId) {
        return coursRepository.findByModuleAndProfesseur(moduleId, professeurId);
    }
}
