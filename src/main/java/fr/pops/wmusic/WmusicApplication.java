package fr.pops.wmusic;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@ComponentScan({ "fr.pops.wmusic.presentation",
	"fr.pops.wmusic.business.entity",
	"fr.pops.wmusic.business.service",
	"fr.pops.wmusic.persistence" })
@SpringBootApplication
public class WmusicApplication {

	public static void main(String[] args) {
		SpringApplication.run(WmusicApplication.class, args);
	}

}
