package controller.skills;

import controller.*;
import controller.general.ShowMainCommand;
import config.DataBaseManagerConnector;
import repository.SkillsRepository;
import service.SkillsServiceImpl;
import service.converter.SkillsConverter;

import java.sql.Connection;

public class SelectSkill extends Command {
    Connection connector = DataBaseManagerConnector.getInstance().getConnector();
    SkillsConverter skillsConverter = new SkillsConverter();
    SkillsRepository skillsRepository = new SkillsRepository(connector);
    SkillsServiceImpl skillsService = new SkillsServiceImpl(skillsRepository, skillsConverter);

    public SelectSkill() {
        super(CommandEnum.SELECT_SKILL);
    }

    @Override
    public CommandResponse execute() {
//        while (true) {
//            int id = CommandLineReader.readInt("Enter id by which you want to get the record");
//            if (skillsService.findById(id).isPresent()) {
//                System.out.println(skillsService.findById(id).get().toString());
//                break;
//            } else {
//                System.out.println("There is no such id in the table. Enter again");
//            }
//        }
        return new CommandResponse(true);
    }

    @Override
    public NextCommands nextCommands() {
        return new NextCommands(new ShowMainCommand());
    }
}