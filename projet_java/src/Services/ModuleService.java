package Services;

import Entities.Module;
import repositories.ModuleRepository;

import java.util.List;

public class ModuleService {
    ModuleRepository moduleRepository = new ModuleRepository();

    public void ajouterModule(Module module) {
        moduleRepository.insert(module);
    }

    public List<Module> listerModules() {
        return moduleRepository.listerModules();
    }

    public Module getModuleById(int id) {
        return moduleRepository.selectModuleById(id);
    }
}
