package sdp.shop.now.dao.config;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@EntityScan("sdp.shop.now.dao.entity")
@EnableJpaRepositories("sdp.shop.now.dao.repository")
public class JPAConfig {

}
