package cn.zifangsky;

import org.springframework.boot.Banner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SsoClientDemoApplication {

	public static void main(String[] args) {
		SpringApplication application = new SpringApplication(SsoClientDemoApplication.class);
		application.setBannerMode(Banner.Mode.CONSOLE);
		application.run(args);
	}
}
