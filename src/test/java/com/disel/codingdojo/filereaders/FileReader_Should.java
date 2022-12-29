package com.disel.codingdojo.filereaders;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import java.io.InputStream;
import java.net.URISyntaxException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class FileReader_Should {
	
	@Autowired
	FileReader fileReader;

	@Test
	public void read_all_the_file() {
	    String expected = "Hola soy un archivo de prueba! Tildes: á y eñes.\nSegunda línea\n";

	    InputStream inputStream = fileReader.getClass().getResourceAsStream("/test_file.txt");
	    String content = fileReader.readFile(inputStream);

	    assertEquals(expected, content);
	}
	
	@Test
	public void read_all_the_file_as_stream() throws URISyntaxException {
	    String expected = "Hola soy un archivo de prueba! Tildes: á y eñes.\nSegunda línea";

	    Path path = Paths.get(getClass().getClassLoader().getResource("test_file.txt").toURI());
	    Stream<String> fileStream = fileReader.readFileAsStream(path);
	    String content = fileStream.collect(Collectors.joining("\n"));
	    
	    assertEquals(expected, content);
	}
	
	@Test
	public void return_null() {
		 InputStream inputStream = fileReader.getClass().getResourceAsStream("NO_FILE");
		 String content = fileReader.readFile(inputStream);
		 
		 assertNull(content);
	}
}
