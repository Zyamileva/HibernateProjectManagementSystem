package command.general;

import command.Command;
import command.CommandEnum;
import command.CommandResponse;
import command.NextCommands;
import command.customers.AddCustomer;
import command.customers.DeleteCustomer;
import command.customers.SelectCustomer;
import command.customers.UpdateCustomer;

public class CustomerCommand extends Command {

    public CustomerCommand() {
        super(CommandEnum.CUSTOMER_TABLE_QUERIES);
    }

    @Override
    public CommandResponse execute() {
        return new CommandResponse(true);
    }

    @Override
    public NextCommands nextCommands() {
        return new NextCommands(new AddCustomer(), new DeleteCustomer(), new SelectCustomer(), new UpdateCustomer(),
        new ExitCommand());
    }
}