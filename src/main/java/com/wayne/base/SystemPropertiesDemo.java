package com.wayne.base;

/**
 * 
 * @author daojia
 *
 */
public class SystemPropertiesDemo {

	char c;
	
	int i;
	
	String str = "123";
	
	int[] p = new int[]{1,2,3};
	void addInt(int x){
		x++;
	}
	
	void addStr(String str){
		str += "321";
	}
	
	void changeItem(int[] p){
		p[1]=5;
	}
	public static void main(String[] args) {
		SystemPropertiesDemo a = new SystemPropertiesDemo();
		System.out.println("|"+a.c+"|");
		System.out.println(a.i);
		a.addInt(a.i);
		System.out.println(a.i);
		System.out.println(a.str+"..");
		a.addStr(a.str);
		System.out.println(a.str+"..");
		
		
		System.out.println(a.p[1]);
		a.changeItem(a.p);
		System.out.println(a.p[1]);
		
		System.out.println("--------------------");
		System.getProperties().list(System.out);
		System.out.println("--------------------");
		System.out.println(System.getProperty("user.name"));
		System.out.println("--------------------");
		System.out.println(System.getProperty("java.library.path"));
		System.out.println("--------------------");
		System.getProperty("line.separator");
		
	}
	
}
