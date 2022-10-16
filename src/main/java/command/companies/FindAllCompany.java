package command.companies;

import command.*;
import command.general.ShowMainCommand;
import config.DataBaseManagerConnector;
import repository.CompaniesRepository;
import service.CompaniesServiceImpl;
import service.converter.CompaniesConverter;

import java.sql.Connection;

import static utils.StringUtils.NEW_LINE;
import static utils.StringUtils.SEPARATOR_SHORT;

public class FindAllCompany extends Command {
    public FindAllCompany() {
        super(CommandEnum.FIND_ALL_COMPANY);
    }

    Connection connector = DataBaseManagerConnector.getInstance().getConnector();
    CompaniesConverter companiesConverter = new CompaniesConverter();
    CompaniesRepository companiesRepository = new CompaniesRepository(connector);
    CompaniesServiceImpl companiesService = new CompaniesServiceImpl(companiesRepository, companiesConverter);

    @Override
    public CommandResponse execute() {
        companiesService.findAll()
                .forEach(company -> System.out.println(company + NEW_LINE + SEPARATOR_SHORT + NEW_LINE));
        return new CommandResponse(true);
    }

    @Override
    public NextCommands nextCommands() {
        return new NextCommands(new ShowMainCommand());
    }
}