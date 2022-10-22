package controller.general;

import controller.Command;
import controller.CommandEnum;
import controller.CommandResponse;
import controller.NextCommands;

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