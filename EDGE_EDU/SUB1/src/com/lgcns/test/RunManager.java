package com.lgcns.test;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Iterator;
import java.util.Scanner;

public class RunManager {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		
		Scanner scanner = new Scanner(System.in);
		
		while (true) {
			// 값 입력 전에 Console에 표시할 내용. println()이 아니고 print()임.
			System.out.print("VALUE : ");
			
			// 값 받기
			String line = scanner.nextLine();
			
			// 종료 문자면 break
			if ("q".equals(line)) break;
			
			// 값 처리
			String value[] = line.split("#");
			
			String command = value[0];
			String devices[] = value[1].split(",");
			String param = value[2];
			
			//System.out.println(command + " " + param);
			
			String info = read("INFO\\SERVER_COMMAND.TXT");
			String[] lines = info.split("\n");
			for (String linex: lines) {
				String str[] = linex.split("#");
				String device = str[0];
				String dInfo = str[1];
				
				if(device.equals(command)) {
					
					
					if (devices.length == 1) {
						StringBuilder sb = new StringBuilder();
						
						sb.append(devices[0]).append(":").append(dInfo).append("#").append(param);
						
						System.out.println(sb.toString());
						
					}else {
						
						for (int i = 0; i < devices.length; i++) {
							StringBuilder sb = new StringBuilder();
							sb.append(devices[i]).append(":").append(dInfo).append("#").append(param);
							System.out.println(sb.toString());
						}
					}
					
					
				}
			}
			
			//System.out.println(info);
			
			
			
			
			
			
		}
			
		scanner.close();
	
	}
	
	public static String read(String filePath) throws IOException {

		StringBuilder stringBuilder;
		FileReader fileReader = null;
		BufferedReader bufferedReader = null;
		try {
			stringBuilder = new StringBuilder();
			fileReader = new FileReader(filePath);
			bufferedReader = new BufferedReader(fileReader);
			String line;
		while ((line = bufferedReader.readLine()) != null)
			stringBuilder.append(line).append('\n');

		} finally {
			if (bufferedReader != null) try { bufferedReader.close(); } catch (Exception ex) { /* Do Nothing */ }
			if (fileReader != null) try { fileReader .close(); } catch (Exception ex) { /* Do Nothing */ }
		}

		return stringBuilder.toString();
	}

}
