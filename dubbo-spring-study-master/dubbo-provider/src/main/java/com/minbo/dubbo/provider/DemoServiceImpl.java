package com.minbo.dubbo.provider;

public class DemoServiceImpl implements DemoService{
	public int count = 0;

	public String sayHello(String name) {
		System.out.println(count);
		count++;
		return "Welcome to Minbo's Dubbo, Hello " + name;
	}

}
