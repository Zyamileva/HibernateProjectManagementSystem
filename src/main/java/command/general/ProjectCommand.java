package command.general;

import command.Command;
import command.CommandEnum;
import command.CommandResponse;
import command.NextCommands;
import command.projects.*;

public class ProjectCommand extends Command {

    public ProjectCommand() {
        super(CommandEnum.PROJECT_TABLE_QUERIES);
    }

    @Override
    public CommandResponse execute() {
        return new CommandResponse(true);
    }

    @Override
    public NextCommands nextCommands() {
        return new NextCommands(new AddProjects(), new DeleteProject(), new SelectProject(), new UpdateProjects(),
                new FindAllProjects(), new ListOfProjects(), new SalaryOfProjects(), new AddDeveloperOfProjects(),
                new DeleteDeveloperOfProjects(), new ExitCommand());
    }
}