package org.rage.swarm.service2.rest;

import java.io.IOException;
import java.io.InputStream;
import java.util.Map;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.container.AsyncResponse;
import javax.ws.rs.container.Suspended;
import javax.ws.rs.core.MediaType;

import org.rage.swarm.service2.dto.NameWrapper;
import org.rage.swarm.service2.service.NameService;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectReader;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufInputStream;
import rx.Observable;

@Path("myname")
public class MyNameResource {

	@Inject
	private NameService nameService;

	@GET
	@Path("/{name}")
	@Produces(MediaType.APPLICATION_JSON)
	public void handleMyName(@Suspended final AsyncResponse asyncResponse, @PathParam("name") String name) {
		System.out.println("Working with name " + name);
		Observable<NameWrapper> nameWrapper = Observable.just(new NameWrapper());
		Observable<ByteBuf> rawData = nameService.get(name).observe();

		nameWrapper.zipWith(rawData, (wrapper, byteBuf) -> {
			try {
				final Map map = parseByteBufPayload(byteBuf);
				wrapper.setResultName(String.valueOf(map.get("paddingName")));
				wrapper.setName(name);
			} catch (Exception e) {
				asyncResponse.resume(e);
			}
			return wrapper;
		}).subscribe(asyncResponse::resume, asyncResponse::resume);
	}

	private Map parseByteBufPayload(ByteBuf payload) throws JsonParseException, IOException {
		final ObjectMapper mapper = new ObjectMapper();
		final ObjectReader reader = mapper.reader();
		final JsonFactory factory = new JsonFactory();
		final JsonParser parser = factory.createParser((InputStream)new ByteBufInputStream(payload));
		return reader.readValue(parser, Map.class);
	}
}
