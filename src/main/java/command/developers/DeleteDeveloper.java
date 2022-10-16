package command.developers;

import command.*;
import command.general.ShowMainCommand;
import config.DataBaseManagerConnector;
import repository.DevelopersRepository;
import repository.ProjectsRepository;
import service.DeveloperServiceImpl;
import service.ProjectsServiceImpl;
import service.converter.DeveloperConverter;
import service.converter.ProjectsConverter;

import java.sql.Connection;

public class DeleteDeveloper extends Command {
    Connection connector = DataBaseManagerConnector.getInstance().getConnector();
    DeveloperConverter developerConverter = new DeveloperConverter();
    DevelopersRepository developersRepository = new DevelopersRepository(connector);
    DeveloperServiceImpl developerService = new DeveloperServiceImpl(developersRepository, developerConverter);
    ProjectsConverter projectsConverter = new ProjectsConverter();
    ProjectsRepository projectsRepository = new ProjectsRepository(connector);
    ProjectsServiceImpl projectsService = new ProjectsServiceImpl(projectsRepository, developerConverter, projectsConverter);

    public DeleteDeveloper() {
        super(CommandEnum.DELETE_DEVELOPER);
    }

    @Override
    public CommandResponse execute() {
        while (true) {
            int id = CommandLineReader.readInt("Enter id of Developer that you want delete");
            if (developerService.findById(id).isPresent()) {
                projectsService.deleteProjectOfIdsDeveloper(id);
                developerService.deleteSkillsOfDeveloper(id);
                developerService.delete(developerService.findById(id).get());
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