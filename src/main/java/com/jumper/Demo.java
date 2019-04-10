/**   
* @Title Demo.java 
* @Package com.daojia 
* @Description: 
* @author yuwen
* @date 2017年7月10日 上午10:42:57 
* @version V1.0   
*/
package com.jumper;

import java.math.BigDecimal;
import java.math.RoundingMode;

/**
* @Description 
* @author yuwen 
* @date 2017年7月10日 上午10:42:57  
*/
public class Demo {

	public static void main(String[] args) throws Exception {
        BigDecimal a = new BigDecimal("1.00");
        BigDecimal b = new BigDecimal("1");
        b = b.setScale(2, RoundingMode.CEILING);
        System.out.println(a.equals(b));
    }

    interface IU1 {
        void test();

        default void defaultTest() {
            System.out.println("defaultTest");
        }
    }

    interface IU2 {
        void test();

//        default void defaultTest() {
//            System.out.println("defaultTest");
//        }
    }

    class U implements IU1, IU2 {

        @Override
        public void test() {
            defaultTest();
        }

    }

}
