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

        if (isVersion130OrAbove()) {
            plugin.getServer().getPluginManager().registerEvents(new DispenserListeners(), plugin);
        }
    }

    private static boolean isVersion130OrAbove() {
        String version = Bukkit.getBukkitVersion();
        String[] versionParts = version.split("\\.");

        int major = Integer.parseInt(versionParts[0]);
        int minor = Integer.parseInt(versionParts[1]);

        return major > 1 || (major == 1 && minor >= 30);
    }

}
