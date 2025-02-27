package org.lushplugins.minecraftmodules.module.glassitemframes;

import fr.skytasul.glowingentities.GlowingEntities;
import org.bukkit.scheduler.BukkitTask;
import org.lushplugins.minecraftmodules.module.glassitemframes.listener.ItemFrameListener;
import org.lushplugins.minecraftmodules.module.glassitemframes.viewer.InvisibleItemFrameViewer;
import org.lushplugins.minecraftmodules.common.module.Module;
import org.lushplugins.minecraftmodules.common.plugin.RootPlugin;

public class GlassItemFrames extends Module {
    private static GlassItemFrames instance;

    private GlowingEntities glowingEntities;
    private BukkitTask viewerTask;

    public GlassItemFrames(RootPlugin plugin) {
        super("glass_item_frames", plugin);

        if (instance == null) {
            instance = this;
        }
    }

    @Override
    public void onEnable() {
        RootPlugin plugin = this.getPlugin();

        glowingEntities = new GlowingEntities(plugin);
        viewerTask = new InvisibleItemFrameViewer().runTaskTimer(plugin, 20, 20);

        plugin.registerListener(new ItemFrameListener());
    }

    @Override
    protected void onDisable() {
        if (viewerTask != null) {
            viewerTask.cancel();
            viewerTask = null;
        }

        if (glowingEntities != null) {
            glowingEntities.disable();
            glowingEntities = null;
        }
    }

    public GlowingEntities getGlowingEntities() {
        return glowingEntities;
    }

    public static GlassItemFrames getInstance() {
        return instance;
    }
}
