package org.lushplugins.minecraftmodules;

import org.lushplugins.lushlib.LushLib;
import org.lushplugins.minecraftmodules.common.plugin.RootPlugin;
import org.lushplugins.minecraftmodules.command.MainCommand;
import org.lushplugins.minecraftmodules.config.ConfigManager;
import org.lushplugins.minecraftmodules.data.SMPUser;
import org.lushplugins.minecraftmodules.data.UserManager;
import org.lushplugins.minecraftmodules.listener.PlayerListener;
import org.lushplugins.minecraftmodules.module.ModuleManager;
import org.lushplugins.minecraftmodules.module.ModuleRegistry;
import org.lushplugins.minecraftmodules.storage.StorageManager;

import java.util.UUID;

public class MinecraftModulesPlugin extends RootPlugin {
    private static MinecraftModulesPlugin plugin;

    private final String pluginName;
    protected ModuleRegistry moduleRegistry;
    private StorageManager storageManager;
    private UserManager userManager;
    private ModuleManager moduleManager;
    private ConfigManager configManager;

    public MinecraftModulesPlugin(String pluginName) {
        this.pluginName = pluginName;
    }

    @Override
    public void onLoad() {
        plugin = this;
        LushLib.getInstance().enable(this);
        moduleRegistry = new ModuleRegistry();
    }

    @Override
    public void onEnable() {
        storageManager = new StorageManager();
        userManager = new UserManager();
        moduleManager = new ModuleManager();

        configManager = new ConfigManager();
        configManager.reload();

        registerListener(new PlayerListener());

        registerCommand(new MainCommand());
    }

    @Override
    public void onDisable() {
        if (configManager != null) {
            configManager.disable();
            configManager = null;
        }

        if (moduleManager != null) {
            moduleManager.disable();
            moduleManager = null;
        }

        if (userManager != null) {
            userManager = null;
        }

        if (storageManager != null) {
            storageManager.disable();
            storageManager = null;
        }
    }

    @Override
    public String getPluginName() {
        return pluginName;
    }

    public ModuleRegistry getModuleRegistry() {
        return moduleRegistry;
    }

    @Override
    public SMPUser getCachedSMPUser(UUID uuid) {
        return userManager.getCachedUser(uuid);
    }

    @Override
    public void saveCachedSMPUser(UUID uuid) {
        userManager.saveUser(this.getCachedSMPUser(uuid));
    }

    public StorageManager getStorageManager() {
        return storageManager;
    }

    public UserManager getUserManager() {
        return userManager;
    }

    public ModuleManager getModuleManager() {
        return moduleManager;
    }

    public ConfigManager getConfigManager() {
        return configManager;
    }

    public static MinecraftModulesPlugin getInstance() {
        return plugin;
    }
}
