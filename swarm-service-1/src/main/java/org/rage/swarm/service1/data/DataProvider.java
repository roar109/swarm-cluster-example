package org.rage.swarm.service1.data;

public class DataProvider {

	public String returnNameWithPadding(final String name){
		System.out.println("Name: "+name);
		return name + System.nanoTime();
	}
}
