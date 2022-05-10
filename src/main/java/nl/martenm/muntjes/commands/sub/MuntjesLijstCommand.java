package nl.martenm.muntjes.commands.sub;

import nl.martenm.muntjes.Muntjes;
import nl.martenm.muntjes.objects.MuntjesPlayer;
import nl.martenm.simplecommands.SimpleCommand;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;

import java.util.List;

public class MuntjesLijstCommand extends SimpleCommand {

    public MuntjesLijstCommand() {
        super("lijst", "Laat een lijst zien van alle spelers.", "+lijst", false);
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String s, String[] strings) {
        Muntjes plugin = Muntjes.getInstance();
        List<MuntjesPlayer> list = plugin.getMuntjesManager().getSortedPlayers();

        sender.sendMessage(" ");
        int index = 1;
        for(MuntjesPlayer player : list) {
            sender.sendMessage(ChatColor.GRAY + "#1" + formatPlayer(player));
            index++;
        }
        sender.sendMessage(" ");
        return true;
    }

    private static String formatPlayer(MuntjesPlayer player) {
        return "  " + ChatColor.RESET + player.getName() + " " + ChatColor.GRAY + "- " + ChatColor.YELLOW + player.getMuntjes();
    }
}
