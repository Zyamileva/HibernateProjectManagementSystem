package command.skills;

import command.*;
import command.general.ShowMainCommand;
import config.DataBaseManagerConnector;
import repository.SkillsRepository;
import service.SkillsServiceImpl;
import service.converter.SkillsConverter;

import java.sql.Connection;

import static utils.StringUtils.NEW_LINE;
import static utils.StringUtils.SEPARATOR_SHORT;

public class FindAllSkill extends Command {
    public FindAllSkill() {
        super(CommandEnum.FIND_ALL_SKILL);
    }

    Connection connector = DataBaseManagerConnector.getInstance().getConnector();
    SkillsConverter skillsConverter = new SkillsConverter();
    SkillsRepository skillsRepository = new SkillsRepository(connector);
    SkillsServiceImpl skillsService = new SkillsServiceImpl(skillsRepository, skillsConverter);

    @Override
    public CommandResponse execute() {
        skillsService.findAll()
                .forEach(company -> System.out.println(company + NEW_LINE + SEPARATOR_SHORT + NEW_LINE));
        return new CommandResponse(true);
    }

    @Override
    public NextCommands nextCommands() {
        return new NextCommands(new ShowMainCommand());
    }
}