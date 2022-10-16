package command.customers;

import command.*;
import command.general.ShowMainCommand;
import config.DataBaseManagerConnector;
import model.dto.CustomersDto;
import repository.CustomersRepository;
import service.CustomersServiceImpl;
import service.converter.CustomersConverter;

import java.sql.Connection;

public class UpdateCustomer extends Command {
    public UpdateCustomer() {
        super(CommandEnum.UPDATE_CUSTOMER);
    }

    Connection connector = DataBaseManagerConnector.getInstance().getConnector();
    CustomersConverter customersConverter = new CustomersConverter();
    CustomersRepository customersRepository = new CustomersRepository(connector);
    CustomersServiceImpl customersService = new CustomersServiceImpl(customersRepository, customersConverter);

    @Override
    public CommandResponse execute() {
        while (true) {
            int id = CommandLineReader.readInt("Enter id where you want to change");
            if (customersService.findById(id).isPresent()) {
                String newName = CommandLineReader.readLine("Enter new name of Customers");
                String newContactPerson = CommandLineReader.readLine("Enter new contact Person");
                String newPhone = CommandLineReader.readLine("Enter new phone");
                CustomersDto customersDto = customersService.findById(id).get();
                customersDto.setName(newName);
                customersDto.setContactPerson(newContactPerson);
                customersDto.setPhoneNumber(newPhone);
                customersService.update(customersDto);
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