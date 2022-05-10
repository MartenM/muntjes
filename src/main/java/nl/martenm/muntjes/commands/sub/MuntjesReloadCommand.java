package nl.martenm.muntjes.commands.sub;

import nl.martenm.muntjes.Muntjes;
import nl.martenm.simplecommands.SimpleCommand;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;

public class MuntjesReloadCommand extends SimpleCommand {

    public MuntjesReloadCommand() {
        super("reload", "Herlaad de gegevens van de save.yml", "+reload", false);
    }

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        Muntjes plugin = Muntjes.getInstance();
        plugin.getMuntjesManager().loadData();
        commandSender.sendMessage(ChatColor.GREEN + "Gegevens zijn opnieuw geladen!");
        return true;
    }
}
