package command;

import view.View;

public class Help implements Command {
    public static final String HELP = "help";
    private View view;

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
        view.write(String.format("Enter %s to add developer", AddDeveloper.ADD_DEVELOPER));
        view.write(String.format("Enter %s to get the salary (sum) of all project developers",
                SalleryOfProjects.SALLERY_OF_PROJECTS));
        view.write(String.format("Enter %s to get List Developers of Project", ListDevelopersOfProjects.LIST_DEVELOPERS_OF_PROJECTS));
        view.write(String.format("Enter %s to get List JAVA Developers", ListOfJavaDeveloper.LIST_OF_JAVA_DEVELOPERS));
        view.write(String.format("Enter %s to get List MIDDLE Developers", LestOfMiddleDevelopers.LIST_OF_MIDDLE_DEVELOPERS));
        view.write(String.format("Enter %s to get List of Projects", ListOfProjects.LIST_OF_PROJECTS));
    }
}
