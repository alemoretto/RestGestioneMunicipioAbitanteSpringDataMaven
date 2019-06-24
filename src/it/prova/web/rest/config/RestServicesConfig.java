package it.prova.web.rest.config;

import java.util.HashSet;
import java.util.Set;

import javax.ws.rs.core.Application;

import it.prova.web.rest.resources.AbitanteResource;
import it.prova.web.rest.resources.MunicipioResource;

public class RestServicesConfig extends Application {
	 public Set<Class<?>> getClasses() {
        Set<Class<?>> classes = new HashSet<Class<?>>();
        classes.add(MunicipioResource.class);
        classes.add(AbitanteResource.class);
        return classes;
	}
}