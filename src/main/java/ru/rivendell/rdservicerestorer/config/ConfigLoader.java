package ru.rivendell.rdservicerestorer.config;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import ru.rivendell.rdservicerestorer.ModuleMain;

import java.io.File;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;

public class ConfigLoader {


    public <T extends Configurable> T load(String fileName, Class<T> clazz) {
        Gson g = new Gson();
        File config = new File(ModuleMain.getModuleFolder(), fileName);

        try {
            byte[] bytes = Files.readAllBytes(config.toPath());
            return g.fromJson(new String(bytes, StandardCharsets.UTF_8), clazz);
        } catch (Exception e) {
            throw new RuntimeException("Config not found");
        }
    }

    public Gson Gson() {
        return new GsonBuilder()
                .create();
    }

}
