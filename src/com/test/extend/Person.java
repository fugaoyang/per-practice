package com.test.extend;

public class Person {
	private String name;
	private String age ;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getAge() {
		return age;
	}
	public void setAge(String age) {
		this.age = age;
	}
	
	@Override
	public String toString(){
		return "name:"+this.getName();
	}
	
	public static void main(String args[]){
		String taNo = "89901".substring(0, "89901".length() - 3);
System.out.println(taNo);
	}
}
