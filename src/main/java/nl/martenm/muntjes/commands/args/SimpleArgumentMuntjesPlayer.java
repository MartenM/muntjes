package nl.martenm.muntjes.commands.args;

import nl.martenm.muntjes.Muntjes;
import nl.martenm.muntjes.objects.MuntjesPlayer;
import nl.martenm.simplecommands.arguments.ParseFailedException;
import nl.martenm.simplecommands.arguments.SimpleCommandArgument;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class SimpleArgumentMuntjesPlayer extends SimpleCommandArgument<MuntjesPlayer> {

    public SimpleArgumentMuntjesPlayer(String name) {
        super(name);
    }

    @Override
    protected MuntjesPlayer parseArgument(String s) throws ParseFailedException {
        Muntjes plugin = Muntjes.getInstance();
        Optional<MuntjesPlayer> player = plugin.getMuntjesManager().getPlayer(s);

        if(player.isEmpty()) throw new ParseFailedException("Die speler bestaat niet!");
        return player.get();
    }

    @Override
    public List<String> onTabCompletion(String input) {
        Muntjes plugin = Muntjes.getInstance();
        return plugin.getMuntjesManager().getPlayerNames().stream().filter(name -> name.startsWith(input)).collect(Collectors.toList());
    }
}
