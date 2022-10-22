package controller.general;

import controller.Command;
import controller.CommandEnum;
import controller.CommandResponse;
import controller.NextCommands;

public class ShowMainCommand extends Command {

    public ShowMainCommand() {
        super(CommandEnum.MAIN_COMMANDS);
    }

    @Override
    public CommandResponse execute() {
        return new CommandResponse(true);
    }

    @Override
    public NextCommands nextCommands() {
        return new NextCommands(new CompanyCommand(), new CustomerCommand(), new ProjectCommand(),
                new DeveloperCommand(), new SkillCommand(), new ExitCommand());
    }
}