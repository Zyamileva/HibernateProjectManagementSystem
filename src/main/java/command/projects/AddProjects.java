package command.projects;

import command.*;
import command.general.ShowMainCommand;
import config.DataBaseManagerConnector;
import model.dto.ProjectsDto;
import repository.CompaniesRepository;
import repository.CustomersRepository;
import repository.DevelopersRepository;
import repository.ProjectsRepository;
import service.CompaniesServiceImpl;
import service.CustomersServiceImpl;
import service.DeveloperServiceImpl;
import service.ProjectsServiceImpl;
import service.converter.CompaniesConverter;
import service.converter.CustomersConverter;
import service.converter.DeveloperConverter;
import service.converter.ProjectsConverter;

import java.sql.Connection;

public class AddProjects extends Command {
    public AddProjects() {
        super(CommandEnum.ADD_PROJECT);
    }

    Connection connector = DataBaseManagerConnector.getInstance().getConnector();
    ProjectsConverter projectsConverter = new ProjectsConverter();
    DeveloperConverter developerConverter = new DeveloperConverter();
    CompaniesConverter companiesConverter = new CompaniesConverter();
    ProjectsRepository projectsRepository = new ProjectsRepository(connector);
    CompaniesRepository companiesRepository = new CompaniesRepository(connector);
    DevelopersRepository developersRepository = new DevelopersRepository(connector);
    CompaniesServiceImpl companiesService = new CompaniesServiceImpl(companiesRepository, companiesConverter);
    ProjectsServiceImpl projectsService = new ProjectsServiceImpl(projectsRepository, developerConverter, projectsConverter);
    DeveloperServiceImpl developerService = new DeveloperServiceImpl(developersRepository, developerConverter);
    CustomersConverter customersConverter = new CustomersConverter();
    CustomersRepository customersRepository = new CustomersRepository(connector);
    CustomersServiceImpl customersService = new CustomersServiceImpl(customersRepository, customersConverter);

    @Override
    public CommandResponse execute() {
        String name = CommandLineReader.readLine("Enter name of Project");
        String task = CommandLineReader.readLine("Enter task_difficulty");
        int idCompany;
        while (true) {
            idCompany = CommandLineReader.readInt("Enter id Company");
            if (companiesService.findById(idCompany).isPresent()) {
                break;
            } else {
                System.out.println("There is no such id in the table. Enter again");
            }
        }
        int idCustomer;
        while (true) {
            idCustomer = CommandLineReader.readInt("Enter id Customer");
            if (customersService.findById(idCustomer).isPresent()) {
                break;
            } else {
                System.out.println("There is no such id in the table. Enter again");
            }
        }
        int cost;
        cost = CommandLineReader.readInt("Enter cost project");
        ProjectsDto projectsDto =
                projectsService.saveProject(new ProjectsDto(name, task, idCustomer, idCompany, cost));
        while (true) {
            int developerId = CommandLineReader.readInt("Enter id of Developers in Project");
            if (developerService.findById(developerId).isPresent()) {
                projectsService.saveDevelopers(developerService.findById(developerId).get().getId(),
                        projectsDto.getId());
                System.out.println("Developer added.");
                if (!CommandLineReader.readLine("If there is another employee, then enter 'Yes' else 'No'")
                        .equals("Yes")) {
                    break;
                } else {
                    continue;
                }
            } else {
                System.out.println("There is no such developers. Enter id yet");
            }
        }
        return new CommandResponse(true);
    }

    @Override
    public NextCommands nextCommands() {
        return new NextCommands(new ShowMainCommand());
    }
}