package command;

import model.dto.ProjectsDto;
import service.ProjectsService;
import view.View;

import java.util.List;

public class ListOfProjects implements Command {
    public static final String LIST_OF_PROJECTS = "query_5";
    private View view;
    ProjectsService projectsService;

    public ListOfProjects(View view, ProjectsService projectsService) {
        this.view = view;
        this.projectsService = projectsService;
    }

    @Override
    public boolean canExecute(String input) {
        return (input.equals(LIST_OF_PROJECTS));
    }

    @Override
    public void execute() {

        List<ProjectsDto> allProjects = projectsService.findAll();
        view.write("   Date_create      -  Name     - Count   ");
        for (ProjectsDto projects : allProjects) {
            view.write(projects.getDatePosted() + " - " + projects.getName() + " - " + projectsService.CountDevelopersOfProjects(projects.getId()));
        }
    }
}
