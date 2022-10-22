package controller.general;

import controller.Command;
import controller.CommandEnum;
import controller.CommandResponse;
import controller.NextCommands;
import controller.developers.*;

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
        return new NextCommands(new ExitCommand());
    }
}