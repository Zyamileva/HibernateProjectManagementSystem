import command.*;
import config.DataBaseManagerConnector;
import controller.Controller;
import model.dao.SkillsDao;
import repository.*;
import service.*;
import service.converter.*;
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
        ProjectsConverter projectsConverter = new ProjectsConverter();
        SkillsConverter skillsConverter = new SkillsConverter();
        CompaniesConverter companiesConverter = new CompaniesConverter();
        CustomersConverter customersConverter = new CustomersConverter();

        DevelopersRepository developersRepository = new DevelopersRepository(connector);
        SkillsRepository skillsRepository = new SkillsRepository(connector);
        CompaniesRepository companiesRepository = new CompaniesRepository(connector);
        ProjectsRepository projectsRepository = new ProjectsRepository(connector);
        CustomersRepository customersRepository = new CustomersRepository(connector);

        DeveloperService developerService = new DeveloperServiceImpl(developersRepository, developerConverter);
        SkillsServiceImpl skillsService = new SkillsServiceImpl(skillsRepository, skillsConverter);
        ProjectsServiceImpl projectsService = new ProjectsServiceImpl(projectsRepository, developerConverter, projectsConverter);
        CompaniesServiceImpl companiesService = new CompaniesServiceImpl(companiesRepository, companiesConverter);
        CustomersServiceImpl customersService = new CustomersServiceImpl(customersRepository, customersConverter);

        List<SkillsDao> skills = skillsRepository.findAll();
        List<String> nameSkills = skills.stream().map(SkillsDao::getName).toList();
        List<String> levelSkills = skills.stream().map(SkillsDao::getLevel).toList();

        List<Command> commands = new ArrayList<>();
        commands.add(new Help(view));
        commands.add(new Exit(view));
        commands.add(new AddDeveloper(view, developerService, skillsService, nameSkills, levelSkills));
        commands.add(new SalleryOfProjects(view, projectsService));
        commands.add(new ListDevelopersOfProjects(view, projectsService));
        commands.add(new ListOfDeveloperSkillName(view, developerService, nameSkills));
        commands.add(new LestOfDevelopersSkillLevel(view, developerService, levelSkills));
        commands.add(new ListOfProjects(view, projectsService));

        Controller controller = new Controller(view, commands);

        controller.run();
    }
}