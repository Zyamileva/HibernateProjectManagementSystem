package command.projects;

import command.*;
import command.general.ShowMainCommand;
import config.DataBaseManagerConnector;
import model.dto.ProjectsDto;
import repository.ProjectsRepository;
import service.ProjectsServiceImpl;
import service.converter.DeveloperConverter;
import service.converter.ProjectsConverter;

import java.sql.Connection;

public class SalaryOfProjects extends Command {
    Connection connector = DataBaseManagerConnector.getInstance().getConnector();
    ProjectsConverter projectsConverter = new ProjectsConverter();
    DeveloperConverter developerConverter = new DeveloperConverter();
    ProjectsRepository projectsRepository = new ProjectsRepository(connector);
    ProjectsServiceImpl projectsService = new ProjectsServiceImpl(projectsRepository, developerConverter, projectsConverter);

    public SalaryOfProjects() {
        super(CommandEnum.SALARY_OF_PROJECTS);
    }

    @Override
    public CommandResponse execute() {
        while (true) {
            int id = CommandLineReader.readInt("Enter id of projects.");
            if (projectsService.findById(id).isPresent()) {
                ProjectsDto projectsDto = projectsService.findById(id).get();
                int salary = projectsService.salaryOfProjects(id);
                System.out.println("Project " + projectsDto.getName() + ": salary (sum) of all project developers = " + salary);
                break;
            } else {
                System.out.println("There is no such id in the table. Enter again");
            }
        }
        return new CommandResponse(true);
    }

    @Override
    public NextCommands nextCommands() {
        return new NextCommands(new ShowMainCommand());
    }
}