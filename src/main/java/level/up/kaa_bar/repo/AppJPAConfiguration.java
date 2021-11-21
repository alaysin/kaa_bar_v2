package level.up.kaa_bar.repo;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
//@EnableJpaRepositories(basePackages = "level.up.kaa_bar.model")
@EnableTransactionManagement
//@EntityScan("level.up.kaa_bar.model")
public class AppJPAConfiguration {
}
