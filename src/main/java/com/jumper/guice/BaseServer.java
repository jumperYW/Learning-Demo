package com.jumper.guice;

import com.google.inject.Binder;
import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.Module;
import com.google.inject.matcher.Matchers;

public  class BaseServer {

	public  BaseServer() {
        super();
        InjectorGenerator.getInjector().injectMembers(this);
    }
    
    
    static class InjectorGenerator{
        private static final Injector injector= Guice.createInjector(new Module() {

			public void configure(Binder binder) {
				//顺序很重要
				binder.bindInterceptor(Matchers.any(), Matchers.annotatedWith(LogTag.class), LogInterceptor.getInstance());
				
			}
        	
        });
        public static Injector getInjector(){
            return injector;
        }
    }
}
