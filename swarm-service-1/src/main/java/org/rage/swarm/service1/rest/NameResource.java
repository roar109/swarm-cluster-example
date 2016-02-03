package org.rage.swarm.service1.rest;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.rage.swarm.service1.data.DataProvider;
import org.rage.swarm.service1.dto.NameWrapper;

@Path("name")
public class NameResource {
	
	@Inject
	private DataProvider dataProvider;

	@GET
	@Path("/{name}")
	@Produces(MediaType.APPLICATION_JSON)
	public NameWrapper padName(@PathParam("name") String name){
		return new NameWrapper(Boolean.TRUE, name, dataProvider.returnNameWithPadding(name));
	}
}
