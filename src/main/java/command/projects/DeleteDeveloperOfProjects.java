package command.projects;

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

public class DeleteDeveloperOfProjects extends Command {
    public DeleteDeveloperOfProjects() {
        super(CommandEnum.DELETE_DEVELOPER_OF_PROJECT);
    }

    Connection connector = DataBaseManagerConnector.getInstance().getConnector();
    ProjectsConverter projectsConverter = new ProjectsConverter();
    DeveloperConverter developerConverter = new DeveloperConverter();
    ProjectsRepository projectsRepository = new ProjectsRepository(connector);
    DevelopersRepository developersRepository = new DevelopersRepository(connector);
    ProjectsServiceImpl projectsService = new ProjectsServiceImpl(projectsRepository, developerConverter, projectsConverter);
    DeveloperServiceImpl developerService = new DeveloperServiceImpl(developersRepository, developerConverter);

    @Override
    public CommandResponse execute() {
        while (true) {
            int id = CommandLineReader.readInt("Enter id Projects where you want delete developer");
            if (projectsService.findById(id).isPresent()) {
                while (true) {
                    int developerId = CommandLineReader.readInt("Enter id of Developer");
                    if (projectsService.findByIdDeveloperIdProjects(developerId, id)) {
                        projectsService.deleteOfIdsDeveloperOfProject(developerId, id);
                        System.out.println("Developer delete.");
                        if (!CommandLineReader.readLine("If there is another employee that you want delete, then enter 'Yes' else 'No'")
                                .equals("Yes")) {
                            return new CommandResponse(true);
                        } else {
                            continue;
                        }
                    } else {
                        System.out.println("There is no such developers. Enter id yet");
                    }
                }
            } else {
                System.out.println("There is no such id in the table. Enter again");
            }
        }
    }

    @Override
    public NextCommands nextCommands() {
        return new NextCommands(new ShowMainCommand());
    }
}