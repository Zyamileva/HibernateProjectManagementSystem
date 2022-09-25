package command;

import model.dto.DevelopersDto;
import model.dto.ProjectsDto;
import service.ProjectsServiceImpl;
import view.View;

import java.util.List;

public class ListDevelopersOfProjects implements Command {
    public static final String LIST_DEVELOPERS_OF_PROJECTS = "query_2";
    private final View view;
    private final ProjectsServiceImpl projectsService;

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
        while (true) {
            int id = Integer.parseInt(view.read());
            if (projectsService.findById(id).isPresent()) {
                ProjectsDto projectsDto = projectsService.findById(id).get();
                List<DevelopersDto> developers = projectsService.ListDevelopersOfProjects(id);
                view.write("Project: " + projectsDto.getName());
                view.write("Developers:");
                developers.forEach(System.out::println);
                view.write(String.format("Enter %s to see all command", Help.HELP));
                break;
            } else {
                view.write("There is no such id in the table. Enter again");
            }
        }
    }
}
