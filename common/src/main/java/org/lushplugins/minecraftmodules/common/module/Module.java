package org.lushplugins.minecraftmodules.common.module;

import com.google.gson.JsonObject;
import org.jetbrains.annotations.Nullable;
import org.lushplugins.minecraftmodules.common.data.UserData;
import org.lushplugins.minecraftmodules.common.data.SMPUser;
import org.lushplugins.minecraftmodules.common.plugin.RootPlugin;

import java.util.UUID;

public abstract class Module extends org.lushplugins.lushlib.module.Module {
    private final RootPlugin plugin;

    public Module(String id, RootPlugin plugin) {
        super(id);
        this.plugin = plugin;
    }

    public RootPlugin getPlugin() {
        return plugin;
    }

    public boolean storesUserData() {
        return false;
    }

    /**
     * @param jsonObject user data json or null to create default
     * @return user data
     */
    public UserData createUserData(UUID uuid, JsonObject jsonObject) {
        return null;
    }

    public @Nullable UserData getCachedUserData(UUID uuid) {
        SMPUser smpUser = plugin.getCachedSMPUser(uuid);
        if (smpUser == null) {
            return null;
        }


        return smpUser.getUserData(id);
    }
}
