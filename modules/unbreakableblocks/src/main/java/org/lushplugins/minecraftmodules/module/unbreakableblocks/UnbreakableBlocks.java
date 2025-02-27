package org.lushplugins.minecraftmodules.module.unbreakableblocks;

import org.lushplugins.minecraftmodules.module.unbreakableblocks.listener.SpawnerListener;
import org.lushplugins.minecraftmodules.common.module.Module;
import org.lushplugins.minecraftmodules.common.plugin.RootPlugin;
import org.lushplugins.minecraftmodules.module.unbreakableblocks.config.ConfigManager;
import org.lushplugins.minecraftmodules.module.unbreakableblocks.listener.BlockListener;

public class UnbreakableBlocks extends Module {
    private static UnbreakableBlocks instance;

    private final ConfigManager configManager;

    public UnbreakableBlocks(RootPlugin plugin) {
        super("unbreakable_blocks", plugin);

        if (instance == null) {
            instance = this;
        }

        this.configManager = new ConfigManager();

        plugin.registerListeners(
            new BlockListener(),
            new SpawnerListener()
        );
    }

    @Override
    public void onEnable() {
        this.configManager.reloadConfig();
    }

    public ConfigManager getConfigManager() {
        return configManager;
    }

    public static UnbreakableBlocks getInstance() {
        return instance;
    }
}