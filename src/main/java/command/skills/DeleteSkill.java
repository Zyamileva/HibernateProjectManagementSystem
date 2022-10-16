package command.skills;

import command.*;
import command.general.ShowMainCommand;
import config.DataBaseManagerConnector;
import repository.DevelopersRepository;
import repository.SkillsRepository;
import service.DeveloperServiceImpl;
import service.SkillsServiceImpl;
import service.converter.DeveloperConverter;
import service.converter.SkillsConverter;

import java.sql.Connection;

public class DeleteSkill extends Command {
    Connection connector = DataBaseManagerConnector.getInstance().getConnector();

    DeveloperConverter developerConverter = new DeveloperConverter();
    DevelopersRepository developersRepository = new DevelopersRepository(connector);
    DeveloperServiceImpl developerService = new DeveloperServiceImpl(developersRepository, developerConverter);
    SkillsConverter skillsConverter = new SkillsConverter();
    SkillsRepository skillsRepository = new SkillsRepository(connector);
    SkillsServiceImpl skillsService = new SkillsServiceImpl(skillsRepository, skillsConverter);


    public DeleteSkill() {
        super(CommandEnum.DELETE_SKILL);
    }

    @Override
    public CommandResponse execute() {
        while (true) {
            int id = CommandLineReader.readInt("Enter id of Skills");
            if (skillsService.findById(id).isPresent()) {
                developerService.deleteDevelopersOfIdsSkill(id);
                skillsService.delete(skillsService.findById(id).get());
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