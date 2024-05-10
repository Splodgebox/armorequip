package net.splodgebox.armorequip.enums;

import lombok.Getter;
import net.splodgebox.armorequip.utils.ItemUtils;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

@Getter
public enum ArmorType {

    HELMET(5),
    CHESTPLATE(6),
    LEGGINGS(7),
    BOOTS(8);

    private final int slot;

    ArmorType(int slot) {
        this.slot = slot;
    }

    public static ArmorType matchType(final ItemStack itemStack){
        if(!ItemUtils.isValid(itemStack)) return null;
        Material type = itemStack.getType();
        String typeName = type.name();
        if(typeName.endsWith("_HELMET") || typeName.endsWith("_SKULL") || typeName.endsWith("_HEAD") || typeName.equalsIgnoreCase("CARVED_PUMPKIN")) return HELMET;
        else if(typeName.endsWith("_CHESTPLATE") || typeName.equals("ELYTRA")) return CHESTPLATE;
        else if(typeName.endsWith("_LEGGINGS")) return LEGGINGS;
        else if(typeName.endsWith("_BOOTS")) return BOOTS;
        else return null;
    }
}
