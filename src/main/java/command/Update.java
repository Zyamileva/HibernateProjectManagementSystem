package command;

import model.dto.*;
import service.*;
import view.View;

public class Update implements Command {
    public static final String UPDATE_IN_TABLES = "update";
    private final View view;
    private final DeveloperService developerService;
    private final SkillsService skillsService;
    private final CustomersService customersService;
    private final CompaniesService companiesService;
    private final ProjectsService projectsService;

    public Update(View view, DeveloperService developerService, SkillsServiceImpl skillsService,
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
        return (input.equals(UPDATE_IN_TABLES));
    }

    @Override
    public void execute() {
        boolean param = true;
        while (param) {
            view.write("Write the name of the table in which you will update entry: Companies, Customers, Developers, " +
                    "Projects, Skills");
            String table = view.read();
            switch (table) {
                case ("Companies") -> {
                    updateInCompanies();
                    param = false;
                }
                case ("Customers") -> {
                    updateInCustomers();
                    param = false;
                }
                case ("Developers") -> {
                    updateInDeveloper();
                    param = false;
                }
                case ("Projects") -> {
                    updateInProject();
                    param = false;
                }
                case ("Skills") -> {
                    updateInSkills();
                    param = false;
                }
                default -> view.write("re-enter");
            }
            view.write(String.format("Enter %s to see all command", Help.HELP));
        }
    }

    private void updateInDeveloper() {
        while (true) {
            int id;
            while (true) {
                try {
                    view.write("Enter id where you want to change the developers's phone number");
                    id = Integer.parseInt(view.read());
                    break;
                } catch (NumberFormatException ex) {
                    view.write("Invalid value. Use digits");
                }
            }
            if (developerService.findById(id).isPresent()) {
                view.write("Enter new phone number");
                String phoneNumber = view.read();
                DevelopersDto developersDto = developerService.findById(id).get();
                developersDto.setPhoneNumber(phoneNumber);
                developerService.update(developersDto);
                break;
            } else {
                view.write("There is no such id in the table. Enter again");
            }
        }
    }

    private void updateInProject() {
        while (true) {
            int id;
            while (true) {
                try {
                    view.write("Enter id where you want to change the task_difficulty");
                    id = Integer.parseInt(view.read());
                    break;
                } catch (NumberFormatException ex) {
                    view.write("Invalid value. Use digits");
                }
            }
            if (projectsService.findById(id).isPresent()) {
                view.write("Enter new task_difficulty");
                String taskDifficulty = view.read();
                ProjectsDto projectsDto = projectsService.findById(id).get();
                projectsDto.setTask_difficulty(taskDifficulty);
                projectsService.update(projectsDto);
                break;
            } else {
                view.write("There is no such id in the table. Enter again");
            }
        }
    }

    private void updateInSkills() {
        while (true) {
            int id;
            while (true) {
                try {
                    view.write("Enter id where you want to change the level of skill");
                    id = Integer.parseInt(view.read());
                    break;
                } catch (NumberFormatException ex) {
                    view.write("Invalid value. Use digits");
                }
            }
            if (skillsService.findById(id).isPresent()) {
                view.write("Enter new level of skill");
                String newLevelOfSkill = view.read();
                SkillsDto skillsDto = skillsService.findById(id).get();
                skillsDto.setLevel(newLevelOfSkill);
                skillsService.update(skillsDto);
                break;
            } else {
                view.write("There is no such id in the table. Enter again");
            }
        }
    }

    private void updateInCustomers() {
        while (true) {
            int id;
            while (true) {
                try {
                    view.write("Enter id where you want to change the phone of contact person");
                    id = Integer.parseInt(view.read());
                    break;
                } catch (NumberFormatException ex) {
                    view.write("Invalid value. Use digits");
                }
            }
            if (customersService.findById(id).isPresent()) {
                view.write("Enter new phone");
                String newPhone = view.read();
                CustomersDto customersDto = customersService.findById(id).get();
                customersDto.setPhoneNumber(newPhone);
                customersService.update(customersDto);
                break;
            } else {
                view.write("There is no such id in the table. Enter again");
            }
        }
    }

    private void updateInCompanies() {
        while (true) {
            int id;
            while (true) {
                try {
                    view.write("Enter id where you want to change the staff of company");
                    id = Integer.parseInt(view.read());
                    break;
                } catch (NumberFormatException ex) {
                    view.write("Invalid value. Use digits");
                }
            }
            if (companiesService.findById(id).isPresent()) {
                int newStaff;
                while (true) {
                    try {
                        view.write("Enter new staff");
                        newStaff = Integer.parseInt(view.read());
                        break;
                    } catch (NumberFormatException ex) {
                        view.write("Invalid value. Use digits");
                    }
                }
                CompaniesDto companiesDto = companiesService.findById(id).get();
                companiesDto.setStaff(newStaff);
                companiesService.update(companiesDto);
                break;
            } else {
                view.write("There is no such id in the table. Enter again");
            }
        }
    }
}