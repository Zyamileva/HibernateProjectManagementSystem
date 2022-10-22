package controller.general;

import controller.Command;
import controller.CommandEnum;
import controller.CommandResponse;
import controller.NextCommands;
import controller.skills.*;

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