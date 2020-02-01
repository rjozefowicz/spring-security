package dev.jozefowicz.springsecurity.permissionevaluator.service;

import dev.jozefowicz.springsecurity.permissionevaluator.model.Project;
import dev.jozefowicz.springsecurity.permissionevaluator.repository.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProjectService {

    @Autowired
    private ProjectRepository projectRepository;

    // TODO zwroc tylko projekty do ktorych uzytkownik ma uprawnienie READ
    public List<Project> listAll() {
        return projectRepository.findAll();
    }

    // TODO uzytkownik ma uprawnienie read
    public void read(Project project) {
        System.out.println("Could read project");
    }

    // TODO uzytkownik ma uprawnienie read, bazuj na ID i klasie obiektu
    public Project readById(Long id) {
        return projectRepository.findById(id).orElseThrow(() -> new IllegalStateException());
    }

    // TODO uzytkownik ma uprawnienie write
    public void update(Project project) {
        projectRepository.findById(project.getId()).ifPresent(p -> {
            p.setName(project.getName());
        });
    }

    // TODO uzytkownik ma uprawnienie write, bazuj na ID i klasie obiektu
    public void updateNameById(Long id, String name) {
        projectRepository.findById(id).ifPresent(p -> {
            p.setName(name);
        });
    }

}
