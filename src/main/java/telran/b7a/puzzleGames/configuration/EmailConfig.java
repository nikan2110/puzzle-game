package telran.b7a.puzzleGames.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.SimpleMailMessage;

@Configuration
public class EmailConfig {
	
	String addresFrom;
	
	@Bean
	public SimpleMailMessage emailTemplate() {
		SimpleMailMessage message = new SimpleMailMessage();
		message.setTo("user@email.com");
		message.setFrom("nikan2110@gmail.com");
		message.setSubject("Your result");
		message.setText("Template text");
		return message;
	}
	
}
