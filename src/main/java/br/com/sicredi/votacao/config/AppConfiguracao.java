package br.com.sicredi.votacao.config;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfiguracao {

    @Bean
    ModelMapper modelMapper() {
		return new ModelMapper();
	}
}
