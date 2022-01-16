package com.peepersoak.autocraftingvmc;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

public class Command implements CommandExecutor {


    @Override
    public boolean onCommand(CommandSender sender, org.bukkit.command.Command command, String label, String[] args) {

        if (!(sender instanceof Player player)) return false;
        if (!player.isOp()) return false;

        if (args.length == 2) {
            if (args[0].equalsIgnoreCase("add")) {
                player.openInventory(openGUI(args[1]));
                return false;
            }
        }

        player.sendMessage(Utils.color("&6Usage:"));
        player.sendMessage(Utils.color("&b/customrecipe add [unique name] &e: to add a custom recipe"));

        return false;
    }

    private Inventory openGUI(String name) {
        Inventory inv = Bukkit.createInventory(null, 45, "Recipe Creator | " + name);
        for (int i = 0; i < 45; i++) {
            switch (i) {
                case 10,11,12,19,20,21,28,29,30,24 -> {
                    inv.setItem(i, new ItemStack(Material.AIR));
                }
                default -> inv.setItem(i, new ItemStack(Material.BLACK_STAINED_GLASS_PANE));
            }
        }
        return inv;
    }
}
