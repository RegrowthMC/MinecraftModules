package org.lushplugins.minecraftmodules.command;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.lushplugins.lushlib.command.SubCommand;
import org.lushplugins.lushlib.libraries.chatcolor.ChatColorHandler;
import org.lushplugins.minecraftmodules.MinecraftModulesPlugin;
import org.lushplugins.minecraftmodules.common.module.Module;

import java.util.ArrayList;
import java.util.List;

public class ReloadCommand extends SubCommand {

    public ReloadCommand() {
        super("reload");
        addRequiredPermission(MinecraftModulesPlugin.getInstance().getPluginName().toLowerCase() + ".reload");
    }

    @Override
    public boolean execute(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args, @NotNull String[] fullArgs) {
        MinecraftModulesPlugin.getInstance().getConfigManager().reload();

        if (args.length != 0) {
            String moduleId = args[0].toLowerCase();
            Module module =  MinecraftModulesPlugin.getInstance().getModuleManager().getModule(moduleId);
            if (module != null) {
                module.reload();
                ChatColorHandler.sendMessage(sender, "&#b7faa2%s has successfully reloaded &#66b04f%s 🔃".formatted(MinecraftModulesPlugin.getInstance().getPluginName(), moduleId));
            } else {
                ChatColorHandler.sendMessage(sender, "&#ff6969Failed to find module &#d13636%s".formatted(moduleId));
            }
        } else {
            ChatColorHandler.sendMessage(sender, "&#b7faa2%s has been reloaded &#66b04f🔃".formatted(MinecraftModulesPlugin.getInstance().getPluginName()));
        }

        return true;
    }

    @Override
    public @Nullable List<String> tabComplete(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args, @NotNull String[] fullArgs) {
        if (args.length == 1) {
            return new ArrayList<>(MinecraftModulesPlugin.getInstance().getModuleManager().getModuleTypes());
        } else {
            return null;
        }
    }
}
