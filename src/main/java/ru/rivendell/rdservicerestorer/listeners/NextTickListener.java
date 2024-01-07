package ru.rivendell.rdservicerestorer.listeners;

import eu.cloudnetservice.driver.event.EventListener;
import eu.cloudnetservice.node.event.instance.CloudNetTickEvent;
import eu.cloudnetservice.node.service.CloudServiceManager;
import jakarta.inject.Inject;
import jakarta.inject.Singleton;
import ru.rivendell.rdservicerestorer.ModuleMain;
import ru.rivendell.rdservicerestorer.restorer.Restorer;


@Singleton
public class NextTickListener {

    private CloudServiceManager serviceManager;

    @Inject
    public NextTickListener(CloudServiceManager serviceManager) {
        this.serviceManager = serviceManager;
    }

    @EventListener
    public void onNextTick(CloudNetTickEvent event) {
        for(Restorer restorer : ModuleMain.getRestorers()) {
            restorer.restoreAuto(serviceManager);
        }
    }

}
