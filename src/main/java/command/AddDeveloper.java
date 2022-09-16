package command;

import exceptions.ExitException;
import model.dto.DevelopersDto;
import model.dto.DevelopersSkillsDto;
import repository.SkillsRepository;
import service.*;
import view.View;

import java.util.ArrayList;
import java.util.List;

public class AddDeveloper implements Command {
    public static final String ADD_DEVELOPER = "add_developer";
    private View view;
    private DeveloperService developerService;
    private DevelopersSkillsService developersSkillsService;
    private SkillsServiceImpl skillsService;

    public AddDeveloper(View view, DeveloperService developerService, SkillsServiceImpl skillsService, DevelopersSkillsService developersSkillsService) {
        this.view = view;
        this.developerService = developerService;
        this.skillsService = skillsService;
        this.developersSkillsService = developersSkillsService;
    }

    @Override
    public boolean canExecute(String input) {
        return (input.equals(ADD_DEVELOPER));
    }

    @Override
    public void execute() {
        view.write("Enter developer first name");
        String first_name = view.read();
        view.write("Enter developer last name");
        String last_name = view.read();
        view.write("Enter developer email");
        String email = view.read();
        view.write("Enter developer phone_number");
        String phone_number = view.read();
        int salary = 0;
        while (true) {
            try {
                view.write("Enter developer salary");
                salary = Integer.parseInt(view.read());
                break;
            } catch (NumberFormatException ex) {
                view.write("Invalid value. Use digits");
            }
        }

        List<String> nameSkills = List.of("Java", "C++", "C#", "JS");
        List<String> levelSkills = List.of("Junior", "Middle", "Senior");

        DevelopersDto developersDto =
                developerService.saveDeveloper(new DevelopersDto(first_name, last_name, email, phone_number, salary));

        while (true) {
            view.write("Enter name of skills: Java, C++, C#, JS");
            String skill = view.read();
            if (nameSkills.stream().anyMatch(element -> element.equals(skill))) {
                while (true) {
                    view.write("Enter level of skills: Junior, Middle, Senior");
                    String level = view.read();
                    if (levelSkills.stream().anyMatch(element -> element.equals(level))) {
                        int idNameLevel = skillsService.findByNameLevel(skill, level);
                        developersSkillsService.saveDevelopersSkiils(new DevelopersSkillsDto(developersDto.getId(), idNameLevel));
                        break;
                    } else {
                        view.write("Error. Enter level of skills: Junior, Middle, Senior");
                    }
                }
                view.write("If you have else skills enter 'Yes' else 'No'");
                if (!view.read().equals("Yes")) {
                    break;
                } else {
                    view.write("Error. Enter name of skills: Java, C++, C#, JS");
                }
            }
        }
        view.write("Developer added.");
    }
}
