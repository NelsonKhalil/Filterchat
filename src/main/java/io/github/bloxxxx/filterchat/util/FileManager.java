package io.github.bloxxxx.filterchat.util;

import io.github.bloxxxx.filterchat.Filterchat;
import net.fabricmc.loader.api.FabricLoader;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;

public class FileManager {
    public static File getConfigFile() {
        return new File(FabricLoader.getInstance().getConfigDir().toFile(), Filterchat.MOD_ID + ".json");
    }

    public static void writeConfig(String content) throws IOException {
        File file = getConfigFile();
        Path path = file.toPath();
        Files.deleteIfExists(path);
        Files.createFile(path);
        Files.write(path, content.getBytes(), StandardOpenOption.WRITE);
    }

    public static String readConfig() throws IOException {
        return Files.readString(getConfigFile().toPath());
    }

    private FileManager() {}
}
