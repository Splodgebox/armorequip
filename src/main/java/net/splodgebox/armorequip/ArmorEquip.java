package net.splodgebox.armorequip;

import net.splodgebox.armorequip.listeners.ArmorListeners;
import net.splodgebox.armorequip.listeners.DispenserListeners;
import org.bukkit.Bukkit;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

public final class ArmorEquip extends JavaPlugin {

    @Override
    public void onEnable() {
        registerListeners(this);
    }

    public static void registerListeners(Plugin plugin) {
        plugin.getServer().getPluginManager().registerEvents(new ArmorListeners(), plugin);

        String serverVersion = Bukkit.getVersion();
        if (isVersion1_13OrAbove(serverVersion)) {
            plugin.getServer().getPluginManager().registerEvents(new DispenserListeners(), plugin);
        }
    }

    private static boolean isVersion1_13OrAbove(String version) {
        String[] versionParts = version.split(" ")[1].split("\\.");
        int majorVersion = Integer.parseInt(versionParts[1]);
        return majorVersion >= 13;
    }

}
