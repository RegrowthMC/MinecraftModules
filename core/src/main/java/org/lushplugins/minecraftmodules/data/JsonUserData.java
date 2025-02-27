package org.lushplugins.minecraftmodules.data;

import com.google.gson.JsonObject;
import org.lushplugins.minecraftmodules.common.data.UserData;

import java.util.UUID;

public class JsonUserData extends UserData {
    private final JsonObject json;

    public JsonUserData(UUID uuid, JsonObject json) {
        super(uuid);
        this.json = json;
    }

    @Override
    public JsonObject toJsonObject() {
        return json;
    }
}
