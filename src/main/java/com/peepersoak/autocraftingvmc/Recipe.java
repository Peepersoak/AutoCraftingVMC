package com.peepersoak.autocraftingvmc;

import org.bukkit.Bukkit;
import org.bukkit.NamespacedKey;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.RecipeChoice;
import org.bukkit.inventory.ShapedRecipe;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Recipe {

    public Recipe() {
        config = AutoCraftingVMC.getInstance().getData().getConfig();
        data = AutoCraftingVMC.getInstance().getData();
        init();
        addCustomRecipe();
    }

    private final Data data;
    private final FileConfiguration config;

    private final HashMap<String, ItemStack> names = new HashMap<>();
    private final HashMap<ItemStack, List<ItemStack>> recipe = new HashMap<>();

    public void init() {
        for (String key : config.getKeys(false)) {
            String resultData = config.getString(key + "." + StringPath.RESULT);
            List<String> recipeData = config.getStringList(key + "." + StringPath.RECIPES);
            generateItem(resultData, recipeData,key);
        }

    }

    public void saveData() {
        for (String name : names.keySet()) {
            ItemStack result = names.get(name);
            List<ItemStack> recipe = this.recipe.get(result);
            saveItem(result, recipe, name);
        }
    }

    public void addCustomRecipe() {
        for (String name : names.keySet()) {
            ItemStack item = names.get(name);
            List<ItemStack> itemStackList = recipe.get(item);

            ShapedRecipe recipe = new ShapedRecipe(new NamespacedKey(AutoCraftingVMC.getInstance(), name), item);
            recipe.shape("abc","def","ghi");

            int count = 1;

            for (ItemStack i : itemStackList) {
                switch (count) {
                    case 1 -> recipe.setIngredient('a', new RecipeChoice.ExactChoice(i));
                    case 2 -> recipe.setIngredient('b', new RecipeChoice.ExactChoice(i));
                    case 3 -> recipe.setIngredient('c', new RecipeChoice.ExactChoice(i));
                    case 4 -> recipe.setIngredient('d', new RecipeChoice.ExactChoice(i));
                    case 5 -> recipe.setIngredient('e', new RecipeChoice.ExactChoice(i));
                    case 6 -> recipe.setIngredient('f', new RecipeChoice.ExactChoice(i));
                    case 7 -> recipe.setIngredient('g', new RecipeChoice.ExactChoice(i));
                    case 8 -> recipe.setIngredient('h', new RecipeChoice.ExactChoice(i));
                    case 9 -> recipe.setIngredient('i', new RecipeChoice.ExactChoice(i));
                }
                count++;
            }

            if (Bukkit.getRecipe(new NamespacedKey(AutoCraftingVMC.getInstance(), name)) == null) {
                Bukkit.addRecipe(recipe);
            }
        }
    }

    public void addRecipe(ItemStack item, List<ItemStack> recipe, String name) {
        names.put(name, item);
        this.recipe.put(item, recipe);
    }

    private void generateItem(String item, List<String> recipe, String name) {
        ItemStack result = (ItemStack) Utils.deserialized(item);

        List<ItemStack> recipes = new ArrayList<>();

        for (String str : recipe) {
            ItemStack i = (ItemStack) Utils.deserialized(str);
            if (i == null) continue;
            recipes.add(i);
        }

        this.recipe.put(result, recipes);
        this.names.put(name, result);
    }

    private void saveItem(ItemStack result, List<ItemStack> recipe, String name) {
        String resultData = Utils.serialized(result);

        List<String> recipeData = new ArrayList<>();

        for (ItemStack items : recipe) {
            String i = Utils.serialized(items);
            recipeData.add(i);
        }

        data.writeString(name + "." + StringPath.RESULT, resultData);
        data.writeList(name + "." + StringPath.RECIPES, recipeData);
    }
}
