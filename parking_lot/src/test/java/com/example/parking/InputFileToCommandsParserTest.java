package com.example.parking;

import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import com.example.parking.parser.InputFileToCommandsParser;

public class InputFileToCommandsParserTest {
	
	@Test
	public void test1() {
		
		List<String> expectedCommands = new ArrayList<String>();
		expectedCommands.add("create_parking_lot 6");
		expectedCommands.add("park KA-01-HH-1234");
		expectedCommands.add("park KA-01-HH-9999");
		expectedCommands.add("leave KA-01-HH-3141 4");
		expectedCommands.add("status");
		
		String fileName = "file_input.txt";
		String destinationFilePath = getClass().getClassLoader().getResource(fileName).getFile().toString();
		
		InputFileToCommandsParser fileParser = new InputFileToCommandsParser(destinationFilePath);
		
		List<String> commands = new ArrayList<String>();
		try {
			commands = fileParser.parseToCommandList();
		} catch (Exception e) {
			e.printStackTrace();
			return;
		}
		boolean allCommandsEqual = false;
		int cmdIdx = 0;
		
		while (cmdIdx < commands.size() && cmdIdx < expectedCommands.size()) {
			if (cmdIdx == 0)	
				allCommandsEqual = true;
			if (!commands.get(cmdIdx).equals(expectedCommands.get(cmdIdx))) {
				allCommandsEqual = false;
				break;
			}
			cmdIdx++;
		}
		
		assertTrue(allCommandsEqual && cmdIdx == commands.size() && cmdIdx == expectedCommands.size());
		
		return;
	}
	
}
