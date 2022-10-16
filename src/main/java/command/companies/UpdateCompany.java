package command.companies;

import command.*;
import command.general.ShowMainCommand;
import config.DataBaseManagerConnector;
import model.dto.CompaniesDto;
import repository.CompaniesRepository;
import service.CompaniesServiceImpl;
import service.converter.CompaniesConverter;

import java.sql.Connection;

public class UpdateCompany extends Command {
    public UpdateCompany() {
        super(CommandEnum.UPDATE_COMPANY);
    }

    Connection connector = DataBaseManagerConnector.getInstance().getConnector();
    CompaniesConverter companiesConverter = new CompaniesConverter();
    CompaniesRepository companiesRepository = new CompaniesRepository(connector);
    CompaniesServiceImpl companiesService = new CompaniesServiceImpl(companiesRepository, companiesConverter);

    @Override
    public CommandResponse execute() {
        while (true) {
            int id = CommandLineReader.readInt("Enter id where you want to change");
            if (companiesService.findById(id).isPresent()) {
                String newName = CommandLineReader.readLine("Enter new name of Companies");
                int newStaff = CommandLineReader.readInt("Enter new staff");
                CompaniesDto companiesDto = companiesService.findById(id).get();
                companiesDto.setName(newName);
                companiesDto.setStaff(newStaff);
                companiesService.update(companiesDto);
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