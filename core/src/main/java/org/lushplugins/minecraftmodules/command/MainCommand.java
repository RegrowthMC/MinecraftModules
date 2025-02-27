package org.lushplugins.minecraftmodules.command;

import org.bukkit.command.CommandSender;
import org.jetbrains.annotations.NotNull;
import org.lushplugins.lushlib.command.Command;
import org.lushplugins.lushlib.libraries.chatcolor.ChatColorHandler;
import org.lushplugins.minecraftmodules.MinecraftModulesPlugin;

public class MainCommand extends Command {

    public MainCommand() {
        super(MinecraftModulesPlugin.getInstance().getPluginName().toLowerCase());
        addSubCommand(new ReloadCommand());

        // Plugin Modules
    }

    @Override
    public boolean execute(@NotNull CommandSender sender, @NotNull org.bukkit.command.Command command, @NotNull String label, @NotNull String[] args, @NotNull String[] fullArgs) {
        ChatColorHandler.sendMessage(sender, "&#a8e1ffYou are currently running &#58b1e0%s &#a8e1ffversion &#58b1e0%s".formatted(
            MinecraftModulesPlugin.getInstance().getPluginName(),
            MinecraftModulesPlugin.getInstance().getDescription().getVersion()));

        return true;
    }
}
