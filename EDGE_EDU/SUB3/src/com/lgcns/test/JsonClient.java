package com.lgcns.test;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;

import org.eclipse.jetty.client.HttpClient;
import org.eclipse.jetty.client.api.ContentResponse;
import org.eclipse.jetty.client.api.Request;
import org.eclipse.jetty.client.util.StringContentProvider;
import org.eclipse.jetty.http.HttpHeader;
import org.eclipse.jetty.http.HttpMethod;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.stream.JsonReader;

public class JsonClient {

	private static Map<String, String> devicesInfoMap; 
	
	public static void main(String[] args) throws Exception  {
//		String strFileList = getFileList();
		
		HttpClient httpClient = new HttpClient();
		httpClient.start();
		
		
    	//{"command":"CMD_002","targetDevice":["DEVICE_083","DEVICE_015"],"param":"ce3c39e1"}
    	JsonObject jsonObj = new JsonObject();
    	
    	jsonObj.addProperty("command", "CMD_002");
    	
		JsonArray jsonArr = new JsonArray();
		jsonArr.add("DEVICE_083");
		jsonArr.add("DEVICE_015");
		jsonObj.add("targetDevice", jsonArr);
		
    	jsonObj.addProperty("param", "ce3c39e1");

		String httpBody = jsonObj.toString();
		

		Request request = httpClient.newRequest("http://127.0.0.1:8010/fromServer").method(HttpMethod.POST);
		request.header(HttpHeader.CONTENT_TYPE, "application/json");
		request.content(new StringContentProvider(httpBody,"utf-8"));
		ContentResponse contentRes = request.send();
		System.out.println(contentRes.getContentAsString());		
		
		httpClient.stop();
	}
	
//	public static void main(String[] args) throws Exception {
//		// TODO Auto-generated method stub
//		
//		
//	
//		//CMD_002#DEVICE_083,DEVICE_015#ce3c39e1
//		loadingInfo();
//		
//		try (Scanner scanner = new Scanner((System.in))) {
//			// 값 처리
//			String value[] = scanner.next().split("#");
//			
//			String command = value[0];
//			String devices[] = value[1].split(",");
//			String param = value[2];
//			
//			List<String> resultList = new ArrayList<String>();
//			
//			for (String dev : devices) {
//				saveFile(dev, devicesInfoMap.get(command) + "#" + param);
//				Thread.sleep(500);
//				String result = readFile(String.format("DEVICE/RES_FROM_%S.TXT", dev));
//				//System.out.println(String.format("%s:%s#%s", dev, devicesInfoMap.get(command), param));	
//				resultList.add(result);
//			}
//			
//			System.out.println(String.join(",", resultList));
//		}
//		
//		
//	}

	private static String readFile(String filename) throws Exception {
		// TODO Auto-generated method stub
		return Files.readAllLines(Paths.get(filename)).get(0);
	}

	private static void loadingInfo() throws Exception {
		// TODO Auto-generated method stub
		
		devicesInfoMap = new HashMap<String, String>();
		
		Gson gson = new Gson();
		JsonReader reader = new JsonReader(new FileReader("INFO/SERVER_COMMAND.JSON"));
		JsonObject jsonObj = gson.fromJson(reader, JsonObject.class);
		
		JsonArray jsonArr = jsonObj.get("serverCommandInfo").getAsJsonArray();
		for(int inx=0; inx <jsonArr.size(); inx++) {			
			JsonObject jsonobj = jsonArr.get(inx).getAsJsonObject();

			String command = jsonobj.get("command").getAsString();
			String forwardCommand = jsonobj.get("forwardCommand").getAsString();
			System.out.println("command:"+command + "("+forwardCommand+")");
			
			devicesInfoMap.put(command, forwardCommand);
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
