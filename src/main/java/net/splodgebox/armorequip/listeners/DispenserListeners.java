package net.splodgebox.armorequip.listeners;

import net.splodgebox.armorequip.enums.ArmorType;
import net.splodgebox.armorequip.events.ArmorEquipEvent;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockDispenseArmorEvent;

public class DispenserListeners implements Listener {

    @EventHandler
    public void onDispenser(BlockDispenseArmorEvent event) {
        ArmorType type = ArmorType.matchType(event.getItem());
        if (type == null) return;
        if (!(event.getTargetEntity() instanceof Player)) return;

        Player player = (Player) event.getTargetEntity();
        ArmorEquipEvent armorEquipEvent = new ArmorEquipEvent(event.getItem(), player);
        Bukkit.getServer().getPluginManager().callEvent(armorEquipEvent);

        if (armorEquipEvent.isCancelled()) {
            event.setCancelled(true);
        }
    }

}
