package command.customers;

import command.*;
import command.general.ShowMainCommand;
import config.DataBaseManagerConnector;
import model.dto.CustomersDto;
import repository.CustomersRepository;
import service.CustomersServiceImpl;
import service.converter.CustomersConverter;

import java.sql.Connection;

public class AddCustomer extends Command {
    public AddCustomer() {
        super(CommandEnum.ADD_CUSTOMER);
    }

    Connection connector = DataBaseManagerConnector.getInstance().getConnector();
    CustomersConverter customersConverter = new CustomersConverter();
    CustomersRepository customersRepository = new CustomersRepository(connector);
    CustomersServiceImpl customersService = new CustomersServiceImpl(customersRepository, customersConverter);

    @Override
    public CommandResponse execute() {
        String name = CommandLineReader.readLine("Enter name of Customers");
        String contactPerson = CommandLineReader.readLine("Enter contact Person");
        String phone = CommandLineReader.readLine("Enter phone");
        customersService.saveCustomer(new CustomersDto(name, contactPerson, phone));
        System.out.println("Customer added.");
        return new CommandResponse(true);
    }

    @Override
    public NextCommands nextCommands() {
        return new NextCommands(new ShowMainCommand());
    }
}