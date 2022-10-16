package command.companies;

import command.*;
import command.general.ShowMainCommand;
import config.DataBaseManagerConnector;
import model.dto.*;
import repository.CompaniesRepository;
import service.*;
import service.converter.CompaniesConverter;

import java.sql.Connection;

public class AddCompany extends Command {
    public AddCompany() {
        super(CommandEnum.ADD_COMPANY);
    }

    Connection connector = DataBaseManagerConnector.getInstance().getConnector();
    CompaniesConverter companiesConverter = new CompaniesConverter();
    CompaniesRepository companiesRepository = new CompaniesRepository(connector);
    CompaniesServiceImpl companiesService = new CompaniesServiceImpl(companiesRepository, companiesConverter);

    @Override
    public CommandResponse execute() {
        String name = CommandLineReader.readLine("Enter name of Companies");
        int staff = CommandLineReader.readInt("Enter staff");
        companiesService.saveCompanies(new CompaniesDto(name, staff));
        System.out.println("Companies added.");
        return new CommandResponse(true);
    }

    @Override
    public NextCommands nextCommands() {
        return new NextCommands(new ShowMainCommand());
    }
}