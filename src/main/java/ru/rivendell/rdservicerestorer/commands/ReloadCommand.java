package ru.rivendell.rdservicerestorer.commands;

import cloud.commandframework.annotations.CommandMethod;
import cloud.commandframework.annotations.CommandPermission;
import eu.cloudnetservice.node.command.source.CommandSource;
import eu.cloudnetservice.node.service.CloudServiceManager;
import jakarta.inject.Inject;
import jakarta.inject.Singleton;
import ru.rivendell.rdservicerestorer.ModuleMain;
import ru.rivendell.rdservicerestorer.restorer.Restorer;

@Singleton
@CommandPermission("rdsericerestorer.reload")
public class ReloadCommand {

    private CloudServiceManager serviceManager;

    @Inject
    public ReloadCommand(CloudServiceManager serviceManager) {
        this.serviceManager = serviceManager;
    }

    @CommandMethod("restorer reload")
    public void onCommand(CommandSource source) {
        ModuleMain.getInstance().reloadRestorers();
    }

}
