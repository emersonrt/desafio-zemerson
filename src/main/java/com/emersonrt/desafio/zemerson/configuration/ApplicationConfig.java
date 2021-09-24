package com.emersonrt.desafio.zemerson.configuration;

import java.util.Set;
import javax.ws.rs.core.Application;

/**
 *
 * @author emerson
 */

@javax.ws.rs.ApplicationPath("br")
public class ApplicationConfig extends Application{

    @Override
    public Set<Class<?>> getClasses() {
        Set<Class<?>> resources = new java.util.HashSet<>();
        addRestResourceClasses(resources);
        return resources;
    }
    
    private void addRestResourceClasses(Set<Class<?>> resources) {
        resources.add(com.emersonrt.desafio.zemerson.configuration.CrossOriginResourceSharingFilter.class);
        resources.add(com.emersonrt.desafio.zemerson.services.partner.PartnerController.class);
    }

}
