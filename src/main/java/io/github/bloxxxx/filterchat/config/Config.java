package io.github.bloxxxx.filterchat.config;

import com.google.gson.JsonObject;
import io.github.bloxxxx.filterchat.Filterchat;
import io.github.bloxxxx.filterchat.util.FileManager;

public class Config {
    private static Config instance;
    private Config() {}

    public int testConfig = 0;

    public static Config getConfig() {
        if (instance == null) {
            try {
                instance = Filterchat.gson.fromJson(FileManager.readConfig(), Config.class);
                instance.save();
            } catch (Exception exception) {
                instance = new Config();
                instance.save();
            }
        }
        return instance;
    }

    public void save() {
        try {
            JsonObject object = new JsonObject();

            object.addProperty("testConfig", testConfig);

            FileManager.writeConfig(object.toString());
        } catch (Exception exception) {
            Filterchat.LOGGER.error("Failed to save config: " + exception);
        }

    }
}
