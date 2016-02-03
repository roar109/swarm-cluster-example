package org.rage.swarm.service2.service;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Produces;

import com.netflix.ribbon.Ribbon;

@ApplicationScoped
public class ServicesFactory {

    @Produces
    @ApplicationScoped
    public static NameService getInstance() {
        return Ribbon.from(NameService.class);
    }
}
