package net.splodgebox.armorequip.listeners;

import net.splodgebox.armorequip.enums.ArmorType;
import net.splodgebox.armorequip.events.ArmorEquipEvent;
import net.splodgebox.armorequip.events.ArmorUnequipEvent;
import net.splodgebox.armorequip.utils.ItemUtils;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerItemBreakEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import java.util.Arrays;

public class ArmorListeners implements Listener {

    @EventHandler
    public void onArmorEquip(InventoryClickEvent event) {
        if (event.getInventory().getType() != InventoryType.CRAFTING && event.getInventory().getType() != InventoryType.PLAYER) return;
        if (!(event.getWhoClicked() instanceof Player)) return;

        Player player = (Player) event.getWhoClicked();
        ItemStack currentItem = event.getCurrentItem();
        ItemStack cursor = event.getCursor();

        ArmorEquipEvent armorEquipEvent = null;

        if (event.getSlotType() != InventoryType.SlotType.ARMOR && event.getClick() == ClickType.SHIFT_LEFT) {
            if (!ItemUtils.isValid(currentItem)) return;
            ArmorType armorType = ArmorType.matchType(currentItem);
            if (armorType == null) return;
            if (isShiftClick(event, armorType)) {
                armorEquipEvent = new ArmorEquipEvent(currentItem, player);
            }
        } else if (event.getInventory().getType() == InventoryType.CRAFTING && event.getHotbarButton() != -1) {
            currentItem = event.getClickedInventory().getItem(event.getHotbarButton());
            if (!ItemUtils.isValid(currentItem)) return;
            armorEquipEvent = new ArmorEquipEvent(currentItem, player);
        } else if (event.getSlotType() == InventoryType.SlotType.ARMOR) {
            if (!ItemUtils.isValid(cursor)) return;
            armorEquipEvent = new ArmorEquipEvent(cursor, player);
        }

        if (armorEquipEvent != null) {
            Bukkit.getServer().getPluginManager().callEvent(armorEquipEvent);

            if (armorEquipEvent.isCancelled()) {
                event.setCancelled(true);
            }
        }
    }

    @EventHandler
    public void onArmorUnequip(InventoryClickEvent event) {
        if (!(event.getWhoClicked() instanceof Player)) return;
        ItemStack currentItem = event.getCurrentItem();
        ArmorUnequipEvent armorEquipEvent;

        if (event.getSlotType() != InventoryType.SlotType.ARMOR) return;
        if (!ItemUtils.isValid(currentItem)) return;
        armorEquipEvent = new ArmorUnequipEvent(currentItem, (Player) event.getWhoClicked());

        Bukkit.getServer().getPluginManager().callEvent(armorEquipEvent);

        if (armorEquipEvent.isCancelled()) {
            event.setCancelled(true);
        }
    }

    @EventHandler
    public void onBreak(PlayerItemBreakEvent event) {
        Player player = event.getPlayer();
        ItemStack itemStack = event.getBrokenItem();
        if (!ItemUtils.isValid(itemStack)) return;

        ArmorType type = ArmorType.matchType(itemStack);
        if (type == null) return;
        ArmorUnequipEvent armorUnequipEvent = new ArmorUnequipEvent(itemStack, player);
        Bukkit.getServer().getPluginManager().callEvent(armorUnequipEvent);
    }

    @EventHandler
    public void onDeath(PlayerDeathEvent event) {
        Player player = event.getEntity();
        Arrays.stream(player.getInventory().getArmorContents())
                .filter(ItemUtils::isValid)
                .map(itemStack -> new ArmorUnequipEvent(itemStack, player))
                .forEach(armorUnequipEvent -> Bukkit.getServer().getPluginManager().callEvent(armorUnequipEvent));
    }

    @EventHandler
    public void onInteract(PlayerInteractEvent event) {
        if (event.getAction() != Action.RIGHT_CLICK_AIR && event.getAction() != Action.RIGHT_CLICK_BLOCK) return;

        Player player = event.getPlayer();
        ItemStack itemStack = event.getItem();
        if (!ItemUtils.isValid(itemStack)) return;

        ArmorType armorType = ArmorType.matchType(itemStack);
        if (armorType == null) return;

        ItemStack armor = getArmor(player, armorType);
        if (ItemUtils.isValid(armor)) {
            ArmorEquipEvent armorEquipEvent = new ArmorEquipEvent(itemStack, player);
            Bukkit.getServer().getPluginManager().callEvent(armorEquipEvent);

            ArmorUnequipEvent armorUnequipEvent = new ArmorUnequipEvent(armor, player);
            Bukkit.getServer().getPluginManager().callEvent(armorUnequipEvent);
        } else {
            ArmorEquipEvent armorEquipEvent = new ArmorEquipEvent(itemStack, player);
            Bukkit.getServer().getPluginManager().callEvent(armorEquipEvent);
        }
    }

    public boolean isShiftClick(InventoryClickEvent event, ArmorType armorType) {
        Inventory inventory = event.getInventory();
        return !ItemUtils.isValid(inventory.getItem(armorType.getSlot()));
    }

    public ItemStack getArmor(Player player, ArmorType armorType) {
        if (armorType == ArmorType.HELMET) {
            return player.getInventory().getHelmet();
        } else if (armorType == ArmorType.CHESTPLATE) {
            return player.getInventory().getChestplate();
        } else if (armorType == ArmorType.LEGGINGS) {
            return player.getInventory().getLeggings();
        } else if (armorType == ArmorType.BOOTS) {
            return player.getInventory().getBoots();
        }
        return null;
    }

}
