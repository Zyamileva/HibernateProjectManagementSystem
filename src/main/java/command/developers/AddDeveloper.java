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

public class AddDeveloper extends Command {
    public AddDeveloper() {
        super(CommandEnum.ADD_DEVELOPER);
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
        String firstName = CommandLineReader.readLine("Enter developer first name");
        String lastName = CommandLineReader.readLine("Enter developer last name");
        String email = CommandLineReader.readLine("Enter developer email");
        String phoneNumber = CommandLineReader.readLine("Enter developer phone_number");
        int salary = CommandLineReader.readInt("Enter developer salary");
        DevelopersDto developersDto =
                developerService.saveDeveloper(new DevelopersDto(firstName, lastName, email, phoneNumber, salary));
        while (true) {
            String skill = CommandLineReader.readLine("Enter name of skills:" + nameSkills.stream().distinct().toList().toString());
            if (nameSkills.stream().anyMatch(element -> element.equals(skill))) {
                while (true) {
                    System.out.println("Enter level of skills:");
                    List<SkillsDto> all = skillsService.findAll();
                    String level = CommandLineReader.readLine(all.stream().filter(el -> el.getName().equals(skill))
                            .map(SkillsDto::getLevel).toList().toString());
                    if (levelSkills.stream().anyMatch(element -> element.equals(level))) {
                        int idNameLevel = skillsService.findByNameLevel(skill, level);
                        developerService.saveSkills(developersDto.getId(), idNameLevel);
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
        System.out.println("Developer added.");
        return new CommandResponse(true);
    }

    @Override
    public NextCommands nextCommands() {
        return new NextCommands(new ShowMainCommand());
    }
}