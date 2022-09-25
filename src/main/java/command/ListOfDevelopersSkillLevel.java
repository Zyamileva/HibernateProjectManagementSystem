package command;

import model.dto.DevelopersDto;
import model.dto.SkillsDto;
import service.DeveloperService;
import service.SkillsService;
import view.View;

import java.util.List;

public class ListOfDevelopersSkillLevel implements Command {
    public static final String LIST_OF_SKILL_LEVEL_DEVELOPERS = "query_4";
    private final View view;
    private final DeveloperService developersService;
    private final List<String> levelSkills;

    public ListOfDevelopersSkillLevel(View view, DeveloperService developersService, SkillsService skillsService) {
        this.view = view;
        this.developersService = developersService;
        List<SkillsDto> skills = skillsService.findAll();
        this.levelSkills = skills.stream().map(SkillsDto::getLevel).toList();
    }

    @Override
    public boolean canExecute(String input) {
        return (input.equals(LIST_OF_SKILL_LEVEL_DEVELOPERS));
    }

    @Override
    public void execute() {
        view.write("Enter level of skills: Junior, Middle, Senior");
        while (true) {
            String level = view.read();
            if (levelSkills.stream().anyMatch(element -> element.equals(level))) {
                List<DevelopersDto> developers = developersService.listOfSkillLevelDevelopers(level);
                developers.forEach(System.out::println);
                view.write(String.format("Enter %s to see all command", Help.HELP));
                break;
            }
        }
    }
}
