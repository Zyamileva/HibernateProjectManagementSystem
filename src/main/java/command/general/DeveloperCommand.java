package command.general;

import command.Command;
import command.CommandEnum;
import command.CommandResponse;
import command.NextCommands;
import command.developers.*;

public class DeveloperCommand extends Command {

    public DeveloperCommand() {
        super(CommandEnum.DEVELOPER_TABLE_QUERIES);
    }

    @Override
    public CommandResponse execute() {
        return new CommandResponse(true);
    }

    @Override
    public NextCommands nextCommands() {
        return new NextCommands(new AddDeveloper(), new DeleteDeveloper(),new SelectDeveloper(),new UpdateDeveloper(),
                new FindAllDeveloper(),new ListDevelopersOfProjects(), new ListOfDeveloperSkillName(),
                new ListOfDevelopersSkillLevel(), new AddSkillDeveloper(), new DeleteSkillDeveloper(), new ExitCommand());
    }
}