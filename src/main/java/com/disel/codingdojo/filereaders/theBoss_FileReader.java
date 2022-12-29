package com.disel.codingdojo.filereaders;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URISyntaxException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Stream;

import org.springframework.stereotype.Component;

@Component
public class theBoss_FileReader implements FileReader {
	
	public final static String DICTIONARY_FILE = "diccionario.txt";
	

	@Override
	public String readFile(InputStream inputStream) {
		try {
			return this.readFromInputStream(inputStream);
		} catch (Exception e) {
			System.out.println("Error reading file");
			return null;
		}
	}
	
	@Override
	public Stream<String> readFileAsStream(Path path) {
		try {
			return Files.lines(path, Charset.forName("ISO-8859-1"));
		} catch (Exception e) {
			System.out.println("Error reading file");
			return null;
		}
	}
	
	private String readFromInputStream(InputStream inputStream) throws IOException {
		if(inputStream == null) {
			throw new IOException();
		}
		StringBuilder resultStringBuilder = new StringBuilder();
		try (BufferedReader br = new BufferedReader(new InputStreamReader(inputStream, "ISO-8859-1" ))) {
			String line;
			while ((line = br.readLine()) != null) {
				resultStringBuilder.append(line).append("\n");
			}
			br.close();
		}
		return resultStringBuilder.toString();
	}

	@Override
	public Stream<String> readDictionary() {
		Stream<String> dictionary = Stream.empty();
		try {
			Path path = Paths.get(getClass().getClassLoader().getResource(DICTIONARY_FILE).toURI());
			dictionary = this.readFileAsStream(path);
		} catch (URISyntaxException e) {
			System.out.println("Error reading file: "+e);
		}
		return dictionary;
	}
}
