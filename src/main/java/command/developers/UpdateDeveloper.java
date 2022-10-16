package command.developers;

import command.*;
import command.general.ShowMainCommand;
import config.DataBaseManagerConnector;
import model.dto.DevelopersDto;
import repository.DevelopersRepository;
import service.DeveloperServiceImpl;
import service.converter.DeveloperConverter;

import java.sql.Connection;

public class UpdateDeveloper extends Command {
    public UpdateDeveloper() {
        super(CommandEnum.UPDATE_DEVELOPER);
    }

    Connection connector = DataBaseManagerConnector.getInstance().getConnector();
    DeveloperConverter developerConverter = new DeveloperConverter();
    DevelopersRepository developersRepository = new DevelopersRepository(connector);
    DeveloperServiceImpl developerService = new DeveloperServiceImpl(developersRepository, developerConverter);

    @Override
    public CommandResponse execute() {
        while (true) {
            int id = CommandLineReader.readInt("Enter id where you want to change");
            if (developerService.findById(id).isPresent()) {
                String newFirstName = CommandLineReader.readLine("Enter new developer first name");
                String newLastName = CommandLineReader.readLine("Enter new developer last name");
                String newEmail = CommandLineReader.readLine("Enter new developer email");
                String newPhoneNumber = CommandLineReader.readLine("Enter new developer phone_number");
                int newSalary = CommandLineReader.readInt("Enter new developer salary");
                DevelopersDto developersDto = developerService.findById(id).get();
                developersDto.setFirstName(newFirstName);
                developersDto.setLastName(newLastName);
                developersDto.setEmail(newEmail);
                developersDto.setSalary(newSalary);
                developersDto.setPhoneNumber(newPhoneNumber);
                developerService.update(developersDto);
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