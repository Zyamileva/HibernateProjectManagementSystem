package command;

import model.dto.DevelopersDto;
import service.DeveloperService;
import view.View;

import java.util.List;

public class LestOfDevelopersSkillLevel implements Command {
    public static final String LIST_OF_SKILL_LEVEL_DEVELOPERS = "query_4";
    private View view;
    DeveloperService developersService;
    private List<String> levelSkills;

    public LestOfDevelopersSkillLevel(View view, DeveloperService developersService, List<String> levelSkills) {
        this.view = view;
        this.developersService = developersService;
        this.levelSkills = levelSkills;
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
                List<DevelopersDto> developers = developersService.listOfJavaDevelopers();
                developers.stream().forEach(System.out::println);
                break;
            }
        }
    }
}
