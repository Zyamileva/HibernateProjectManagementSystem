package command.developers;

import command.*;
import command.general.ShowMainCommand;
import config.DataBaseManagerConnector;
import repository.DevelopersRepository;
import service.DeveloperServiceImpl;
import service.converter.DeveloperConverter;

import java.sql.Connection;

import static utils.StringUtils.NEW_LINE;
import static utils.StringUtils.SEPARATOR_SHORT;

public class FindAllDeveloper extends Command {
    public FindAllDeveloper() {
        super(CommandEnum.FIND_ALL_DEVELOPER);
    }

    Connection connector = DataBaseManagerConnector.getInstance().getConnector();
    DeveloperConverter developerConverter = new DeveloperConverter();
    DevelopersRepository developersRepository = new DevelopersRepository(connector);
    DeveloperServiceImpl developerService = new DeveloperServiceImpl(developersRepository, developerConverter);

    @Override
    public CommandResponse execute() {
        developerService.findAll()
                .forEach(company -> System.out.println(company + NEW_LINE + SEPARATOR_SHORT + NEW_LINE));
        return new CommandResponse(true);
    }
    @Override
    public NextCommands nextCommands() {
        return new NextCommands(new ShowMainCommand());
    }
}