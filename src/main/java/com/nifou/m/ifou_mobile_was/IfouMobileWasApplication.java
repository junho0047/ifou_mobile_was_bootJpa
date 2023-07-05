package com.nifou.m.ifou_mobile_was;

import com.nifou.m.ifou_mobile_was.common.ApplicationPropertiesUtil;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class IfouMobileWasApplication {

	public static void main(String[] args) {
		SpringApplication.run(IfouMobileWasApplication.class, args);

		String serverPort = ApplicationPropertiesUtil.getProperty("server.port");
		String driverClassName = ApplicationPropertiesUtil.getProperty("spring.datasource.driver-class-name");
		String dataSourceUrl = ApplicationPropertiesUtil.getProperty("spring.datasource.url");
		String username = ApplicationPropertiesUtil.getProperty("spring.datasource.username");
		String password = ApplicationPropertiesUtil.getProperty("spring.datasource.password");

	}
}
