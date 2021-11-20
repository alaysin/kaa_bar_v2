package level.up.kaa_bar.web;

import level.up.kaa_bar.KaaBarApplication;
import level.up.kaa_bar.repo.AppJPAConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@Configuration
@EnableWebMvc
@ComponentScan(basePackages = {"level.up.kaa_bar"}, excludeFilters = {
        @ComponentScan.Filter(
                type = FilterType.ASSIGNABLE_TYPE,
                classes = {
                        AppJPAConfiguration.class,
                        KaaBarApplication.class
                }
        )
})
public class TestWebConfiguration {
}
