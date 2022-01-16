package com.peepersoak.autocraftingvmc;

import org.bukkit.ChatColor;
import org.bukkit.inventory.ItemStack;
import org.bukkit.util.io.BukkitObjectInputStream;
import org.bukkit.util.io.BukkitObjectOutputStream;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Base64;

public class Utils {

    public static Object deserialized(String data) {
        try {
            byte[] raw = Base64.getDecoder().decode(data);

            ByteArrayInputStream is = new ByteArrayInputStream(raw);
            BukkitObjectInputStream bs = new BukkitObjectInputStream(is);

            return bs.readObject();
        } catch (IOException | ClassNotFoundException e) {
            AutoCraftingVMC.getInstance().getLogger().warning("Failed to deserialized data!");
        }

        return null;
    }

    public static String serialized(ItemStack itemStack) {
        try {
            ByteArrayOutputStream os = new ByteArrayOutputStream();
            BukkitObjectOutputStream bs = new BukkitObjectOutputStream(os);

            bs.writeObject(itemStack);
            bs.flush();

            byte[] data = os.toByteArray();

            return Base64.getEncoder().encodeToString(data);
        } catch (IOException e) {
            AutoCraftingVMC.getInstance().getLogger().warning("Failed to serialized data!");
        }

        return null;
    }

    public static String color(String msg) {
        return ChatColor.translateAlternateColorCodes('&', msg);
    }
}
