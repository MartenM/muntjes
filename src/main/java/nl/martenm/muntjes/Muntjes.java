package nl.martenm.muntjes;

import nl.martenm.muntjes.commands.MuntjesCommand;
import nl.martenm.muntjes.listeners.PlayerJoinListener;
import nl.martenm.muntjes.manager.MuntjesManager;
import nl.martenm.muntjes.objects.MuntjesPlayer;
import org.bukkit.Bukkit;
import org.bukkit.configuration.serialization.ConfigurationSerialization;
import org.bukkit.plugin.java.JavaPlugin;

public class Muntjes extends JavaPlugin {

    private static Muntjes instance;
    private MuntjesManager muntjesManager;

    @Override
    public void onLoad() {
        instance = this;

        // Load classes.
        ConfigurationSerialization.registerClass(MuntjesPlayer.class);

        this.muntjesManager = new MuntjesManager(this);
        this.muntjesManager.loadData();
        super.onLoad();
    }

    @Override
    public void onEnable() {
        if (Bukkit.getPluginManager().getPlugin("PlaceholderAPI") == null) {
            getLogger().warning("Could not find PlaceholderAPI! This plugin is required.");
            Bukkit.getPluginManager().disablePlugin(this);
        }

        new MuntjesCommand().registerCommand(this);
        getServer().getPluginManager().registerEvents(new PlayerJoinListener(this), this);
        super.onEnable();
    }

    @Override
    public void onDisable() {
        muntjesManager.saveData();

        super.onDisable();
    }

    public MuntjesManager getMuntjesManager() {
        return muntjesManager;
    }

    public static Muntjes getInstance() {
        return instance;
    }
}
