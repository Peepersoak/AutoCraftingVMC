package com.peepersoak.autocraftingvmc;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Objects;

public final class AutoCraftingVMC extends JavaPlugin {

    private static AutoCraftingVMC instance;
    private Recipe recipe;
    private Data data;

    @Override
    public void onEnable() {
        instance = this;

        this.getConfig().options().copyDefaults();
        saveDefaultConfig();

        data = new Data(StringPath.RECIPE_YML);
        recipe = new Recipe();

        Objects.requireNonNull(getCommand("customrecipe")).setExecutor(new Command());

        Bukkit.getPluginManager().registerEvents(new EventHandlers(), instance);
    }

    @Override
    public void onDisable() {
        recipe.saveData();
    }

    public static AutoCraftingVMC getInstance() {
        return instance;
    }

    public Data getData() {
        return data;
    }

    public Recipe getRecipe() {
        return recipe;
    }
}
