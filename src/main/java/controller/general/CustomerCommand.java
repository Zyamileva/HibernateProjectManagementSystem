package controller.general;

import controller.Command;
import controller.CommandEnum;
import controller.CommandResponse;
import controller.NextCommands;
import controller.customers.*;

public class CustomerCommand extends Command {

    public CustomerCommand() {
        super(CommandEnum.CUSTOMER_TABLE_QUERIES);
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