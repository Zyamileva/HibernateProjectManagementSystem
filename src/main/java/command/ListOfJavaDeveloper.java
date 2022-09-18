package command;

import model.dto.DevelopersDto;
import model.dto.ProjectsDto;
import service.DeveloperService;
import service.DeveloperServiceImpl;
import service.ProjectsServiceImpl;
import view.View;

import java.util.List;

public class ListOfJavaDeveloper implements Command {
    public static final String LIST_OF_JAVA_DEVELOPERS = "query_3";
    private View view;
    DeveloperService developersService;

    public ListOfJavaDeveloper(View view, DeveloperService developersService) {
        this.view = view;
        this.developersService = developersService;
    }

    @Override
    public boolean canExecute(String input) {
        return (input.equals(LIST_OF_JAVA_DEVELOPERS));
    }

    @Override
    public void execute() {
        List<DevelopersDto> developers = developersService.listOfJavaDevelopers();
        developers.stream().forEach(System.out::println);
    }
}
