package command.companies;

import command.*;
import command.general.ShowMainCommand;
import config.DataBaseManagerConnector;
import model.dto.ProjectsDto;
import repository.CompaniesRepository;
import repository.ProjectsRepository;
import service.CompaniesServiceImpl;
import service.ProjectsServiceImpl;
import service.converter.CompaniesConverter;
import service.converter.DeveloperConverter;
import service.converter.ProjectsConverter;

import java.sql.Connection;
import java.util.List;

public class DeleteCompany extends Command {
    Connection connector = DataBaseManagerConnector.getInstance().getConnector();
    ProjectsConverter projectsConverter = new ProjectsConverter();
    DeveloperConverter developerConverter = new DeveloperConverter();
    CompaniesConverter companiesConverter = new CompaniesConverter();
    ProjectsRepository projectsRepository = new ProjectsRepository(connector);
    CompaniesRepository companiesRepository = new CompaniesRepository(connector);
    CompaniesServiceImpl companiesService = new CompaniesServiceImpl(companiesRepository, companiesConverter);
    ProjectsServiceImpl projectsService = new ProjectsServiceImpl(projectsRepository, developerConverter, projectsConverter);

    public DeleteCompany() {
        super(CommandEnum.DELETE_COMPANY);
    }

    @Override
    public CommandResponse execute() {
        while (true) {
            int id = CommandLineReader.readInt("Enter id of Companies");
            if (companiesService.findById(id).isPresent()) {
                int finalId = id;
                List<ProjectsDto> projectsDtos = projectsService.findAll().stream()
                        .filter(el -> el.getCompanyId() == finalId).toList();
                for (ProjectsDto element : projectsDtos) {
                    projectsService.deleteOfIdsProject(element.getId());
                    projectsService.delete(element);
                }
                companiesService.delete(companiesService.findById(id).get());
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