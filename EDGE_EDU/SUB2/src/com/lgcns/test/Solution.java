package com.lgcns.test;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class Solution {
	
	private static Map<String, String> devicesInfoMap; 
	
	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
	
		loadingInfo();
		
		try (Scanner scanner = new Scanner((System.in))) {
			// 값 처리
			String value[] = scanner.next().split("#");
			
			String command = value[0];
			String devices[] = value[1].split(",");
			String param = value[2];
			
			List<String> resultList = new ArrayList<>();
			
			for (String targetDevice : devices) {
				writeFile(String.format("DEVICE/REQ_TO_%S.TXT", targetDevice), String.format("%s#%s", devicesInfoMap.get(command), param));
				Thread.sleep(500);
				String result = readFile(String.format("DEVICE/RES_FROM_%S.TXT", targetDevice));
				resultList.add(result);
			}
			
			System.out.println(String.join(",", resultList));
		}
		
		
	}

	private static void loadingInfo() throws Exception {
		// TODO Auto-generated method stub
		
		devicesInfoMap = new HashMap<String, String>();
		
		try (Scanner scanner = new Scanner(new File("INFO/SERVER_COMMAND.TXT"))) {
			while(scanner.hasNext()) {
				String arr[] = scanner.next().split("#");
				devicesInfoMap.put(arr[0], arr[1]);
			}
		}
		
	}
	
	private static String readFile(String fileName) throws Exception {
		return Files.readAllLines(Paths.get(fileName)).get(0);
	}

	private static void writeFile(String fileName, String content) throws Exception {
		Files.write(Paths.get(fileName), (content + "\n").getBytes(), StandardOpenOption.CREATE);
	}

}
