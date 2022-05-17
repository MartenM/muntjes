package nl.martenm.muntjes.commands;

import nl.martenm.muntjes.commands.sub.*;
import nl.martenm.simplecommands.RootCommand;

public class MuntjesCommand extends RootCommand {

    public MuntjesCommand() {
        super("muntjes", "De muntjes command.", "muntjes", false);
        addCommand(new MuntjesAddCommand());
        addCommand(new MuntjesTakeCommand());
        addCommand(new MuntjesLijstCommand());
        addCommand(new MuntjesTotaalCommand());
        addCommand(new MuntjesReloadCommand());
    }
}
