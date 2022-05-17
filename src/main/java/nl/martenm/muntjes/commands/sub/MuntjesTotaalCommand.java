package nl.martenm.muntjes.commands.sub;

import nl.martenm.muntjes.Muntjes;
import nl.martenm.muntjes.objects.MuntjesPlayer;
import nl.martenm.simplecommands.SimpleCommand;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public class MuntjesTotaalCommand extends SimpleCommand {
    public MuntjesTotaalCommand() {
        super("totaal", "Een lijst met het totaal aantal muntjes verkregen van elke persoon.", "+totaal", false);
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String s, String[] strings) {
        Muntjes plugin = Muntjes.getInstance();
        List<MuntjesPlayer> list = plugin.getMuntjesManager().getSortedTotal();

        sender.sendMessage(" ");
        sender.sendMessage(ChatColor.GOLD + "  Totaal " + ChatColor.GRAY + "aantal muntjes");
        sender.sendMessage(" ");
        int index = 1;
        for(MuntjesPlayer player : list) {
            sender.sendMessage(ChatColor.GRAY + "#" + index + formatPlayer(player));
            index++;
        }
        sender.sendMessage(" ");
        return true;
    }

    private static String formatPlayer(MuntjesPlayer player) {
        return "  " + ChatColor.RESET + player.getNickName() + " " + ChatColor.GRAY + "- " + ChatColor.YELLOW + player.getTotalMuntjes();
    }
}
