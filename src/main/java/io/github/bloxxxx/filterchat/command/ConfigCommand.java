package io.github.bloxxxx.filterchat.command;

import com.mojang.brigadier.CommandDispatcher;
import io.github.bloxxxx.filterchat.Filterchat;
import io.github.bloxxxx.filterchat.config.ConfigScreen;
import net.fabricmc.fabric.api.client.command.v2.ClientCommandManager;
import net.fabricmc.fabric.api.client.command.v2.FabricClientCommandSource;
import net.minecraft.command.CommandRegistryAccess;

public class ConfigCommand {
    public static void register(CommandDispatcher<FabricClientCommandSource> dispatcher, CommandRegistryAccess registryAccess) {
        dispatcher.register(ClientCommandManager.literal("filterchat")
                .then(ClientCommandManager.literal("config").executes((command) -> {
                    Filterchat.pendingScreen = new ConfigScreen();
                    return 0;
                }))
        );
    }
}
