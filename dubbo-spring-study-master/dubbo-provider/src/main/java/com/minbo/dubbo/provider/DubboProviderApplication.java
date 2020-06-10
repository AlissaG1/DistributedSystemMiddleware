package com.minbo.dubbo.provider;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ImportResource;
import com.alibaba.metrics.integrate.ConfigFields;
import com.alibaba.metrics.integrate.LoggerProvider;
import com.alibaba.metrics.integrate.MetricsIntegrateUtils;
import com.alibaba.metrics.rest.server.MetricsHttpServer;

import java.util.Properties;

@SpringBootApplication
// 使用 providers.xml 配置
@ImportResource(value = { "classpath:providers.xml" })
public class DubboProviderApplication {
	private static MetricsHttpServer metricsHttpServer = null;

	public static void main(String[] args) {
		Bootstrap.init();
		SpringApplication.run(DubboProviderApplication.class, args);
	}
}
