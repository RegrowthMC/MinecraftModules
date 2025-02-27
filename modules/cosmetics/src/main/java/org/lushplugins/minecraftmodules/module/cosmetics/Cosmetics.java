package org.lushplugins.minecraftmodules.module.cosmetics;

import org.lushplugins.minecraftmodules.module.cosmetics.command.GiveCosmeticCommand;
import org.lushplugins.minecraftmodules.common.module.Module;
import org.lushplugins.minecraftmodules.common.plugin.RootPlugin;
import org.lushplugins.minecraftmodules.module.cosmetics.config.ConfigManager;

public final class Cosmetics extends Module {
    private static Cosmetics instance;

    private final ConfigManager configManager;

    public Cosmetics(RootPlugin plugin) {
        super("cosmetics", plugin);

        if (instance == null) {
            instance = this;
        }

        this.configManager = new ConfigManager();

        plugin.registerCommand(new GiveCosmeticCommand());
    }

    @Override
    public void onEnable() {
        configManager.reloadConfig();
    }

    public ConfigManager getConfigManager() {
        return configManager;
    }

    public static Cosmetics getInstance() {
        return instance;
    }
}
