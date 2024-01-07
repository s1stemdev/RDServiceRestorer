package ru.rivendell.rdservicerestorer;

import eu.cloudnetservice.driver.event.EventManager;
import eu.cloudnetservice.driver.module.ModuleLifeCycle;
import eu.cloudnetservice.driver.module.ModuleTask;
import eu.cloudnetservice.driver.module.driver.DriverModule;
import eu.cloudnetservice.node.command.CommandProvider;
import lombok.Getter;
import ru.rivendell.rdservicerestorer.commands.ReloadCommand;
import ru.rivendell.rdservicerestorer.commands.restore.RestoreAllCommand;
import ru.rivendell.rdservicerestorer.commands.restore.RestoreCommand;
import ru.rivendell.rdservicerestorer.config.ConfigLoader;
import ru.rivendell.rdservicerestorer.config.configurations.RestorerConfiguration;
import ru.rivendell.rdservicerestorer.listeners.NextTickListener;
import ru.rivendell.rdservicerestorer.restorer.Restorer;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class ModuleMain extends DriverModule {

    @Getter private static List<Restorer> restorers = new ArrayList<>();

    private ConfigLoader configLoader;
    @Getter private static ModuleMain instance;

    private EventManager eventManager;
    private CommandProvider commandProvider;

    @ModuleTask(lifecycle = ModuleLifeCycle.STARTED)
    public void start(EventManager eventManager, CommandProvider commandProvider) {
        instance = this;
        this.eventManager = eventManager;
        this.commandProvider = commandProvider;

        setupConfig();

        registerListeners();
        registerCommands();
    }

    private void setupConfig() {
        configLoader = new ConfigLoader();
        loadRestorers();
    }

    public void reloadRestorers() {

        restorers.clear();
        loadRestorers();
    }

    private void registerListeners() {
        eventManager.registerListener(NextTickListener.class);
    }

    private void registerCommands() {
        commandProvider.register(RestoreCommand.class);
        commandProvider.register(RestoreAllCommand.class);
        commandProvider.register(ReloadCommand.class);
    }

    private void loadRestorers() {
        File folder = new File(getModuleFolder(), getRestorersSubDir());
        if(folder.listFiles() == null) return;
            for(File file : Objects.requireNonNull(folder.listFiles())) {
                RestorerConfiguration config = configLoader.load(getRestorersSubDir() + file.getName(), RestorerConfiguration.class);
                restorers.add(new Restorer(config));
            }
    }

    public static Restorer getRestorerById(String id) {
        for (Restorer restorer : restorers) {
            if (restorer.getConfig().getId().equals(id)) {
                return restorer;
            }
        }
        return null;
    }

    public static String getRestorersSubDir() { return "restorers/"; }

    public static String getModuleFolder() {
        return "modules/RDServiceRestorer/";
    }

}
