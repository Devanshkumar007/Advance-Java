package com.lpu.BeanScope;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.lpu.Annotations.AppConfig;

public class BeanScopeDemo {
	public static void main(String[] args) {
		
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(AppConfig2.class);
				
		System.out.println("SINGLETON");
		SingletonBean s1 = context.getBean(SingletonBean.class);
		SingletonBean s2 = context.getBean(SingletonBean.class);
		System.out.println((s1==s2)+"\n"+s1+"\n"+s2);
		
		System.out.println("PROTOTYPE");
		PrototypeBean p1 = context.getBean(PrototypeBean.class);
		PrototypeBean p2 = context.getBean(PrototypeBean.class);
		System.out.println((p1==p2)+"\n"+p1+"\n"+p2);
	}
}
