package command;

import service.*;
import view.View;

public class Select implements Command {
    public static final String SELECT_IN_TABLES = "select";
    private final View view;
    private final DeveloperService developerService;
    private final SkillsService skillsService;
    private final CustomersService customersService;
    private final CompaniesService companiesService;
    private final ProjectsService projectsService;

    public Select(View view, DeveloperService developerService, SkillsServiceImpl skillsService,
                  CompaniesService companiesService, CustomersService customersService, ProjectsService projectsService) {
        this.view = view;
        this.developerService = developerService;
        this.skillsService = skillsService;
        this.companiesService = companiesService;
        this.customersService = customersService;
        this.projectsService = projectsService;
    }

    @Override
    public boolean canExecute(String input) {
        return (input.equals(SELECT_IN_TABLES));
    }

    @Override
    public void execute() {
        boolean param = true;
        while (param) {
            view.write("Write the name of the table in which you will find record: Companies, Customers, Developers, " +
                    "Projects, Skills");
            String table = view.read();
            switch (table) {
                case ("Companies") -> {
                    selectInCompanies();
                    param = false;
                }
                case ("Customers") -> {
                    selectInCustomers();
                    param = false;
                }
                case ("Developers") -> {
                    selectInDeveloper();
                    param = false;
                }
                case ("Projects") -> {
                    selectInProject();
                    param = false;
                }
                case ("Skills") -> {
                    selectInSkills();
                    param = false;
                }
                default -> view.write("re-enter");
            }
            view.write(String.format("Enter %s to see all command", Help.HELP));
        }
    }

    private void selectInCustomers() {
        while (true) {
            int id;
            while (true) {
                try {
                    view.write("Enter id by which you want to get the record");
                    id = Integer.parseInt(view.read());
                    break;
                } catch (NumberFormatException ex) {
                    view.write("Invalid value. Use digits");
                }
            }
            if (customersService.findById(id).isPresent()) {
                view.write(customersService.findById(id).get().toString());
                break;
            } else {
                view.write("There is no such id in the table. Enter again");
            }
        }
    }

    private void selectInProject() {
        while (true) {
            int id;
            while (true) {
                try {
                    view.write("Enter id by which you want to get the record");
                    id = Integer.parseInt(view.read());
                    break;
                } catch (NumberFormatException ex) {
                    view.write("Invalid value. Use digits");
                }
            }
            if (projectsService.findById(id).isPresent()) {
                view.write(projectsService.findById(id).get().toString());
                break;
            } else {
                view.write("There is no such id in the table. Enter again");
            }
        }
    }

    private void selectInSkills() {
        while (true) {
            int id;
            while (true) {
                try {
                    view.write("Enter id by which you want to get the record");
                    id = Integer.parseInt(view.read());
                    break;
                } catch (NumberFormatException ex) {
                    view.write("Invalid value. Use digits");
                }
            }
            if (skillsService.findById(id).isPresent()) {
                view.write(skillsService.findById(id).get().toString());
                break;
            } else {
                view.write("There is no such id in the table. Enter again");
            }
        }
    }

    private void selectInCompanies() {
        while (true) {
            int id;
            while (true) {
                try {
                    view.write("Enter id by which you want to get the record");
                    id = Integer.parseInt(view.read());
                    break;
                } catch (NumberFormatException ex) {
                    view.write("Invalid value. Use digits");
                }
            }
            if (companiesService.findById(id).isPresent()) {
                view.write(companiesService.findById(id).get().toString());
                break;
            } else {
                view.write("There is no such id in the table. Enter again");
            }
        }
    }

    private void selectInDeveloper() {
        while (true) {
            int id;
            while (true) {
                try {
                    view.write("Enter id by which you want to get the record");
                    id = Integer.parseInt(view.read());
                    break;
                } catch (NumberFormatException ex) {
                    view.write("Invalid value. Use digits");
                }
            }
            if (developerService.findById(id).isPresent()) {
                view.write(developerService.findById(id).get().toString());
                break;
            } else {
                view.write("There is no such id in the table. Enter again");
            }
        }
    }
}