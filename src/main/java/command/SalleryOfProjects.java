package command;

import model.dto.ProjectsDto;
import service.ProjectsServiceImpl;
import view.View;

public class SalleryOfProjects implements Command {
    public static final String SALLERY_OF_PROJECTS = "query_1";
    private View view;
    ProjectsServiceImpl projectsService;

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
        int id = Integer.parseInt(view.read());
        ProjectsDto projectsDto = projectsService.findById(id).get();
        int salary = projectsService.sallaryOfProjects(id);
        view.write("Project " + projectsDto.getName() + ": salary (sum) of all project developers = " + salary);
    }
}
