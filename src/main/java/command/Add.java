package command;

import model.dto.*;
import service.*;
import view.View;

import java.util.List;

public class Add implements Command {
    public static final String ADD_TO_TABLES = "add";
    private final View view;
    private final DeveloperService developerService;
    private final SkillsService skillsService;
    private final CustomersService customersService;
    private final CompaniesService companiesService;
    private final ProjectsService projectsService;
    private final List<String> nameSkills;
    private final List<String> levelSkills;

    public Add(View view, DeveloperService developerService, SkillsServiceImpl skillsService,
               CompaniesService companiesService, CustomersService customersService, ProjectsService projectsService) {
        this.view = view;
        this.developerService = developerService;
        this.skillsService = skillsService;
        this.companiesService = companiesService;
        this.customersService = customersService;
        this.projectsService = projectsService;
        List<SkillsDto> skills = this.skillsService.findAll();
        this.nameSkills = skills.stream().map(SkillsDto::getName).toList();
        this.levelSkills = skills.stream().map(SkillsDto::getLevel).toList();
    }

    @Override
    public boolean canExecute(String input) {
        return (input.equals(ADD_TO_TABLES));
    }

    @Override
    public void execute() {
        boolean param = true;
        while (param) {
            view.write("Write the name of the table in which you will make a record: Companies, Customers, Developers, " +
                    "Projects, Skills");
            String table = view.read();
            switch (table) {
                case ("Companies") -> {
                    addCompanies();
                    param = false;
                }
                case ("Customers") -> {
                    addCustomers();
                    param = false;
                }
                case ("Developers") -> {
                    addDeveloper();
                    param = false;
                }
                case ("Projects") -> {
                    addProject();
                    param = false;
                }
                case ("Skills") -> {
                    addSkills();
                    param = false;
                }
                default -> view.write("re-enter");
            }
        }
        view.write(String.format("Enter %s to see all command", Help.HELP));
    }

    private void addProject() {
        view.write("Enter name of Project");
        String name = view.read();
        view.write("Enter task_difficulty");
        String task = view.read();

        int idCompany;
        while (true) {
            while (true) {
                try {
                    view.write("Enter id Company");
                    idCompany = Integer.parseInt(view.read());
                    break;
                } catch (NumberFormatException ex) {
                    view.write("Invalid value. Use digits");
                }
            }
            if (companiesService.findById(idCompany).isPresent()) {
                break;
            } else {
                view.write("There is no such id in the table. Enter again");
            }
        }

        int idCustomer;
        while (true) {
            while (true) {
                try {
                    view.write("Enter id Customer");
                    idCustomer = Integer.parseInt(view.read());
                    break;
                } catch (NumberFormatException ex) {
                    view.write("Invalid value. Use digits");
                }
            }
            if (customersService.findById(idCustomer).isPresent()) {
                break;
            } else {
                view.write("There is no such id in the table. Enter again");
            }
        }

        int cost;
        while (true) {
            try {
                view.write("Enter cost project");
                cost = Integer.parseInt(view.read());
                break;
            } catch (NumberFormatException ex) {
                view.write("Invalid value. Use digits");
            }
        }

        ProjectsDto projectsDto =
                projectsService.saveProject(new ProjectsDto(name, task, idCustomer, idCompany, cost));

        while (true) {
            view.write("Enter id of Developers in Project");
            String developerId = view.read();
            if (developerService.findById(Integer.parseInt(developerId)).isPresent()) {
                projectsService.saveDevelopers(developerService.findById(Integer.parseInt(developerId)).get().getId(),
                        projectsDto.getId());
                view.write("Developer added.");
                view.write("If there is another employee, then enter 'Yes' else 'No'");
                if (!view.read().equals("Yes")) {
                    break;
                } else {
                    continue;
                }
            } else {
                view.write("There is no such developers. Enter id yet");
            }
        }
    }

    private void addSkills() {
        view.write("Enter name of Skills");
        String name = view.read();
        view.write("Enter level of Skills");
        String level = view.read();
        skillsService.saveSkill(new SkillsDto(name, level));
        view.write("Skill added.");
    }

    private void addCustomers() {
        view.write("Enter name of Customers");
        String name = view.read();
        view.write("Enter contact Person");
        String contactPerson = view.read();
        view.write("Enter phone");
        String phone = view.read();
        customersService.saveCustomer(new CustomersDto(name, contactPerson, phone));
        view.write("Customers added.");
    }

    private void addCompanies() {
        view.write("Enter name of Companies");
        String name = view.read();
        int staff = 0;
        while (true) {
            try {
                view.write("Enter staff");
                staff = Integer.parseInt(view.read());
                break;
            } catch (NumberFormatException ex) {
                view.write("Invalid value. Use digits");
            }
        }
        companiesService.saveCompanies(new CompaniesDto(name, staff));
        view.write("Companies added.");
    }

    private void addDeveloper() {
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

        DevelopersDto developersDto =
                developerService.saveDeveloper(new DevelopersDto(first_name, last_name, email, phone_number, salary));

        while (true) {
            view.write("Enter name of skills:");
            view.write(nameSkills.stream().distinct().toList().toString());
            String skill = view.read();
            if (nameSkills.stream().anyMatch(element -> element.equals(skill))) {
                while (true) {
                    view.write("Enter level of skills:");
                    List<SkillsDto> all = skillsService.findAll();
                    view.write(all.stream().filter(el -> el.getName().equals(skill))
                            .map(SkillsDto::getLevel).toList().toString());
                    String level = view.read();
                    if (levelSkills.stream().anyMatch(element -> element.equals(level))) {
                        int idNameLevel = skillsService.findByNameLevel(skill, level);
                        developerService.saveSkills(developersDto.getId(), idNameLevel);
                        break;
                    } else {
                        view.write("Error. Enter level of skills");
                    }
                }
                view.write("If you have else skills enter 'Yes' else 'No'");
                if (!view.read().equals("Yes")) {
                    break;
                }
            } else {
                view.write("Error. Enter name of skills");
            }

        }
        view.write("Developer added.");
    }
}
