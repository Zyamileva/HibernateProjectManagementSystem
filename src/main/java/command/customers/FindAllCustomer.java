package command.customers;

import command.*;
import command.general.ShowMainCommand;
import config.DataBaseManagerConnector;
import repository.CustomersRepository;
import service.CustomersServiceImpl;
import service.converter.CustomersConverter;

import java.sql.Connection;

import static utils.StringUtils.NEW_LINE;
import static utils.StringUtils.SEPARATOR_SHORT;

public class FindAllCustomer extends Command {
    public FindAllCustomer() {
        super(CommandEnum.FIND_ALL_CUSTOMER);
    }

    Connection connector = DataBaseManagerConnector.getInstance().getConnector();
    CustomersConverter customersConverter = new CustomersConverter();
    CustomersRepository customersRepository = new CustomersRepository(connector);
    CustomersServiceImpl customersService = new CustomersServiceImpl(customersRepository, customersConverter);

    @Override
    public CommandResponse execute() {
        customersService.findAll()
                .forEach(company -> System.out.println(company + NEW_LINE + SEPARATOR_SHORT + NEW_LINE));
        return new CommandResponse(true);
    }

    @Override
    public NextCommands nextCommands() {
        return new NextCommands(new ShowMainCommand());
    }
}