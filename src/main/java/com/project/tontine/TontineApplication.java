package com.project.tontine;

import com.project.tontine.service.SchemaService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.List;
import java.util.Optional;

@SpringBootApplication
public class TontineApplication implements CommandLineRunner
{
	private final SchemaService schemaService;

	public TontineApplication(SchemaService schemaService){
        this.schemaService = schemaService;
    }

	@Override
	public void run(String... args) throws Exception
	{
		schemaService.createSchema("global_schema");
		schemaService.generateTables();

		// log.info("""
		
		
		// 111
		
		
		// """);
	}

    public static void main(String[] args) {
		SpringApplication.run(TontineApplication.class, args);
	}
}