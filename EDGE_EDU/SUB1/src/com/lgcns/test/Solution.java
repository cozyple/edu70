package com.lgcns.test;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Scanner;

import javax.print.attribute.HashAttributeSet;

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
			
			for (String dev : devices) {
				
				System.out.println(String.format("%s:%s#%s", dev, devicesInfoMap.get(command), param));	
				
			}
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

}
