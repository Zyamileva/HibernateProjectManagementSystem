package command;

import model.dto.DevelopersDto;
import service.DeveloperService;
import view.View;

import java.util.List;

public class ListOfDeveloperSkillName implements Command {
    public static final String LIST_OF_SKILL_DEVELOPERS = "query_3";
    private View view;
    DeveloperService developersService;
    private List<String> nameSkills;

    public ListOfDeveloperSkillName(View view, DeveloperService developersService, List<String> nameSkills) {
        this.view = view;
        this.developersService = developersService;
        this.nameSkills = nameSkills;
    }

    @Override
    public boolean canExecute(String input) {
        return (input.equals(LIST_OF_SKILL_DEVELOPERS));
    }

    @Override
    public void execute() {
        view.write("Enter name of skills: Java, C++, C#, JS");
        while (true) {
            String skill = view.read();
            if (nameSkills.stream().anyMatch(element -> element.equals(skill))) {
                List<DevelopersDto> developers = developersService.listOfJavaDevelopers();
                developers.stream().forEach(System.out::println);
                break;
            }
        }
    }
}
