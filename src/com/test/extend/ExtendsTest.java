package com.test.extend;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.math.BigDecimal;

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
	
	public void changeValue(BigDecimal b1,BigDecimal b2){
		for(int i=0;i<3;i++){
          b1 = b1.add(BigDecimal.valueOf(1.12D));			
          b2 = b2.add(BigDecimal.valueOf(1.11D));	
		}
		System.out.println(b1+"------"+b2);
	}
	
	public static void check(Person p) throws Exception{
		  if(p instanceof ChinesePerson){
			  ChinesePerson cp = (ChinesePerson) p;
			  Method getM = cp.getClass().getMethod("getName");
			  String value = (String) getM.invoke(cp);
			  System.out.println("getName==="+value);
			  
			  Class setM = cp.getClass().getSuperclass();
			  //setM.invoke(cp, "23");
			  
			  Field f = cp.getClass().getSuperclass().getDeclaredField("age");
		      f.setAccessible(true);
		      f.set(cp, "22");
			  
			  System.out.println("obj==="+cp);
		  }
	}
	
	public static void checkO(Object o) throws Exception{
		
		  if(o instanceof Person){
			  Person p  = (Person) o;
			  Method getM = p.getClass().getMethod("getName");
			  String value = (String) getM.invoke(p);
			  System.out.println("getName==="+value);
			  
			//  Class setM = cp.getClass().getSuperclass();
			  //setM.invoke(cp, "23");
			  
			//  Method setM = p.getClass().getMethod("setAge");
			//  setM.invoke(p, "12");
			  Field f = p.getClass().getSuperclass().getDeclaredField("age");
		      f.setAccessible(true);
		      f.set(p, "22");
		      System.out.println("obj==="+p);
		  }
	}

   public static void main(String args []){
	//new ExtendsTest().forEach();
	
//	 ExtendsTest test = new ExtendsTest();
//	 BigDecimal a = new  BigDecimal(1.00d);
//	 BigDecimal b = new BigDecimal(1.00d);
//	 test.changeValue(a, b);
//	 System.out.println(a+"==="+b);
	   
	  ChinesePerson cp = new ChinesePerson();
	  cp.setName("user");
	  cp.setSkin("blue");
	  try {
		  checkO(cp);
	} catch (Exception e) {
		e.printStackTrace();
	}
	
	
	//System.out.println(subRemarkStr("1通讯异常，请稍后再试"));
 }
 
}
