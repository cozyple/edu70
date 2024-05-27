package com.lgcns.test;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class RunManager {

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
			
			List<String> resultList = new ArrayList<String>();
			
			for (String dev : devices) {
				saveFile(dev, devicesInfoMap.get(command) + "#" + param);
				Thread.sleep(500);
				String result = readFile(String.format("DEVICE/RES_FROM_%S.TXT", dev));
				//System.out.println(String.format("%s:%s#%s", dev, devicesInfoMap.get(command), param));	
				resultList.add(result);
			}
			
			System.out.println(String.join(",", resultList));
		}
		
		
	}

	private static String readFile(String filename) throws Exception {
		// TODO Auto-generated method stub
		return Files.readAllLines(Paths.get(filename)).get(0);
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
	
	private static void saveFile(String deviceName, String contents) throws IOException {
		// Create Folder
		File destFolder = new File("DEVICE");
		if (!destFolder.exists()) {
			destFolder.mkdirs();
		}

		// File Writing
		String strFilename = destFolder + "//REQ_TO_" + deviceName + ".TXT";
		FileWriter fw = null;
		
		try {
			fw = new FileWriter(strFilename, true);
			fw.write(contents + "\r\n");
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (fw != null) { fw.close(); }
		}
	}
	

}
