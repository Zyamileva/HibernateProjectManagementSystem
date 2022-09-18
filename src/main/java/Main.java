import command.*;
import config.DataBaseManagerConnector;
import controller.Controller;
import repository.*;
import service.*;
import service.converter.DeveloperConverter;
import service.converter.DevelopersSkillsConverter;
import service.converter.ProjectsConverter;
import view.Console;
import view.View;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {


    public static void main(String[] args) {
        String dbUsername = System.getenv("dbUsername");
        String dbPassword = System.getenv("dbPassword");


        Connection connector = DataBaseManagerConnector.getInstance().getConnector();

        Scanner scanner = new Scanner(System.in);
        View view = new Console(scanner);

        DeveloperConverter developerConverter = new DeveloperConverter();
        DevelopersSkillsConverter developersSkillsConverter = new DevelopersSkillsConverter();
        ProjectsConverter projectsConverter = new ProjectsConverter();

        DevelopersRepository developersRepository = new DevelopersRepository(connector);
        SkillsRepository skillsRepository = new SkillsRepository(connector);
        DevelopersSkillsRepository developersSkillsRepository = new DevelopersSkillsRepository(connector);
        CompaniesRepository companiesRepository = new CompaniesRepository(connector);
        ProjectsRepository projectsRepository = new ProjectsRepository(connector);
        DeveloperService developerService = new DeveloperServiceImpl(developersRepository, developerConverter);
        SkillsServiceImpl skillsService = new SkillsServiceImpl(skillsRepository);
        ProjectsServiceImpl projectsService = new ProjectsServiceImpl(projectsRepository, developerConverter, projectsConverter);
        DevelopersSkillsService developersSkillsService = new DevelopersSkillsServiceImpl(developersSkillsRepository,
                developersSkillsConverter);

        List<Command> commands = new ArrayList<>();
        commands.add(new Help(view));
        commands.add(new Exit(view));
        commands.add(new AddDeveloper(view, developerService, skillsService, developersSkillsService));
        commands.add(new SalleryOfProjects(view, projectsService));
        commands.add(new ListDevelopersOfProjects(view, projectsService));
        commands.add(new ListOfJavaDeveloper(view, developerService));
        commands.add(new LestOfMiddleDevelopers(view, developerService));
        commands.add(new ListOfProjects(view, projectsService));

        Controller controller = new Controller(view, commands);

        controller.run();
    }
}