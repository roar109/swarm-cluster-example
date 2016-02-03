package org.rage.swarm.service2;

import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.ClassLoaderAsset;
import org.rage.swarm.service2.rest.MyNameResource;
import org.wildfly.swarm.container.Container;
import org.wildfly.swarm.jaxrs.JAXRSArchive;
import org.wildfly.swarm.jaxrs.JAXRSFraction;
import org.wildfly.swarm.netflix.ribbon.RibbonArchive;
import org.wildfly.swarm.topology.TopologyArchive;
import org.wildfly.swarm.weld.WeldFraction;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args ) throws Exception
    {
    	Container container = new Container();

        JAXRSArchive deployment = ShrinkWrap.create(JAXRSArchive.class);
        
	    // add the fractions needed
	    //container.fraction(new JAXRSFraction());
	    //container.fraction(new WeldFraction());
	    
	    deployment.addAllDependencies();
	    
        deployment.addPackages(true,"org.rage.swarm.service2");
        deployment.addResource(MyNameResource.class);
		deployment.addAsWebInfResource(new ClassLoaderAsset("META-INF/beans.xml", App.class.getClassLoader()),"classes/META-INF/beans.xml");
        deployment.as(TopologyArchive.class).advertise("myname-service");
        
        container.start();
        container.deploy(deployment);
    }
}
