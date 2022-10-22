package controller.projects;

import controller.*;
import controller.general.ShowMainCommand;
import config.DataBaseManagerConnector;
import repository.ProjectsRepository;
import service.ProjectsServiceImpl;
import service.converter.DeveloperConverter;
import service.converter.ProjectsConverter;

import java.sql.Connection;

import static utils.StringUtils.NEW_LINE;
import static utils.StringUtils.SEPARATOR_SHORT;

public class FindAllProjects extends Command {
    public FindAllProjects() {
        super(CommandEnum.FIND_ALL_PROJECT);
    }

    Connection connector = DataBaseManagerConnector.getInstance().getConnector();
    ProjectsConverter projectsConverter = new ProjectsConverter();
    DeveloperConverter developerConverter = new DeveloperConverter();
    ProjectsRepository projectsRepository = new ProjectsRepository(connector);
    ProjectsServiceImpl projectsService = new ProjectsServiceImpl(projectsRepository, developerConverter, projectsConverter);

    @Override
    public CommandResponse execute() {
        projectsService.findAll()
                .forEach(company -> System.out.println(company + NEW_LINE + SEPARATOR_SHORT + NEW_LINE));
        return new CommandResponse(true);
    }

    @Override
    public NextCommands nextCommands() {
        return new NextCommands(new ShowMainCommand());
    }
}