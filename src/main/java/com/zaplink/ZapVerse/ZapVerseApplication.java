package com.zaplink.ZapVerse;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import io.github.cdimascio.dotenv.Dotenv;

@SpringBootApplication
public class ZapVerseApplication {

	public static void main(String[] args) {
		Dotenv dotenv = Dotenv.load();

		// Set system properties so Spring Boot can resolve placeholders
		System.setProperty("SUPABASE_DB_URL", dotenv.get("SUPABASE_DB_URL"));
		System.setProperty("SUPABASE_DB_USERNAME", dotenv.get("SUPABASE_DB_USERNAME"));
		System.setProperty("SUPABASE_DB_PASSWORD", dotenv.get("SUPABASE_DB_PASSWORD"));

		SpringApplication.run(ZapVerseApplication.class, args);
	}

}