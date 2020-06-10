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
		init();
		SpringApplication.run(DubboProviderApplication.class, args);
	}



	public static void init() {
		System.out.println(ConfigFields.CONFIG_FILE_NAME);
		init(System.getProperty(ConfigFields.CONFIG_FILE_NAME));
	}

	public static void init(String configFile) {

		LoggerProvider.initLogger();

		Properties config = MetricsIntegrateUtils.parsePropertiesFromFile(configFile);
		if (configFile != null) {
			config.setProperty(ConfigFields.CONFIG_FILE_NAME, configFile);
		}

		if (MetricsIntegrateUtils.isEnabled(config, "com.alibaba.metrics.http_server.start")) {
			startHttpServer();
		}
		MetricsIntegrateUtils.registerAllMetrics(config);
	}

	public static void destroy() {
		if (metricsHttpServer != null) {
			metricsHttpServer.stop();
		}
	}

	private static void startHttpServer() {
		metricsHttpServer = new MetricsHttpServer();
		metricsHttpServer.start();
	}
}
