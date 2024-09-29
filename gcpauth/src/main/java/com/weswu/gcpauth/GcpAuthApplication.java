package com.weswu.gcpauth;

import com.weswu.gcpauth.config.Configuration;
import com.weswu.gcpauth.service.SATokenService;
// import org.slf4j.Logger;
// import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author weswu
 * @date 2021/05/23
 */
@SpringBootApplication
public class GcpAuthApplication {
	// private static final Logger log = LoggerFactory.getLogger(GcpAuthApplication.class);
	public static void main(String[] args) throws Exception {
		Configuration.load();
		@SuppressWarnings("unused")
		SATokenService saTokenService = new SATokenService();
		//saTokenService.getAccessToken(new TokenReq("android-1101", "xxx", "https://www.googleapis.com/auth/pubsub"));
		SpringApplication.run(GcpAuthApplication.class, args);
	}
}
