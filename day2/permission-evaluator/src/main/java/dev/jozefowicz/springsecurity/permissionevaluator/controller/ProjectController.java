package dev.jozefowicz.springsecurity.permissionevaluator.controller;

import dev.jozefowicz.springsecurity.permissionevaluator.model.Project;
import dev.jozefowicz.springsecurity.permissionevaluator.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/projects")
public class ProjectController {

    @Autowired
    private ProjectService projectService;

    @GetMapping
    public List<Project> listAll() {
        return projectService.listAll();
    }

    @RequestMapping(value = "/{projectId}", method = RequestMethod.GET)
    public Project findById(@PathVariable long projectId) {
        return projectService.readById(projectId);
    }

    @RequestMapping(method = RequestMethod.PUT)
    public void updateById(@RequestBody Project project) {
        projectService.update(project);
    }

    @RequestMapping(value = "/{projectId}", method = RequestMethod.PUT)
    public void updateNameById(@PathVariable long projectId, @RequestParam(name = "name", defaultValue = "NO_NAME") String projectName) {
        projectService.updateNameById(projectId, projectName);
    }

}
