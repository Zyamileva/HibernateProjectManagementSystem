package command;

import model.dto.DevelopersDto;
import model.dto.ProjectsDto;
import service.ProjectsServiceImpl;
import view.View;

import java.util.List;

public class ListDevelopersOfProjects implements Command {
    public static final String LIST_DEVELOPERS_OF_PROJECTS = "query_2";
    private View view;
    ProjectsServiceImpl projectsService;

    public ListDevelopersOfProjects(View view, ProjectsServiceImpl projectsService) {
        this.view = view;
        this.projectsService = projectsService;
    }

    @Override
    public boolean canExecute(String input) {
        return (input.equals(LIST_DEVELOPERS_OF_PROJECTS));
    }

    @Override
    public void execute() {
        view.write("Enter id of projects.");
        int id = Integer.parseInt(view.read());
        ProjectsDto projectsDto = projectsService.findById(id).get();
        List<DevelopersDto> developers = projectsService.ListDevelopersOfProjects(id);
        view.write("Project: " + projectsDto.getName());
        view.write("Developers:");
        developers.stream().forEach(System.out::println);
    }
}
