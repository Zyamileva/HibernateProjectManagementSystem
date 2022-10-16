package command.skills;

import command.*;
import command.general.ShowMainCommand;
import config.DataBaseManagerConnector;
import model.dto.SkillsDto;
import repository.SkillsRepository;
import service.SkillsServiceImpl;
import service.converter.SkillsConverter;

import java.sql.Connection;

public class AddSkill extends Command {
    public AddSkill() {
        super(CommandEnum.ADD_SKILL);
    }

    Connection connector = DataBaseManagerConnector.getInstance().getConnector();
    SkillsConverter skillsConverter = new SkillsConverter();
    SkillsRepository skillsRepository = new SkillsRepository(connector);
    SkillsServiceImpl skillsService = new SkillsServiceImpl(skillsRepository, skillsConverter);

    @Override
    public CommandResponse execute() {
        String name = CommandLineReader.readLine("Enter name of Skills");
        String level = CommandLineReader.readLine("Enter level of Skills");
        skillsService.saveSkill(new SkillsDto(name, level));
        System.out.println("Skill added.");
        return new CommandResponse(true);
    }

    @Override
    public NextCommands nextCommands() {
        return new NextCommands(new ShowMainCommand());
    }
}