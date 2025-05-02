package net.splodgebox.armorequip;

import lombok.Getter;
import net.splodgebox.armorequip.listeners.ArmorListeners;
import net.splodgebox.armorequip.listeners.DispenserListeners;
import net.splodgebox.armorequip.enums.MinecraftVersion;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

public final class ArmorEquip extends JavaPlugin {

    @Getter
    private static Plugin instance;

    @Override
    public void onEnable() {
        instance = this;
        registerListeners(this);
    }

    public static void registerListeners(Plugin plugin) {
        instance = plugin;
        plugin.getServer().getPluginManager().registerEvents(new ArmorListeners(), plugin);

        if (MinecraftVersion.getVersion().getVersionId() >= 1131) {
            plugin.getServer().getPluginManager().registerEvents(new DispenserListeners(), plugin);
        }
    }

}
