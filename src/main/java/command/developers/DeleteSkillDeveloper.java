package command.developers;

import command.*;
import command.general.ShowMainCommand;
import config.DataBaseManagerConnector;
import repository.DevelopersRepository;
import service.DeveloperServiceImpl;
import service.converter.DeveloperConverter;

import java.sql.Connection;

public class DeleteSkillDeveloper extends Command {
    public DeleteSkillDeveloper() {
        super(CommandEnum.DELETE_SKILL_DEVELOPER);
    }

    Connection connector = DataBaseManagerConnector.getInstance().getConnector();
    DeveloperConverter developerConverter = new DeveloperConverter();
    DevelopersRepository developersRepository = new DevelopersRepository(connector);
    DeveloperServiceImpl developerService = new DeveloperServiceImpl(developersRepository, developerConverter);

    @Override
    public CommandResponse execute() {
        while (true) {
            int id = CommandLineReader.readInt("Enter id where you want delete skills");
            if (developerService.findById(id).isPresent()) {
                developerService.deleteSkillsOfDeveloper(id);
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