package nl.martenm.muntjes.commands.sub;

import nl.martenm.muntjes.commands.args.SimpleArgumentMuntjesPlayer;
import nl.martenm.muntjes.objects.MuntjesPlayer;
import nl.martenm.simplecommands.arguments.SimpleParsedCommand;
import nl.martenm.simplecommands.arguments.prefab.SimpleArgumentInteger;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;

import java.util.List;

public class MuntjesAddCommand extends SimpleParsedCommand {

    public MuntjesAddCommand() {
        super("add", "Voeg muntjes toe.", "+add", false);
        addArgument(new SimpleArgumentMuntjesPlayer("speler"));
        addArgument(new SimpleArgumentInteger("aantal"));
    }

    @Override
    protected boolean onArgumentCommand(CommandSender sender, Command command, String s, String[] strings, List<Object> objects) {
        MuntjesPlayer player = (MuntjesPlayer) objects.get(0);
        int amount = (int) objects.get(1);

        if(amount <= 0) {
            sender.sendMessage(ChatColor.RED + "Je kunt geen negatieve muntjes toevoegen....");
            return true;
        }
        player.addMuntjes(amount);
        sender.sendMessage(ChatColor.GREEN + "Muntjes zijn toegevoegd");
        return true;
    }
}
