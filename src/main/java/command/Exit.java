package command;

import exceptions.ExitException;
import view.View;

public class Exit implements Command {
    public static final String EXIT = "exit";
    private final View view;

    public Exit(View view) {
        this.view = view;
    }

    @Override
    public boolean canExecute(String input) {
        return (input.equals(EXIT));
    }

    @Override
    public void execute() {
        view.write("Buy!");
        throw new ExitException();
    }
}
