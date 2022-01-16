package com.peepersoak.autocraftingvmc;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.List;

public class EventHandlers implements Listener {

    @EventHandler
    public void onClose(InventoryCloseEvent e) {
        String title = e.getView().getTitle();
        String[] sp = title.split("\\|");

        if (!sp[0].trim().equalsIgnoreCase("Recipe Creator")) return;

        Inventory inv = e.getInventory();
        Player player = (Player) e.getPlayer();

        List<ItemStack> recipe = new ArrayList<>();

        recipe.add(inv.getItem(10));
        recipe.add(inv.getItem(11));
        recipe.add(inv.getItem(12));
        recipe.add(inv.getItem(19));
        recipe.add(inv.getItem(20));
        recipe.add(inv.getItem(21));
        recipe.add(inv.getItem(28));
        recipe.add(inv.getItem(29));
        recipe.add(inv.getItem(30));

        ItemStack result = inv.getItem(24);

        if (result == null || result.getType() == Material.AIR) {
            player.sendMessage(Utils.color("&cFailed to add recipe! No result has been set"));
            return;
        }

        AutoCraftingVMC.getInstance().getRecipe().addRecipe(result, recipe, sp[1].trim());

        player.sendMessage(Utils.color("&bRecipes has been added"));
    }

    @EventHandler
    public void onClick(InventoryClickEvent e) {
        if (e.getClickedInventory() == null) return;

        String title = e.getView().getTitle();
        String[] sp = title.split("\\|");

        if (!sp[0].trim().equalsIgnoreCase("Recipe Creator")) return;

        if (e.getCurrentItem() == null) return;
        if (e.getCurrentItem().getType() == Material.BLACK_STAINED_GLASS_PANE) e.setCancelled(true);
    }
}
