package nl.martenm.muntjes.listeners;

import nl.martenm.muntjes.Muntjes;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class PlayerJoinListener implements Listener {

    private Muntjes plugin;

    public PlayerJoinListener(Muntjes plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        if(this.plugin.getMuntjesManager().hasProfile(event.getPlayer().getUniqueId())) return;
        this.plugin.getMuntjesManager().createPlayer(event.getPlayer().getUniqueId(), event.getPlayer().getName(), event.getPlayer().getPlayerListName());
    }
}
