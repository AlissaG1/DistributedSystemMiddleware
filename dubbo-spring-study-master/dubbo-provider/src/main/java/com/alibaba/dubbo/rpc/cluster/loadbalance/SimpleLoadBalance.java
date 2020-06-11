package com.alibaba.dubbo.rpc.cluster.loadbalance;

import java.util.List;
import java.util.Random;


import com.alibaba.dubbo.common.URL;
import com.alibaba.dubbo.rpc.Invocation;
import com.alibaba.dubbo.rpc.Invoker;
import com.alibaba.dubbo.rpc.RpcException;
import com.alibaba.dubbo.rpc.cluster.LoadBalance;
import com.alibaba.dubbo.rpc.cluster.loadbalance.AbstractLoadBalance;
import com.alibaba.metrics.*;


public class SimpleLoadBalance implements LoadBalance {

//	Meter methodMeter = MetricManager.getMeter("test", MetricName.build("myapp.mybiz.hello"));
//
//	public void hello() {
//		// your logic
//		methodMeter.mark();
//	}

	private void doFastCompass() {
		FastCompass cacheRead = MetricManager.getFastCompass("robb", MetricName.build("cache.read"));
//		if (OK) {
//			//缓存命中
//			cacheRead.record(rt, "hit");
//		} else if (SUCCESS) {
//			//访问成功
//			cacheRead.record(rt, "success");
//		} else {
//			//访问失败
//			cacheRead.record(rt, "error");
//		}
	}

	private void doGetCompass() {

		Compass compass = MetricManager.getCompass("robb", MetricName.build("carts.my_cart"));
		// 获取Compass的上下文，并开始计时
		Compass.Context context = compass.time();

		try {
			// 执行业务逻辑
			//doCreateOrder();
			// 标记该次调用是成功的
			//context.success();
		//} catch (BizException1 e1) {
			// 标记BizException1出现了1次
			//context.error("BizException1");
		//} catch (BizException2 e2) {
			// 标记BizException2出现了1次
			//context.error("BizException2");
		} finally {
			// 停止上下文，会自动记录当前的运行时间，和调用次数，成功次数，成功率，每个错误码的次数
			// 从而算出qps，rt的分布情况等信息，如最大rt，最小rt，平均rt，
			// 90%, 95%, 99%的rt落在哪个范围内
			context.stop();
		}
	}
	
	public <T> Invoker<T> select(List<Invoker<T>> invokers, URL url, Invocation invocation) throws RpcException {
		// TODO Auto-generated method stub
		doGetCompass();
		doFastCompass();
		System.out.println("KKK");
		Random r=new Random();
		int ran=r.nextInt(invokers.size());
		return invokers.get(ran);
		//return invokers.get(0);
	}

//	@Override
//	protected <T> Invoker<T> doSelect(List<Invoker<T>> invokers, URL url, Invocation invocation) {
//		// TODO Auto-generated method stub
//		System.out.println("select of SimpleLoadBalance");
//		Random r=new Random();
//		int ran=r.nextInt(invokers.size());
//		return invokers.get(ran);
//	}

}
