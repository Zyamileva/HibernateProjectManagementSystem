package command;

import model.dto.ProjectsDto;
import service.ProjectsServiceImpl;
import view.View;

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
        int salary = projectsService.sallaryOfProjects(id);
        view.write("Project " + projectsDto.getName() + ": salary (sum) of all project developers = " + salary);
    }
}






//    select first_name as first_name, last_name as last_name, pr.name as name
//        from projects as pr
//        join developers_projects as dp on pr.id=dp.project_id
//        join developers as d on dp.developer_id=d.id
//        where pr.id=1
//
//        select first_name as first_name, last_name as last_name
//        from developers
