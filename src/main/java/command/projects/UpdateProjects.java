package command.projects;

import command.*;
import command.general.ShowMainCommand;
import config.DataBaseManagerConnector;
import model.dto.ProjectsDto;
import repository.CompaniesRepository;
import repository.CustomersRepository;
import repository.ProjectsRepository;
import service.CompaniesServiceImpl;
import service.CustomersServiceImpl;
import service.ProjectsServiceImpl;
import service.converter.CompaniesConverter;
import service.converter.CustomersConverter;
import service.converter.DeveloperConverter;
import service.converter.ProjectsConverter;

import java.sql.Connection;

public class UpdateProjects extends Command {
    public UpdateProjects() {
        super(CommandEnum.UPDATE_PROJECT);
    }

    Connection connector = DataBaseManagerConnector.getInstance().getConnector();
    ProjectsConverter projectsConverter = new ProjectsConverter();
    DeveloperConverter developerConverter = new DeveloperConverter();
    CompaniesConverter companiesConverter = new CompaniesConverter();
    ProjectsRepository projectsRepository = new ProjectsRepository(connector);
    CompaniesRepository companiesRepository = new CompaniesRepository(connector);
    CompaniesServiceImpl companiesService = new CompaniesServiceImpl(companiesRepository, companiesConverter);
    ProjectsServiceImpl projectsService = new ProjectsServiceImpl(projectsRepository, developerConverter, projectsConverter);
    CustomersConverter customersConverter = new CustomersConverter();
    CustomersRepository customersRepository = new CustomersRepository(connector);
    CustomersServiceImpl customersService = new CustomersServiceImpl(customersRepository, customersConverter);
    @Override
    public CommandResponse execute() {
        while (true) {
            int id = CommandLineReader.readInt("Enter id where you want to change");
            if (projectsService.findById(id).isPresent()) {
                String newName = CommandLineReader.readLine("Enter new name of Project");
                String newTask = CommandLineReader.readLine("Enter new task_difficulty");
                int newIdCompany;
                while (true) {
                    newIdCompany = CommandLineReader.readInt("Enter new id Company");
                    if (companiesService.findById(newIdCompany).isPresent()) {
                        break;
                    } else {
                        System.out.println("There is no such id in the table. Enter again");
                    }
                }
                int newIdCustomer;
                while (true) {
                    newIdCustomer = CommandLineReader.readInt("Enter new id Customer");
                    if (customersService.findById(newIdCustomer).isPresent()) {
                        break;
                    } else {
                        System.out.println("There is no such id in the table. Enter again");
                    }
                }
                int newCost = CommandLineReader.readInt("Enter new cost project");
                ProjectsDto projects = projectsService.findById(id).get();
                projects.setName(newName);
                projects.setTask_difficulty(newTask);
                projects.setCustomerId(newIdCustomer);
                projects.setCompanyId(newIdCompany);
                projects.setCost(newCost);
                projectsService.update(projects);
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