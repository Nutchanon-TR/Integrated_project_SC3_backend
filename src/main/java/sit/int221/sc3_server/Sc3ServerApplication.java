package sit.int221.sc3_server;

import jakarta.annotation.PostConstruct;
import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.ui.ModelMap;
import sit.int221.sc3_server.utils.ListMapper;

import java.util.Locale;
import java.util.TimeZone;

@SpringBootApplication
public class Sc3ServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(Sc3ServerApplication.class, args);
	}


	@Bean
	public ListMapper listMapper(){
		return ListMapper.getInstance();
	}
}
