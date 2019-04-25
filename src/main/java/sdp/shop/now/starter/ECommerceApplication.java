package sdp.shop.now.starter;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

@SpringBootApplication(scanBasePackages = "sdp.shop.now.*")
public class ECommerceApplication extends SpringBootServletInitializer {

	public static void main(String[] args) {
		SpringApplication.run(ECommerceApplication.class, args);
	}
	
	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(ECommerceApplication.class);
	}
}
