package org.rage.swarm.service1.dto;

public class NameWrapper {

	private String name;
	private String paddingName;
	private boolean valid;
	
	public NameWrapper(){}
	public NameWrapper(final boolean valid, String name, String paddingName){
		this.valid = valid;
		this.name = name;
		this.paddingName = paddingName;
	}
	

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPaddingName() {
		return paddingName;
	}

	public void setPaddingName(String paddingName) {
		this.paddingName = paddingName;
	}

	public boolean isValid() {
		return valid;
	}

	public void setValid(boolean valid) {
		this.valid = valid;
	}

}
