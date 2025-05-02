package net.splodgebox.armorequip.enums;

import lombok.Getter;
import org.bukkit.Bukkit;

@Getter
public enum MinecraftVersion {
    UNKNOWN(Integer.MAX_VALUE),

    // Minecraft 1.8
    MC1_8_R3(183),

    // Minecraft 1.9
    MC1_9_R1(191),
    MC1_9_R2(192),

    // Minecraft 1.10
    MC1_10_R1(1101),

    // Minecraft 1.11
    MC1_11_R1(1111),

    // Minecraft 1.12
    MC1_12_R1(1121),

    // Minecraft 1.13
    MC1_13_R1(1131),
    MC1_13_R2(1132),

    // Minecraft 1.14
    MC1_14_R1(1141),

    // Minecraft 1.15
    MC1_15_R1(1151),

    // Minecraft 1.16
    MC1_16_R1(1161),
    MC1_16_R2(1162),
    MC1_16_R3(1163),

    // Minecraft 1.17
    MC1_17_R1(1171),

    // Minecraft 1.18
    MC1_18_R1(1181),
    MC1_18_R2(1182),

    // Minecraft 1.19
    MC1_19_R1(1191),
    MC1_19_R2(1192),

    // Minecraft 1.20
    MC1_20_R1(1201),
    MC1_20_R2(1202),
    MC1_20_R3(1203),

    // Minecraft 1.21
    MC1_21_R1(1211),
    MC1_21_R1_1(1212); // Represents version 1.21.1

    private static MinecraftVersion version;
    private final int versionId;

    MinecraftVersion(int versionId) {
        this.versionId = versionId;
    }

    public static MinecraftVersion getVersion() {
        if (version != null) {
            return version;
        } else {
            String ver = Bukkit.getServer().getClass().getPackage().getName().split("\\.")[3];
            try {
                version = valueOf(ver.replace("v", "MC"));
            } catch (IllegalArgumentException var2) {
                version = UNKNOWN;
            }

            return version;
        }
    }
}
