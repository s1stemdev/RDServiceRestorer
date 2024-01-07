package ru.rivendell.rdservicerestorer.restorer;

import eu.cloudnetservice.driver.service.ServiceInfoSnapshot;
import eu.cloudnetservice.driver.service.ServiceTask;
import eu.cloudnetservice.node.service.CloudService;
import eu.cloudnetservice.node.service.CloudServiceManager;
import lombok.Getter;
import ru.rivendell.rdservicerestorer.config.configurations.RestorerConfiguration;

import java.util.ArrayList;
import java.util.Collection;

public class Restorer {

    @Getter private RestorerConfiguration config;
    private long lastTime;

    public Restorer(RestorerConfiguration config) {
        this.config = config;
        lastTime = System.currentTimeMillis();
    }

    public void restoreAuto(CloudServiceManager serviceManager) {
        if(config.isEnabled()) {

            if(System.currentTimeMillis() - lastTime < config.getDelay()) return;

            lastTime = System.currentTimeMillis();

            restore(serviceManager);
        }
    }

    public void restoreCommand(CloudServiceManager serviceManager) {
            restore(serviceManager);
    }

    private void restore(CloudServiceManager serviceManager) {
        Collection<ServiceInfoSnapshot> services = new ArrayList<>();

        for(String task : config.getTasks()) {
            services.addAll(serviceManager.servicesByTask(task));
        }

        for(ServiceInfoSnapshot service : services) {
            if(config.removeFiles) {
                service.provider().stop();
                service.provider().delete();
            }
            else {
                service.provider().runCommand("save-all");
                service.provider().restart();
            }
        }
    }
}
