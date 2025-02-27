package org.lushplugins.minecraftmodules.module.welcome;

import org.lushplugins.minecraftmodules.module.welcome.config.ConfigManager;
import org.lushplugins.minecraftmodules.module.welcome.listener.PlayerListener;
import org.lushplugins.minecraftmodules.common.module.Module;
import org.lushplugins.minecraftmodules.common.plugin.RootPlugin;

public class Welcome extends Module {
    private static Welcome instance;

    private final ConfigManager configManager;

    public Welcome(RootPlugin plugin) {
        super("welcome", plugin);

        if (instance == null) {
            instance = this;
        }

        this.configManager = new ConfigManager();

        plugin.registerListener(new PlayerListener());
    }

    @Override
    public void onEnable() {
        configManager.reloadConfig();
    }

    public ConfigManager getConfigManager() {
        return configManager;
    }

    public static Welcome getInstance() {
        return instance;
    }
}
