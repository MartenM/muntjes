package nl.martenm.muntjes.listeners;

import me.clip.placeholderapi.PlaceholderAPI;
import nl.martenm.muntjes.Muntjes;
import nl.martenm.muntjes.objects.MuntjesPlayer;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import java.util.Optional;

public class PlayerJoinListener implements Listener {

    private Muntjes plugin;

    public PlayerJoinListener(Muntjes plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        Optional<MuntjesPlayer> temp = plugin.getMuntjesManager().getPlayer(event.getPlayer().getUniqueId());

        MuntjesPlayer player = null;
        if(!temp.isPresent()) {
            player = this.plugin.getMuntjesManager().createPlayer(event.getPlayer().getUniqueId(), event.getPlayer().getName(), event.getPlayer().getPlayerListName());
        } else {
            player = temp.get();
        }

        String nickName = PlaceholderAPI.setPlaceholders(event.getPlayer(), "%essentials_nickname%");
        player.setNickName(nickName);
    }
}
