package nl.martenm.muntjes.manager;

import nl.martenm.muntjes.objects.MuntjesPlayer;
import nl.martenm.muntjes.util.Config;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.serialization.ConfigurationSerialization;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

public class MuntjesManager {

    private JavaPlugin plugin;

    private Config saveFile;
    private List<MuntjesPlayer> muntjesPlayers = new ArrayList<>();

    public MuntjesManager(JavaPlugin plugin) {
        this.plugin = plugin;
        this.saveFile = new Config(plugin, "muntjes-save");
    }

    public MuntjesPlayer createPlayer(UUID uuid, String name, String nickName) {
        if(hasProfile(uuid)) return null;
        MuntjesPlayer player = new MuntjesPlayer(uuid, name, nickName, 0);
        player.addOnChange(onPlayerChange(player));

        muntjesPlayers.add(player);
        return player;
    }

    public boolean hasProfile(UUID uuid) {
        return muntjesPlayers.stream().anyMatch(player -> player.getUuid().equals(uuid));
    }

    public Optional<MuntjesPlayer> getPlayer(String name) {
        return muntjesPlayers.stream().filter(player -> player.getName().equalsIgnoreCase(name)).findFirst();
    }

    public Optional<MuntjesPlayer> getPlayer(UUID uuid) {
        return muntjesPlayers.stream().filter(player -> player.getUuid().equals(uuid)).findFirst();
    }

    public List<String> getPlayerNames() {
        return muntjesPlayers.stream().map(MuntjesPlayer::getName).collect(Collectors.toList());
    }

    public List<MuntjesPlayer> getSortedPlayers() {
        muntjesPlayers.sort((a, b) -> a.getMuntjes() > b.getMuntjes() ? -1 : 1);
        return muntjesPlayers;
    }

    public void loadData() {
        // Clear just in case.
        this.muntjesPlayers.clear();

        this.plugin.getLogger().info("Muntjes worden geladen...");

        ConfigurationSection section = saveFile.getConfigurationSection("players");
        if(section == null) return;

        for(String key : section.getKeys(false)) {
            MuntjesPlayer player = (MuntjesPlayer) section.get(key);
            assert player != null;
            player.addOnChange(onPlayerChange(player));

            muntjesPlayers.add(player);
        }
    }

    private Runnable onPlayerChange(MuntjesPlayer player) {
        return this::saveData;
    }

    public void saveData() {
        this.plugin.getLogger().info("Muntjes worden opgeslagen...");
        for(MuntjesPlayer player : muntjesPlayers) {
            saveFile.set("players." + player.getUuid().toString(), player);
        }
        saveFile.save();
    }
}
