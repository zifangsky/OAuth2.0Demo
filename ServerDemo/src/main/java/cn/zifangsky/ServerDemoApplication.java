package cn.zifangsky;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@ServletComponentScan
@EnableAsync
@MapperScan("cn.zifangsky.mapper")
public class ServerDemoApplication {

	public static void main(String[] args) {
		SpringApplication application = new SpringApplication(ServerDemoApplication.class);
		application.run(args);
	}
}
