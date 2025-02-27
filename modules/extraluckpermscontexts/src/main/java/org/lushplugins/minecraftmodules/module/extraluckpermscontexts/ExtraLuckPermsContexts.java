package org.lushplugins.minecraftmodules.module.extraluckpermscontexts;

import net.luckperms.api.LuckPerms;
import net.luckperms.api.context.ContextManager;
import org.lushplugins.minecraftmodules.module.extraluckpermscontexts.calculator.BedrockPlayerCalculator;
import org.lushplugins.minecraftmodules.common.module.Module;
import org.lushplugins.minecraftmodules.common.plugin.RootPlugin;

public class ExtraLuckPermsContexts extends Module {

    public ExtraLuckPermsContexts(RootPlugin plugin) {
        super("luck_perms_contexts", plugin);

        LuckPerms luckPerms = plugin.getServer().getServicesManager().load(LuckPerms.class);
        if (luckPerms == null) {
            throw new IllegalStateException("LuckPerms is required for this module to run");
        }

        ContextManager contextManager = luckPerms.getContextManager();
        plugin.addHook("floodgate", () -> contextManager.registerCalculator(new BedrockPlayerCalculator()));
    }
}
