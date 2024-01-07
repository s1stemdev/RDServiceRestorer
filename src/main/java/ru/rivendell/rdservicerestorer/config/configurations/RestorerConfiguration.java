package ru.rivendell.rdservicerestorer.config.configurations;

import lombok.Getter;
import ru.rivendell.rdservicerestorer.config.Configurable;

@Getter
public class RestorerConfiguration extends Configurable {

    public String id;
    public boolean enabled;
    public boolean removeFiles;
    public String[] tasks;
    public int delay;


    public RestorerConfiguration(String id, boolean enabled, boolean removeFiles, String[] tasks, int delay) {
        this.id = id;
        this.enabled = enabled;
        this.removeFiles = removeFiles;
        this.tasks = tasks;
        this.delay = delay;
    }

    @Override
    public String getName() {
        return id;
    }
}
