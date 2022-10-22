package controller.general;

import controller.Command;
import controller.CommandEnum;
import controller.CommandResponse;
import controller.NextCommands;
import controller.projects.*;

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