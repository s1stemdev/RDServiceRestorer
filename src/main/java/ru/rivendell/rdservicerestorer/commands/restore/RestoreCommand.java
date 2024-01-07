package ru.rivendell.rdservicerestorer.commands.restore;

import cloud.commandframework.annotations.Argument;
import cloud.commandframework.annotations.CommandMethod;
import cloud.commandframework.annotations.CommandPermission;
import eu.cloudnetservice.node.command.source.CommandSource;
import eu.cloudnetservice.node.service.CloudServiceManager;
import jakarta.inject.Inject;
import jakarta.inject.Singleton;
import ru.rivendell.rdservicerestorer.ModuleMain;
import ru.rivendell.rdservicerestorer.restorer.Restorer;

@Singleton
@CommandPermission("rdsericerestorer.restore")
public class RestoreCommand {

    private CloudServiceManager serviceManager;

    @Inject
    public RestoreCommand(CloudServiceManager serviceManager) {
        this.serviceManager = serviceManager;
    }


    @CommandMethod("restorer restore <restorer_id>")
    public void onCommand(CommandSource source, @Argument("restorer_id") String id) {

        try {
            ModuleMain.getRestorerById(id).restoreCommand(serviceManager);
        } catch (NullPointerException exception) {
            source.sendMessage("ID is invalid");
        }


    }

}
