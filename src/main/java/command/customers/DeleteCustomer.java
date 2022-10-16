package command.customers;

import command.*;
import command.general.ShowMainCommand;
import config.DataBaseManagerConnector;
import model.dto.ProjectsDto;
import repository.CustomersRepository;
import repository.ProjectsRepository;
import service.CustomersServiceImpl;
import service.ProjectsServiceImpl;
import service.converter.CustomersConverter;
import service.converter.DeveloperConverter;
import service.converter.ProjectsConverter;

import java.sql.Connection;
import java.util.List;

public class DeleteCustomer extends Command {
    Connection connector = DataBaseManagerConnector.getInstance().getConnector();
    CustomersConverter customersConverter = new CustomersConverter();
    CustomersRepository customersRepository = new CustomersRepository(connector);
    CustomersServiceImpl customersService = new CustomersServiceImpl(customersRepository, customersConverter);
    ProjectsConverter projectsConverter = new ProjectsConverter();
    DeveloperConverter developerConverter = new DeveloperConverter();
    ProjectsRepository projectsRepository = new ProjectsRepository(connector);
    ProjectsServiceImpl projectsService = new ProjectsServiceImpl(projectsRepository, developerConverter, projectsConverter);

    public DeleteCustomer() {
        super(CommandEnum.DELETE_CUSTOMER);
    }

    @Override
    public CommandResponse execute() {
        while (true) {
            int id = CommandLineReader.readInt("Enter id of Customer");
            if (customersService.findById(id).isPresent()) {
                int finalId = id;
                List<ProjectsDto> projectsDtos = projectsService.findAll().stream()
                        .filter(el -> el.getCustomerId() == finalId).toList();
                for (ProjectsDto element : projectsDtos) {
                    projectsService.deleteOfIdsProject(element.getId());
                    projectsService.delete(element);
                }
                customersService.delete(customersService.findById(id).get());
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