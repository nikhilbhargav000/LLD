package com.example.parking.parser;

import java.io.BufferedReader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class InputFileToCommandsParser implements IInputFileToCommandParser {
	
	private String destinationFilePath;
	
	public InputFileToCommandsParser(String destinationFilePath) {
		this.destinationFilePath = destinationFilePath;
	}

	public List<String> parseToCommandList() throws Exception {
		
		List<String> commands = new ArrayList<String>();
		
		BufferedReader bReader = Files.newBufferedReader(Paths.get(destinationFilePath));
		String line = null;
		while ((line = bReader.readLine()) != null) {
			commands.add(line);
		}
		
		bReader.close();
		return commands;
	}
	
}
