package com.test.extend;

public class ExtendsTest {
	
	public void forEach(){
		for(int i=0;i<5;i++){
			System.out.println("start" + i);
			next(i);
			System.out.println("end" + i);
		}
	}
	public void next(int i){
		if(i==3){
			return;
		}
		
	}
	

 public static void main(String args []){
	//new ExtendsTest().forEach();
	 
//	 ChinesePerson c = new ChinesePerson();
//	 c.setSkin("black");
//	 Person p = c;
//	 p.setAge("12");
//	 p.setName("aaaa");
////	 Person pp = new Person();
////	 pp.setAge("11");
////	 pp.setName("ff");
////	 ChinesePerson c = (ChinesePerson)pp;
////	 c.setSkin("white");
//	 System.out.println(p);
	
	System.out.println("1中国".substring(0,3));
	
	//System.out.println(subRemarkStr("1通讯异常，请稍后再试"));
 }
 
}
