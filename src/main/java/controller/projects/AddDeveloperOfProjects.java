package controller.projects;

import controller.*;
import controller.general.ShowMainCommand;
import config.DataBaseManagerConnector;;
import repository.DevelopersRepository;
import repository.ProjectsRepository;
import service.DeveloperServiceImpl;
import service.ProjectsServiceImpl;
import service.converter.DeveloperConverter;
import service.converter.ProjectsConverter;

import java.sql.Connection;

public class AddDeveloperOfProjects extends Command {
    public AddDeveloperOfProjects() {
        super(CommandEnum.ADD_DEVELOPER_OF_PROJECT);
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
//        while (true) {
//            int id = CommandLineReader.readInt("Enter id Projects where you want add developer");
//            if (projectsService.findById(id).isPresent()) {
//                while (true) {
//                    int developerId = CommandLineReader.readInt("Enter id of Developer");
//                    if (developerService.findById(developerId).isPresent()) {
//                        projectsService.saveDevelopers(developerService.findById(developerId).get().getId(),
//                                id);
//                        System.out.println("Developer added.");
//                        if (!CommandLineReader.readLine("If there is another employee, then enter 'Yes' else 'No'")
//                                .equals("Yes")) {
//                            return new CommandResponse(true);
//                        } else {
//                            continue;
//                        }
//                    } else {
//                        System.out.println("There is no such developers. Enter id yet");
//                    }
//                }
//            } else {
//                System.out.println("There is no such id in the table. Enter again");
//            }
//        }
        return null;
    }

    @Override
    public NextCommands nextCommands() {
        return new NextCommands(new ShowMainCommand());
    }
}