package org.lushplugins.minecraftmodules.module.cosmetics.config;

import org.bukkit.configuration.ConfigurationSection;
import org.lushplugins.minecraftmodules.module.cosmetics.Cosmetics;

import java.util.Collection;
import java.util.HashMap;

public class ConfigManager {
    private HashMap<String, String> displayNameFormats;

    public ConfigManager() {
        Cosmetics.getInstance().getPlugin().saveDefaultResource("modules/cosmetics.yml");
    }

    public void reloadConfig() {
        ConfigurationSection config = Cosmetics.getInstance().getPlugin().getConfigResource("modules/cosmetics.yml");

        this.displayNameFormats = new HashMap<>();
        ConfigurationSection themesSection = config.getConfigurationSection("display-name-themes");
        if (themesSection != null) {
            themesSection.getValues(false).forEach((key, value) -> displayNameFormats.put(key, (String) value));
        }
    }

    public String getDisplayNameFormat(String name) {
        return displayNameFormats.get(name);
    }

    public Collection<String> getDisplayNameFormats() {
        return displayNameFormats.keySet();
    }
}
