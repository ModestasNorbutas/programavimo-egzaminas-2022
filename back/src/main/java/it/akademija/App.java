package it.akademija;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

/**
 * 
 * Main application runner class
 * 
 * @author 
 *
 */
@SpringBootApplication
public class App extends SpringBootServletInitializer {
	
	
	/**
	 * 
	 * Main method
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		SpringApplication.run(App.class, args);
	}

	/**
	 * Spring configuration
	 * 	
	 */
	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
		return builder.sources(App.class);
	}

}