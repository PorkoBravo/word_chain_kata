package com.disel.codingdojo;

import java.net.URISyntaxException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.disel.codingdojo.filereaders.FileReader;

@SpringBootApplication
public class KataWordChainApplication implements CommandLineRunner  {

	@Autowired
	FileReader reader;

	public static void main(String[] args) {
		SpringApplication.run(KataWordChainApplication.class, args);
	}
	
	@Override
    public void run(String... args) throws URISyntaxException {
		System.out.println("Benvidos á primeira Kata do equipo DISEL.\nURL: http://codekata.com/kata/kata19-word-chains/");
		System.out.println("O diccionario ten " +reader.readDictionary().count() + " termos (ES) dispoñibles.");
		
	}
}
