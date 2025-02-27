package org.lushplugins.minecraftmodules.listener;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.lushplugins.minecraftmodules.MinecraftModulesPlugin;

public class PlayerListener implements Listener {

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        MinecraftModulesPlugin.getInstance().getUserManager().getUser(player.getUniqueId(), true);
    }

    @EventHandler
    public void onPlayerQuit(PlayerQuitEvent event) {
        Player player = event.getPlayer();
        MinecraftModulesPlugin.getInstance().getUserManager().invalidateUser(player.getUniqueId());
    }
}
