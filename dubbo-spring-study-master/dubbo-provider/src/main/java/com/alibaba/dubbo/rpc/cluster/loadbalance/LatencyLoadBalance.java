package com.alibaba.dubbo.rpc.cluster.loadbalance;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.util.List;
import java.util.Random;

import com.alibaba.dubbo.common.URL;
import com.alibaba.dubbo.rpc.Invocation;
import com.alibaba.dubbo.rpc.Invoker;
import com.alibaba.dubbo.rpc.RpcException;
import com.alibaba.dubbo.rpc.cluster.LoadBalance;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

public class LatencyLoadBalance implements LoadBalance {
	private final int DEFAULT_PORT=8060;

	public <T> Invoker<T> select(List<Invoker<T>> invokers, URL url, Invocation invocation) throws RpcException {
		// TODO Auto-generated method stub
//		System.out.println("select of LatencyLoadBalance");
		Double minValue=null;
		int minIdx=0;
		for(int i=0;i<invokers.size();i++) {
			URL purl = invokers.get(i).getUrl();
			String path = "/metrics/specific?metric=middleware.metrics.rest.url.rt";
			int port_offset = purl.getPort() - 20880;
			int metrics_port = DEFAULT_PORT + port_offset;
			try {
				java.net.URL queryUrl = new java.net.URL("http", purl.getIp(), metrics_port, path);
				System.out.println(queryUrl.toString());
				HttpURLConnection conn = (HttpURLConnection) queryUrl.openConnection();
				conn.setRequestMethod("GET");
				conn.connect();
				if (conn.getResponseCode() == 200) {
//					System.out.println("200");
					InputStream is = conn.getInputStream();
					BufferedReader br = new BufferedReader(new InputStreamReader(is, "UTF-8"));
					StringBuffer sbf = new StringBuffer();
					String temp = null;
					while ((temp = br.readLine()) != null) {
						sbf.append(temp + "\n");
					}
					String result = sbf.toString();
					JSONObject json = JSONObject.parseObject(result);
					JSONObject data = json.getJSONArray("data").getJSONObject(0);
					double value = data.getDoubleValue("value");
//					System.out.println("value " + value);
					if(minValue==null || value<minValue){
						minValue=value;
						minIdx=i;
					}
				}

			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return invokers.get(minIdx);
	}

}
