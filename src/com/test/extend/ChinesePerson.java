package com.test.extend;

public class ChinesePerson extends Person{

	private String skin;
	private String name;
	
	public String getSkin() {
		return skin;
	}

	public void setSkin(String skin) {
		this.skin = skin;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "ChinesePerson [skin=" + skin + ", name=" + name + ", getAge()=" + getAge() + "]";
	}

	
	
}
