package command.developers;

import command.*;
import command.general.ShowMainCommand;
import config.DataBaseManagerConnector;
import model.dto.DevelopersDto;
import model.dto.SkillsDto;
import repository.DevelopersRepository;
import repository.SkillsRepository;
import service.DeveloperServiceImpl;
import service.SkillsServiceImpl;
import service.converter.DeveloperConverter;
import service.converter.SkillsConverter;

import java.sql.Connection;
import java.util.List;

public class ListOfDevelopersSkillLevel  extends Command {
    Connection connector = DataBaseManagerConnector.getInstance().getConnector();
    DeveloperConverter developerConverter = new DeveloperConverter();
    DevelopersRepository developersRepository = new DevelopersRepository(connector);
    DeveloperServiceImpl developerService = new DeveloperServiceImpl(developersRepository, developerConverter);
    SkillsRepository skillsRepository = new SkillsRepository(connector);
    SkillsConverter skillsConverter = new SkillsConverter();
    SkillsServiceImpl skillsService = new SkillsServiceImpl(skillsRepository, skillsConverter);

    public ListOfDevelopersSkillLevel() {
        super(CommandEnum.LIST_DEVELOPERS_OF_SKILL_LEVEL);
    }

    @Override
    public CommandResponse execute() {
        List<SkillsDto> skills = this.skillsService.findAll();
        List<String> levelSkills = skills.stream().map(SkillsDto::getLevel).toList();
        while (true) {
            String level = CommandLineReader.readLine("Enter level of skills: Junior, Middle, Senior");
            if (levelSkills.stream().anyMatch(element -> element.equals(level))) {
                List<DevelopersDto> developers = developerService.listOfSkillLevelDevelopers(level);
                developers.forEach(System.out::println);
                break;
            }
        }
        return new CommandResponse(true);
    }

    @Override
    public NextCommands nextCommands() {
        return new NextCommands(new ShowMainCommand());
    }
}