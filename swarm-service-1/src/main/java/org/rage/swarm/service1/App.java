package org.rage.swarm.service1;

import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.ClassLoaderAsset;
import org.rage.swarm.service1.rest.NameResource;
import org.wildfly.swarm.container.Container;
import org.wildfly.swarm.jaxrs.JAXRSArchive;
import org.wildfly.swarm.topology.TopologyArchive;

/**
 * Service 1
 */
public class App 
{
    public static void main( String[] args ) throws Exception
    {
    	Container container = new Container();

        JAXRSArchive deployment = ShrinkWrap.create(JAXRSArchive.class);
	    
	    deployment.addAllDependencies();
	    
        deployment.addPackages(true,"org.rage.swarm.service1");
        deployment.addResource(NameResource.class);
		deployment.addAsWebInfResource(new ClassLoaderAsset("META-INF/beans.xml", App.class.getClassLoader()),"classes/META-INF/beans.xml");
        deployment.as(TopologyArchive.class).advertise("name-service");
        
        container.start();
        container.deploy(deployment);
    }
}
