package command;

import model.dto.ProjectsDto;
import service.ProjectsService;
import service.ProjectsServiceImpl;
import view.View;

public class SalleryOfProjects implements Command {
    public static final String SALLERY_OF_PROJECTS = "query_1";
    private final View view;
    private final ProjectsService projectsService;

    public SalleryOfProjects(View view, ProjectsServiceImpl projectsService) {
        this.view = view;
        this.projectsService = projectsService;
    }

    @Override
    public boolean canExecute(String input) {
        return (input.equals(SALLERY_OF_PROJECTS));
    }

    @Override
    public void execute() {
        view.write("Enter id of projects.");
        while (true) {
            int id = Integer.parseInt(view.read());
            if (projectsService.findById(id).isPresent()) {
                ProjectsDto projectsDto = projectsService.findById(id).get();
                int salary = projectsService.sallaryOfProjects(id);
                view.write("Project " + projectsDto.getName() + ": salary (sum) of all project developers = " + salary);
                view.write(String.format("Enter %s to see all command", Help.HELP));
                break;
            } else {
                view.write("There is no such id in the table. Enter again");
            }
        }
    }
}