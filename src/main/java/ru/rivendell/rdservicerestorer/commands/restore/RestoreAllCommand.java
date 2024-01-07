package ru.rivendell.rdservicerestorer.commands.restore;

import cloud.commandframework.annotations.CommandMethod;
import cloud.commandframework.annotations.CommandPermission;
import jakarta.inject.Inject;
import jakarta.inject.Singleton;
import eu.cloudnetservice.node.command.source.CommandSource;
import eu.cloudnetservice.node.service.CloudServiceManager;
import ru.rivendell.rdservicerestorer.ModuleMain;
import ru.rivendell.rdservicerestorer.restorer.Restorer;

@Singleton
@CommandPermission("rdsericerestorer.restoreall")
public class RestoreAllCommand {

    private CloudServiceManager serviceManager;

    @Inject
    public RestoreAllCommand(CloudServiceManager serviceManager) {
        this.serviceManager = serviceManager;
    }

    @CommandMethod("restorer restoreAll")
    public void onCommand(CommandSource source) {

        for(Restorer restorer : ModuleMain.getRestorers()) {
            restorer.restoreCommand(serviceManager);
            source.sendMessage(restorer.getConfig().getId() + " restored!");
        }
    }
}
