package org.lushplugins.minecraftmodules.module;

import org.lushplugins.minecraftmodules.MinecraftModulesPlugin;
import org.lushplugins.minecraftmodules.common.module.Module;
import org.lushplugins.minecraftmodules.common.plugin.RootPlugin;

import java.util.HashMap;
import java.util.function.Function;
import java.util.logging.Level;

public class ModuleRegistry {
    private final HashMap<String, Function<RootPlugin, Module>> moduleTypes = new HashMap<>();

    public boolean isRegisteredModule(String moduleId) {
        return moduleTypes.containsKey(moduleId);
    }

    public void registerModule(String moduleId, Function<RootPlugin, Module> constructor) {
        moduleTypes.put(moduleId, constructor);
    }

    public Module constructModule(String moduleId) {
        Function<RootPlugin, Module> constructor = moduleTypes.get(moduleId);

        try {
            return constructor != null ? constructor.apply(MinecraftModulesPlugin.getInstance()) : null;
        } catch (Exception e) {
            MinecraftModulesPlugin.getInstance().getLogger().log(Level.WARNING, "Caught error whilst constructing module '%s': ".formatted(moduleId), e);
            return null;
        }
    }
}
