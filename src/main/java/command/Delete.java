package command;

import model.dto.*;
import service.*;
import view.View;

import java.util.List;

public class Delete implements Command {
    public static final String DELETE_IN_TABLES = "delete";
    private final View view;
    private final DeveloperService developerService;
    private final SkillsService skillsService;
    private final CustomersService customersService;
    private final CompaniesService companiesService;
    private final ProjectsService projectsService;

    public Delete(View view, DeveloperService developerService, SkillsServiceImpl skillsService,
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
        return (input.equals(DELETE_IN_TABLES));
    }

    @Override
    public void execute() {
        boolean param = true;
        while (param) {
            view.write("Write the name of the table in which you will delete entry: Companies, Customers, Developers, " +
                    "Projects, Skills");
            String table = view.read();
            switch (table) {
                case ("Companies") -> {
                    deleteInCompanies();
                    param = false;
                }
                case ("Customers") -> {
                    deleteInCustomers();
                    param = false;
                }
                case ("Developers") -> {
                    deleteInDeveloper();
                    param = false;
                }
                case ("Projects") -> {
                    deleteInProject();
                    param = false;
                }
                case ("Skills") -> {
                    deleteInSkills();
                    param = false;
                }
                default -> view.write("re-enter");
            }
            view.write(String.format("Enter %s to see all command", Help.HELP));
        }
    }

    private void deleteInSkills() {
        while (true) {
            int id;
            while (true) {
                try {
                    view.write("Enter id of Skills (NameLevel)");
                    id = Integer.parseInt(view.read());
                    break;
                } catch (NumberFormatException ex) {
                    view.write("Invalid value. Use digits");
                }
            }
            if (skillsService.findById(id).isPresent()) {
                developerService.deleteOfIdsSkill(id);
                skillsService.delete(skillsService.findById(id).get());
                break;
            } else {
                view.write("There is no such id in the table. Enter again");
            }
        }
    }

    private void deleteInCustomers() {
        while (true) {
            int id;
            while (true) {
                try {
                    view.write("Enter id of Customer");
                    id = Integer.parseInt(view.read());
                    break;
                } catch (NumberFormatException ex) {
                    view.write("Invalid value. Use digits");
                }
            }
            if (customersService.findById(id).isPresent()) {
                int finalId = id;
                List<ProjectsDto> projectsDtos = projectsService.findAll().stream()
                        .filter(el -> el.getCustomerId() == finalId).toList();
                for (ProjectsDto element : projectsDtos) {
                    projectsService.deleteOfIdsProject(element.getId());
                    projectsService.delete(element);
                }
                customersService.delete(customersService.findById(id).get());
                break;
            } else {
                view.write("There is no such id in the table. Enter again");
            }
        }
    }

    private void deleteInProject() {
        while (true) {
            int id;
            while (true) {
                try {
                    view.write("Enter id of Project");
                    id = Integer.parseInt(view.read());
                    break;
                } catch (NumberFormatException ex) {
                    view.write("Invalid value. Use digits");
                }
            }
            if (projectsService.findById(id).isPresent()) {
                projectsService.deleteOfIdsProject(id);
                projectsService.delete(projectsService.findById(id).get());
                break;
            } else {
                view.write("There is no such id in the table. Enter again");
            }
        }
    }

    private void deleteInDeveloper() {
        while (true) {
            int id;
            while (true) {
                try {
                    view.write("Enter id of Developer");
                    id = Integer.parseInt(view.read());
                    break;
                } catch (NumberFormatException ex) {
                    view.write("Invalid value. Use digits");
                }
            }
            if (developerService.findById(id).isPresent()) {
                projectsService.deleteOfIdsDeveloper(id);
                developerService.deleteOfIdsDeveloper(id);
                developerService.delete(developerService.findById(id).get());
                break;
            } else {
                view.write("There is no such id in the table. Enter again");
            }
        }
    }

    private void deleteInCompanies() {
        while (true) {
            int id;
            while (true) {
                try {
                    view.write("Enter id of Companies");
                    id = Integer.parseInt(view.read());
                    break;
                } catch (NumberFormatException ex) {
                    view.write("Invalid value. Use digits");
                }
            }
            if (companiesService.findById(id).isPresent()) {
                int finalId = id;
                List<ProjectsDto> projectsDtos = projectsService.findAll().stream()
                        .filter(el -> el.getCompanyId() == finalId).toList();
                for (ProjectsDto element : projectsDtos) {
                    projectsService.deleteOfIdsProject(element.getId());
                    projectsService.delete(element);
                }
                companiesService.delete(companiesService.findById(id).get());
                break;
            } else {
                view.write("There is no such id in the table. Enter again");
            }
        }
    }
}
