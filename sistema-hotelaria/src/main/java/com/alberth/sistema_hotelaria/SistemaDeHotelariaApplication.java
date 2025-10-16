package com.alberth.sistema_hotelaria;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@SpringBootApplication
public class SistemaDeHotelariaApplication {

	public static void main(String[] args) {
		SpringApplication.run(SistemaDeHotelariaApplication.class, args);
	}
	@GetMapping("/")
	public String index() {
		return "index";
	}

}
