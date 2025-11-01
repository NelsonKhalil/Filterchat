package io.github.bloxxxx.filterchat;

import com.google.gson.Gson;
import io.github.bloxxxx.filterchat.command.ConfigCommand;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.command.v2.ClientCommandManager;
import net.fabricmc.fabric.api.client.command.v2.ClientCommandRegistrationCallback;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.screen.Screen;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Filterchat implements ClientModInitializer {

    public static final String MOD_ID = "filterchat";
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);
    public static final MinecraftClient MC = MinecraftClient.getInstance();
    public static final Gson gson = new Gson();
    public static Screen pendingScreen = null;

    @Override
    public void onInitializeClient() {
        ClientCommandRegistrationCallback.EVENT.register((dispatcher, registryAccess) -> {
            ConfigCommand.register(dispatcher, registryAccess);
        });

        ClientTickEvents.END_CLIENT_TICK.register((client) -> {
            if (pendingScreen != null) {
                MC.setScreen(pendingScreen);
                pendingScreen = null;
            }
        });
    }
}
