package command;

import view.View;

public class Help implements Command {
    public static final String HELP = "help";
    private final View view;

    public Help(View view) {
        this.view = view;
    }

    @Override
    public boolean canExecute(String input) {
        return input.equals(HELP);
    }

    @Override
    public void execute() {
        view.write(String.format("Enter %s to see all command", Help.HELP));
        view.write(String.format("Enter %s to exit program", Exit.EXIT));
        view.write(String.format("Enter %s to add in Tables", Add.ADD_TO_TABLES));
        view.write(String.format("Enter %s to delete in Tables", Delete.DELETE_IN_TABLES));
        view.write(String.format("Enter %s to update in Tables", Update.UPDATE_IN_TABLES));
        view.write(String.format("Enter %s to select in Tables", Select.SELECT_IN_TABLES));
        view.write(String.format("Enter %s to get the salary (sum) of all project developers",
                SalleryOfProjects.SALLERY_OF_PROJECTS));
        view.write(String.format("Enter %s to get List Developers of Project",
                ListDevelopersOfProjects.LIST_DEVELOPERS_OF_PROJECTS));
        view.write(String.format("Enter %s to get List Developers of the specified programming language", ListOfDeveloperSkillName.LIST_OF_SKILL_DEVELOPERS));
        view.write(String.format("Enter %s to get List Developers of the specified level of knowledge", ListOfDevelopersSkillLevel.LIST_OF_SKILL_LEVEL_DEVELOPERS));
        view.write(String.format("Enter %s to get List of Projects", ListOfProjects.LIST_OF_PROJECTS));
    }
}
