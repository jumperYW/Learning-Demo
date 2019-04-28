package com.wayne.collection;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.concurrent.ConcurrentHashMap;

public class Demo {
	
	public static Object[] getObjects(){
		Integer[] objs = new Integer[10];
		return objs;
	}
	
	static class MyList<E> extends ArrayList<E> {

        // toArray() 的同名方法
        public String[] toArray() {
            return new String[]{"1", "2", "3"};
        }

    }
	
	public static void main(String[] args) {
		
//		 List<String> ss = new LinkedList<String>();             // LinkedList toArray() 返回的本身就是 Object[]
//	        ss.add("123");
//	        Object[] objs = ss.toArray();
//	        objs[0] = new Object();
//
//	        // 此处说明了：c.toArray might (incorrectly) not return Object[] (see 6260652)
//	        ss = new MyList<String>();
//	        objs = ss.toArray();
//	        System.out.println(objs.getClass());        // class [Ljava.lang.String;
//	        objs[0] = new Object();                         // java.lang.ArrayStoreException: java.lang.Object
		
		/**
		 * fail-fast
		 */
		/*Map<String,String> premiumPhone = new HashMap<String,String>();  
        premiumPhone.put("Apple", "iPhone");  
        premiumPhone.put("HTC", "HTC one");  
        premiumPhone.put("Samsung","S5");  
          
        Iterator iterator = premiumPhone.keySet().iterator();  
          
        while (iterator.hasNext())  
        {  
            System.out.println(premiumPhone.get(iterator.next()));  
            premiumPhone.put("Sony", "Xperia Z");  
        }*/
		
        /**
         * fail-safe
         */
        ConcurrentHashMap<String,String> premiumPhone = new ConcurrentHashMap<String,String>();  
		premiumPhone.put("Apple", "iPhone");  
		premiumPhone.put("HTC", "HTC one");  
		premiumPhone.put("Samsung","S5");  
		
		Iterator iterator = premiumPhone.keySet().iterator();  
		
		while (iterator.hasNext())  
		{  
			System.out.println(premiumPhone.get(iterator.next()));  
			premiumPhone.put("Sony", "Xperia Z");  
		}  
        
		
		/**
		 * TreeSet
		 */
//		class Person implements Comparable{
//			String name;
//			int age;
//			public Person(String name, int age) {
//				super();
//				this.name = name;
//				this.age = age;
//			}
//			@Override
//			public int compareTo(Object o) {
//				return 0;
//			}
//		}
//		class Person{
//			String name;
//			int age;
//			public Person(String name, int age) {
//				super();
//				this.name = name;
//				this.age = age;
//			}
////			@Override
////			public int compareTo(Person o) {
////				return 0;
////			}
//		}
		/*class Staff extends Person{

			int 
			
			public Staff(String name, int age) {
				super(name, age);
			}
			
			@Override
			public int compareTo(Object o) {
				return -1;
			}
		}
		
		class Manager extends Person{

			public Manager(String name, int age) {
				super(name, age);
				// TODO Auto-generated constructor stub
			}
			
			@Override
			public int compareTo(Object o) {
				return 1;
			}
		}*/
		
//		TreeSet ts = new TreeSet();
//		Person p1 = new Person("Jack",20);
//		Person p2 = new Person("Rose",15);
//		
//		ts.add(p1);
////		ts.add(p2);
//		System.out.println(ts);
		

//		Object
//		Long
//		ArrayList
//		HashSet
//		String
//		HashSet
//		HashMap
//		Hashtable
//		ConcurrentHashMap
//		List
//		Arrays
		
	}
	
}
