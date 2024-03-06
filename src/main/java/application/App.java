package application;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import java.util.logging.Logger;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.env.Environment;

@SpringBootApplication
@EnableConfigurationProperties
public class App {
	private static final Logger LOGGER = Logger.getLogger(App.class.getName());

	public static void main(String[] args) {
		ConfigurableApplicationContext context = SpringApplication.run(App.class, args);
		Environment env = context.getEnvironment();

		LOGGER.info("Datasource URL: " + env.getProperty("spring.datasource.url"));
		LOGGER.info("Datasource Username: " + env.getProperty("spring.datasource.username"));
		LOGGER.info("Datasource Password: " + env.getProperty("spring.datasource.password"));
	}

}
