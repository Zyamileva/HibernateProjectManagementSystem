package command.skills;

import command.*;
import command.general.ShowMainCommand;
import config.DataBaseManagerConnector;
import model.dto.SkillsDto;
import repository.SkillsRepository;
import service.SkillsServiceImpl;
import service.converter.SkillsConverter;

import java.sql.Connection;

public class UpdateSkill extends Command {
    public UpdateSkill() {
        super(CommandEnum.UPDATE_SKILL);
    }

    Connection connector = DataBaseManagerConnector.getInstance().getConnector();
    SkillsConverter skillsConverter = new SkillsConverter();
    SkillsRepository skillsRepository = new SkillsRepository(connector);
    SkillsServiceImpl skillsService = new SkillsServiceImpl(skillsRepository, skillsConverter);

    @Override
    public CommandResponse execute() {
        while (true) {
            int id = CommandLineReader.readInt("Enter id where you want to change");
            if (skillsService.findById(id).isPresent()) {
                String newName = CommandLineReader.readLine("Enter new name of Skills");
                String newLevel = CommandLineReader.readLine("Enter new level of Skills");
                SkillsDto skillsDto = skillsService.findById(id).get();
                skillsDto.setLevel(newLevel);
                skillsDto.setName(newName);
                skillsService.update(skillsDto);
                break;
            } else {
                System.out.println("There is no such id in the table. Enter again");
            }
        }
        return new CommandResponse(true);
    }

    @Override
    public NextCommands nextCommands() {
        return new NextCommands(new ShowMainCommand());
    }
}