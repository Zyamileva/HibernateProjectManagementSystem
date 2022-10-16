package command.projects;

import command.*;
import command.general.ShowMainCommand;
import config.DataBaseManagerConnector;
import repository.ProjectsRepository;
import service.ProjectsServiceImpl;
import service.converter.DeveloperConverter;
import service.converter.ProjectsConverter;

import java.sql.Connection;

public class DeleteProject extends Command {
    Connection connector = DataBaseManagerConnector.getInstance().getConnector();
    DeveloperConverter developerConverter = new DeveloperConverter();
    ProjectsConverter projectsConverter = new ProjectsConverter();
    ProjectsRepository projectsRepository = new ProjectsRepository(connector);
    ProjectsServiceImpl projectsService = new ProjectsServiceImpl(projectsRepository, developerConverter, projectsConverter);

    public DeleteProject() {
        super(CommandEnum.DELETE_PROJECT);
    }

    @Override
    public CommandResponse execute() {
        while (true) {
            int id = CommandLineReader.readInt("Enter id of Project");
            if (projectsService.findById(id).isPresent()) {
                projectsService.deleteOfIdsProject(id);
                projectsService.delete(projectsService.findById(id).get());
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