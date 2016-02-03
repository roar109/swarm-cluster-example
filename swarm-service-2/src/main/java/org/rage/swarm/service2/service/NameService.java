package org.rage.swarm.service2.service;

import com.netflix.ribbon.RibbonRequest;
import com.netflix.ribbon.proxy.annotation.Http;
import com.netflix.ribbon.proxy.annotation.ResourceGroup;
import com.netflix.ribbon.proxy.annotation.TemplateName;
import com.netflix.ribbon.proxy.annotation.Var;

import io.netty.buffer.ByteBuf;

@ResourceGroup(name = "name-service")
public interface NameService {

	@TemplateName("get")
	@Http(method = Http.HttpMethod.GET, uri = "/name/{name}")
	RibbonRequest<ByteBuf> get(@Var("name") String name);
}
