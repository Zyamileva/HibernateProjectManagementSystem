package command;

import java.io.IOException;
import java.text.ParseException;

public abstract class Command {
    protected final CommandEnum command;

    protected Command(CommandEnum command) {
        this.command = command;
    }

    public abstract CommandResponse execute();

    public abstract NextCommands nextCommands();

    public String toString() {
        return command.getValue();
    }
}
