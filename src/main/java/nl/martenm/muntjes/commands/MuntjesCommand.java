package nl.martenm.muntjes.commands;

import nl.martenm.muntjes.commands.sub.MuntjesAddCommand;
import nl.martenm.muntjes.commands.sub.MuntjesLijstCommand;
import nl.martenm.muntjes.commands.sub.MuntjesReloadCommand;
import nl.martenm.muntjes.commands.sub.MuntjesTakeCommand;
import nl.martenm.simplecommands.RootCommand;

public class MuntjesCommand extends RootCommand {

    public MuntjesCommand() {
        super("muntjes", "De muntjes command.", "muntjes", false);
        addCommand(new MuntjesAddCommand());
        addCommand(new MuntjesTakeCommand());
        addCommand(new MuntjesLijstCommand());
        addCommand(new MuntjesReloadCommand());
    }
}
