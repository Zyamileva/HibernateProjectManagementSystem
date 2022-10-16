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

public class ListOfDeveloperSkillName extends Command {
    Connection connector = DataBaseManagerConnector.getInstance().getConnector();
    DeveloperConverter developerConverter = new DeveloperConverter();
    DevelopersRepository developersRepository = new DevelopersRepository(connector);
    DeveloperServiceImpl developerService = new DeveloperServiceImpl(developersRepository, developerConverter);
    SkillsRepository skillsRepository = new SkillsRepository(connector);
    SkillsConverter skillsConverter = new SkillsConverter();
    SkillsServiceImpl skillsService = new SkillsServiceImpl(skillsRepository, skillsConverter);

    public ListOfDeveloperSkillName() {
        super(CommandEnum.LIST_DEVELOPERS_OF_SKILL_NAME);
    }

    @Override
    public CommandResponse execute() {
        List<SkillsDto> skills = this.skillsService.findAll();
        List<String> nameSkills = skills.stream().map(SkillsDto::getName).toList();
        while (true) {
            String skill = CommandLineReader.readLine("Enter name of skills:" + nameSkills.stream().
                    distinct().toList().toString());
            if (nameSkills.stream().anyMatch(element -> element.equals(skill))) {
                List<DevelopersDto> developers = developerService.listOfSkillNameDevelopers(skill);
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