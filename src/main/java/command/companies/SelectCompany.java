package command.companies;

import command.*;
import command.general.ShowMainCommand;
import config.DataBaseManagerConnector;
import repository.CompaniesRepository;
import service.CompaniesServiceImpl;
import service.converter.CompaniesConverter;

import java.sql.Connection;

public class SelectCompany extends Command {
    public SelectCompany() {
        super(CommandEnum.SELECT_COMPANY);
    }

    Connection connector = DataBaseManagerConnector.getInstance().getConnector();
    CompaniesConverter companiesConverter = new CompaniesConverter();
    CompaniesRepository companiesRepository = new CompaniesRepository(connector);
    CompaniesServiceImpl companiesService = new CompaniesServiceImpl(companiesRepository, companiesConverter);

    @Override
    public CommandResponse execute() {
        while (true) {
            int id = CommandLineReader.readInt("Enter id by which you want to get the record");
            if (companiesService.findById(id).isPresent()) {
                System.out.println(companiesService.findById(id).get().toString());
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