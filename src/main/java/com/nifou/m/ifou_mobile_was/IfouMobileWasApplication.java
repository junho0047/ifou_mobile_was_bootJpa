package com.nifou.m.ifou_mobile_was;

import com.nifou.m.ifou_mobile_was.common.ApplicationPropertiesUtil;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class IfouMobileWasApplication {

	public static void main(String[] args) {


		ApplicationPropertiesUtil.loadProperties();

		// 설정 값 가져오기
		String serverPort = ApplicationPropertiesUtil.getProperty("server.port");
		String driverClassName = ApplicationPropertiesUtil.getProperty("spring.datasource.driver-class-name");
		String dataSourceUrl = ApplicationPropertiesUtil.getProperty("spring.datasource.url");
		String username = ApplicationPropertiesUtil.getProperty("spring.datasource.username");
		String password = ApplicationPropertiesUtil.getProperty("spring.datasource.password");

		// application.properties에 설정 값 입력
		System.setProperty("server.port", serverPort);
		System.setProperty("spring.datasource.driver-class-name", driverClassName);
		System.setProperty("spring.datasource.url", dataSourceUrl);
		System.setProperty("spring.datasource.username", username);
		System.setProperty("spring.datasource.password", password);

		SpringApplication.run(IfouMobileWasApplication.class, args);


	}
}
