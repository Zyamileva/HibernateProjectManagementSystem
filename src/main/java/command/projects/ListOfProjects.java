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
import java.util.List;

public class ListOfProjects extends Command {
    Connection connector = DataBaseManagerConnector.getInstance().getConnector();
    ProjectsConverter projectsConverter = new ProjectsConverter();
    DeveloperConverter developerConverter = new DeveloperConverter();
    ProjectsRepository projectsRepository = new ProjectsRepository(connector);
    ProjectsServiceImpl projectsService = new ProjectsServiceImpl(projectsRepository, developerConverter, projectsConverter);

    public ListOfProjects() {
        super(CommandEnum.LIST_OF_PROJECTS);
    }

    @Override
    public CommandResponse execute() {
        List<ProjectsDto> allProjects = projectsService.findAll();
        for (ProjectsDto projects : allProjects) {
            System.out.println(projects.getDatePosted() + " - " + projects.getName() + " - " +
                    projectsService.CountDevelopersOfProjects(projects.getId()));
        }
        return new CommandResponse(true);
    }

    @Override
    public NextCommands nextCommands() {
        return new NextCommands(new ShowMainCommand());
    }
}