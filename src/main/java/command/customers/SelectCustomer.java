package command.customers;

import command.*;
import command.general.ShowMainCommand;
import config.DataBaseManagerConnector;
import repository.CustomersRepository;
import service.CustomersServiceImpl;
import service.converter.CustomersConverter;

import java.sql.Connection;

public class SelectCustomer extends Command {
    Connection connector = DataBaseManagerConnector.getInstance().getConnector();
    CustomersConverter customersConverter = new CustomersConverter();
    CustomersRepository customersRepository = new CustomersRepository(connector);
    CustomersServiceImpl customersService = new CustomersServiceImpl(customersRepository, customersConverter);

    public SelectCustomer() {
        super(CommandEnum.SELECT_CUSTOMER);
    }

    @Override
    public CommandResponse execute() {
        while (true) {
            int id = CommandLineReader.readInt("Enter id by which you want to get the record");
            if (customersService.findById(id).isPresent()) {
                System.out.println(customersService.findById(id).get().toString());
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