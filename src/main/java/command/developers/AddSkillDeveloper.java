package command.developers;

import command.*;
import command.general.ShowMainCommand;
import config.DataBaseManagerConnector;
import model.dto.SkillsDto;
import repository.DevelopersRepository;
import repository.SkillsRepository;
import service.DeveloperServiceImpl;
import service.SkillsServiceImpl;
import service.converter.DeveloperConverter;
import service.converter.SkillsConverter;

import java.sql.Connection;
import java.util.List;

public class AddSkillDeveloper extends Command {
    public AddSkillDeveloper() {
        super(CommandEnum.ADD_SKILL_DEVELOPER);
    }

    Connection connector = DataBaseManagerConnector.getInstance().getConnector();
    DeveloperConverter developerConverter = new DeveloperConverter();
    DevelopersRepository developersRepository = new DevelopersRepository(connector);
    DeveloperServiceImpl developerService = new DeveloperServiceImpl(developersRepository, developerConverter);
    SkillsRepository skillsRepository = new SkillsRepository(connector);
    SkillsConverter skillsConverter = new SkillsConverter();
    SkillsServiceImpl skillsService = new SkillsServiceImpl(skillsRepository, skillsConverter);

    @Override
    public CommandResponse execute() {
        List<SkillsDto> skills = this.skillsService.findAll();
        List<String> nameSkills = skills.stream().map(SkillsDto::getName).toList();
        List<String> levelSkills = skills.stream().map(SkillsDto::getLevel).toList();
        while (true) {
            int id = CommandLineReader.readInt("Enter id where you want add skill");
            if (developerService.findById(id).isPresent()) {
                while (true) {
                    String skill = CommandLineReader.readLine("Enter name of skills:" +
                            nameSkills.stream().distinct().toList().toString());
                    if (nameSkills.stream().anyMatch(element -> element.equals(skill))) {
                        while (true) {
                            System.out.println("Enter level of skills:");
                            List<SkillsDto> all = skillsService.findAll();
                            String level = CommandLineReader.readLine(all.stream().filter(el -> el.getName().equals(skill))
                                    .map(SkillsDto::getLevel).toList().toString());
                            if (levelSkills.stream().anyMatch(element -> element.equals(level))) {
                                int idNameLevel = skillsService.findByNameLevel(skill, level);
                                developerService.saveSkills(id, idNameLevel);
                                break;
                            } else {
                                System.out.println("Error. Enter level of skills");
                            }
                        }
                        if (!CommandLineReader.readLine("If you have else skills enter 'Yes' else 'No'").equals("Yes")) {
                            break;
                        }
                    } else {
                        System.out.println("Error. Enter name of skills");
                    }
                }
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