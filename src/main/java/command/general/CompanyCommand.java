package command.general;

import command.Command;
import command.CommandEnum;
import command.CommandResponse;
import command.NextCommands;
import command.companies.AddCompany;
import command.companies.DeleteCompany;
import command.companies.SelectCompany;
import command.companies.UpdateCompany;

public class CompanyCommand extends Command {

    public CompanyCommand() {
        super(CommandEnum.COMPANY_TABLE_QUERIES);
    }

    @Override
    public CommandResponse execute() {
        return new CommandResponse(true);
    }

    @Override
    public NextCommands nextCommands() {
        return new NextCommands(new AddCompany(), new DeleteCompany(),new SelectCompany(),new UpdateCompany(),
                new ExitCommand());
    }
}