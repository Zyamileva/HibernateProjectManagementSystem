package command.general;

import command.Command;
import command.CommandEnum;
import command.CommandResponse;
import command.NextCommands;
import command.skills.*;

public class SkillCommand extends Command {

    public SkillCommand() {
        super(CommandEnum.SKILL_TABLE_QUERIES);
    }

    @Override
    public CommandResponse execute() {
        return new CommandResponse(true);
    }

    @Override
    public NextCommands nextCommands() {
        return new NextCommands(new AddSkill(), new DeleteSkill(), new SelectSkill(), new UpdateSkill(),
                new FindAllSkill(), new ExitCommand());
    }
}