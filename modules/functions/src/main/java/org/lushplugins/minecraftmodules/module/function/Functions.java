package org.lushplugins.minecraftmodules.module.function;

import org.lushplugins.minecraftmodules.common.module.Module;
import org.lushplugins.minecraftmodules.common.plugin.RootPlugin;
import org.lushplugins.minecraftmodules.module.function.command.FunctionCommand;
import org.lushplugins.minecraftmodules.module.function.function.FunctionManager;

public final class Functions extends Module {
    private static Functions instance;

    private final FunctionManager functionManager;

    public Functions(RootPlugin plugin) {
        super("functions", plugin);

        if (instance == null) {
            instance = this;
        }

        this.functionManager = new FunctionManager();

        plugin.registerCommand(new FunctionCommand());
    }

    @Override
    public void onEnable() {
        functionManager.reloadConfig();
    }

    public FunctionManager getFunctionManager() {
        return functionManager;
    }

    public static Functions getInstance() {
        return instance;
    }
}
