package com.minbo.dubbo.provider;

public class DemoServiceImpl implements DemoService{
	public int count = 0;

	public String sayHello(String name) {
		System.out.println(count);
		for(int i = 0; i < 1000000; i++)
		{

		}
		count++;
		return "Welcome to Minbo's Dubbo, Hello " + name;
	}

}
