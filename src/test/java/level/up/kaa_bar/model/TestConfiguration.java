package level.up.kaa_bar.model;

import level.up.kaa_bar.repo.AppJPAConfiguration;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@Configuration
@ComponentScan(
        //basePackages = "level.up.kaa_bar.repo",
        excludeFilters = {
        @ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, classes = AppJPAConfiguration.class)
})
@EnableJpaRepositories(basePackages = "level.up.kaa_bar.repo")
@EnableTransactionManagement
@EnableWebMvc
@EnableAutoConfiguration
//@EntityScan("level.up.kaa_bar.repo")
public class TestConfiguration {
}
