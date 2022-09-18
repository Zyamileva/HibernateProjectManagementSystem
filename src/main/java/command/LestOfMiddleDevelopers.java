package command;

import model.dto.DevelopersDto;
import service.DeveloperService;
import view.View;

import java.util.List;

public class LestOfMiddleDevelopers implements Command {
    public static final String LIST_OF_MIDDLE_DEVELOPERS = "query_4";
    private View view;
    DeveloperService developersService;

    public LestOfMiddleDevelopers(View view, DeveloperService developersService) {
        this.view = view;
        this.developersService = developersService;
    }

    @Override
    public boolean canExecute(String input) {
        return (input.equals(LIST_OF_MIDDLE_DEVELOPERS));
    }

    @Override
    public void execute() {
        List<DevelopersDto> developers = developersService.listOfMiddleDevelopers();
        developers.stream().forEach(System.out::println);
    }
}