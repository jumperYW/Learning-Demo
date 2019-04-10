/**   
* @Title Service.java 
* @Package com.jumper.guice
* @Description: 
* @author yuwen
* @date 2017年2月6日 上午10:18:04 
* @version V1.0   
*/
package com.jumper.guice;

import com.google.inject.Inject;

/**  
 * @Description 
 * @author yuwen 
 * @date 2017年2月6日 上午10:18:04  
 */
public class ServiceTest extends BaseServer{
	
	@Inject
	ServerTestProxy serverTestProxy;

	public void test(){
		serverTestProxy.test();
	}
	
}
