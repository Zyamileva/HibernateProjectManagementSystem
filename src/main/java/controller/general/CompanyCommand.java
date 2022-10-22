package controller.general;

import controller.Command;
import controller.CommandEnum;
import controller.CommandResponse;
import controller.NextCommands;
import controller.companies.*;

public class CompanyCommand extends Command {

    public CompanyCommand() {
        super(CommandEnum.COMPANY_TABLE_QUERIES);
    }

    @Override
    public CommandResponse execute() {
        return new CommandResponse(true);
    }

    @Override
    public NextCommands nextCommands() {
        return new NextCommands(               new ExitCommand());
    }
}