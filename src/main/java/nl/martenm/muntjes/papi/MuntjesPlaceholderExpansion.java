package nl.martenm.muntjes.papi;

import me.clip.placeholderapi.expansion.PlaceholderExpansion;
import nl.martenm.muntjes.Muntjes;
import nl.martenm.muntjes.objects.MuntjesPlayer;
import org.bukkit.ChatColor;
import org.bukkit.OfflinePlayer;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Optional;

public class MuntjesPlaceholderExpansion extends PlaceholderExpansion {

    private Muntjes plugin;
    public MuntjesPlaceholderExpansion(Muntjes plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean persist() {
        return true;
    }

    @Override
    public @NotNull String getIdentifier() {
        return "muntjes";
    }

    @Override
    public @NotNull String getAuthor() {
        return "MartenM";
    }

    @Override
    public @NotNull String getVersion() {
        return "1.0.0";
    }

    @Override
    public @Nullable String onRequest(OfflinePlayer player, @NotNull String params) {
        Optional<MuntjesPlayer> tempPlayer = plugin.getMuntjesManager().getPlayer(player.getUniqueId());
        if(!tempPlayer.isPresent()) return ChatColor.RED + "No account";
        MuntjesPlayer mPlayer = tempPlayer.get();

        if(params.equalsIgnoreCase("total")) {
            return mPlayer.getTotalMuntjes() + "";
        }
        if(params.equalsIgnoreCase("current")) {
            return mPlayer.getMuntjes() + "";
        }

        return null;
    }
}
