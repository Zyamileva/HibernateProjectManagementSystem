package controller.projects;

import controller.*;
import controller.general.ShowMainCommand;
import config.DataBaseManagerConnector;
import repository.ProjectsRepository;
import service.ProjectsServiceImpl;
import service.converter.DeveloperConverter;
import service.converter.ProjectsConverter;

import java.sql.Connection;

public class SelectProject extends Command {
    Connection connector = DataBaseManagerConnector.getInstance().getConnector();
    DeveloperConverter developerConverter = new DeveloperConverter();
    ProjectsConverter projectsConverter = new ProjectsConverter();
    ProjectsRepository projectsRepository = new ProjectsRepository(connector);
    ProjectsServiceImpl projectsService = new ProjectsServiceImpl(projectsRepository, developerConverter, projectsConverter);

    public SelectProject() {
        super(CommandEnum.SELECT_PROJECT);
    }

    @Override
    public CommandResponse execute() {
//        while (true) {
//            int id = CommandLineReader.readInt("Enter id by which you want to get the record");
//            if (projectsService.findById(id).isPresent()) {
//                System.out.println(projectsService.findById(id).get().toString());
//                break;
//            } else {
//                System.out.println("There is no such id in the table. Enter again");
//            }
//        }
        return new CommandResponse(true);
    }

    @Override
    public NextCommands nextCommands() {
        return new NextCommands(new ShowMainCommand());
    }
}