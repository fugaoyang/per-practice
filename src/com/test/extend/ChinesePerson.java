package com.test.extend;

public class ChinesePerson extends Person{

	private String skin;

	public String getSkin() {
		return skin;
	}

	public void setSkin(String skin) {
		this.skin = skin;
	}

	@Override
	public String toString() {
		return "ChinesePerson [skin=" + skin + ", getName()=" + getName() + ", getAge()=" + getAge() + "]";
	}
	
	
}
