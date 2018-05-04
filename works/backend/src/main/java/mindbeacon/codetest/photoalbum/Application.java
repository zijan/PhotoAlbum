package mindbeacon.codetest.photoalbum;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ImportResource;

@SpringBootApplication
@ImportResource("classpath:applicationContext.xml")
public class Application {
	private static final Logger logger = LogManager.getLogger(Application.class);
	public static void main(String[] args) {
		System.out.println("Hello World!");
		logger.info("Application is started!");
		SpringApplication.run(Application.class, args);
	}
}
