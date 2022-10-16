package command.developers;

import command.*;
import command.general.ShowMainCommand;
import config.DataBaseManagerConnector;
import model.dto.DevelopersDto;
import model.dto.ProjectsDto;
import repository.ProjectsRepository;
import service.ProjectsServiceImpl;
import service.converter.DeveloperConverter;
import service.converter.ProjectsConverter;

import java.sql.Connection;
import java.util.List;

public class ListDevelopersOfProjects extends Command {

    Connection connector = DataBaseManagerConnector.getInstance().getConnector();
    DeveloperConverter developerConverter = new DeveloperConverter();
    ProjectsConverter projectsConverter = new ProjectsConverter();
    ProjectsRepository projectsRepository = new ProjectsRepository(connector);
    ProjectsServiceImpl projectsService = new ProjectsServiceImpl(projectsRepository, developerConverter, projectsConverter);

    public ListDevelopersOfProjects() {
            super(CommandEnum.LIST_DEVELOPERS_OF_PROJECTS);
    }

    @Override
    public CommandResponse execute() {
        while (true) {
            int id = CommandLineReader.readInt("Enter id of projects.");
            if (projectsService.findById(id).isPresent()) {
                ProjectsDto projectsDto = projectsService.findById(id).get();
                List<DevelopersDto> developers = projectsService.ListDevelopersOfProjects(id);
                System.out.println("Project: " + projectsDto.getName());
                System.out.println("Developers:");
                developers.forEach(System.out::println);
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