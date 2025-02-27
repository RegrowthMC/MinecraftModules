package org.lushplugins.minecraftmodules.module.recipes;

import org.lushplugins.minecraftmodules.common.module.Module;
import org.lushplugins.minecraftmodules.common.plugin.RootPlugin;
import org.lushplugins.minecraftmodules.module.recipes.command.RecipesCommand;
import org.lushplugins.minecraftmodules.module.recipes.config.ConfigManager;
import org.lushplugins.minecraftmodules.module.recipes.listener.CraftListener;
import org.lushplugins.minecraftmodules.module.recipes.listener.PlayerListener;

public final class Recipes extends Module {
    private static Recipes instance;

    private final ConfigManager configManager;

    public Recipes(RootPlugin plugin) {
        super("recipes", plugin);

        if (instance == null) {
            instance = this;
        }

        this.configManager = new ConfigManager();

        plugin.registerListeners(
            new CraftListener(),
            new PlayerListener()
        );

        plugin.registerCommand(new RecipesCommand());
    }

    @Override
    public void onEnable() {
        configManager.reloadConfig();
    }

    public ConfigManager getConfigManager() {
        return configManager;
    }

    public static Recipes getInstance() {
        return instance;
    }
}
