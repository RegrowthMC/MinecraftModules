package org.lushplugins.minecraftmodules.common.plugin;

import org.lushplugins.lushlib.plugin.SpigotPlugin;
import org.lushplugins.minecraftmodules.common.data.SMPUser;

import java.util.UUID;

public abstract class RootPlugin extends SpigotPlugin {

    public abstract String getPluginName();

    public abstract SMPUser getCachedSMPUser(UUID uuid);

    public abstract void saveCachedSMPUser(UUID uuid);
}
