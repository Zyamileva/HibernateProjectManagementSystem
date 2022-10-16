package command.general;

import command.Command;
import command.CommandEnum;
import command.CommandResponse;
import command.NextCommands;

public class ExitCommand extends Command {

    public ExitCommand() {
        super(CommandEnum.EXIT);
    }

    @Override
    public CommandResponse execute() {
        System.exit(0);
        return null;
    }

    @Override
    public NextCommands nextCommands() {
        return null;
    }
}