package com.lgcns.test;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.ServerConnector;
import org.eclipse.jetty.servlet.ServletHandler;

public class RunManager {
	
		
//	public static void main(String[] args) throws Exception {
//		
//		Server server = new Server();
//		ServerConnector http = new ServerConnector(server);
//		http.setHost("127.0.0.1");
//		http.setPort(8010);
//		server.addConnector(http);
//
//		ServletHandler servletHandler = new ServletHandler();
//		servletHandler.addServletWithMapping(MyServlet.class, "/");	
//		server.setHandler(servletHandler);
//
//		server.start();
//		server.join();
//	}
	
	public static void main(String[] args) throws Exception {
//		Solution solution = new Solution();
//		solution.run();
		
		
		List<String> list = Files.readAllLines(Paths.get("INFO/SERVER_COMMAND.JSON"));
		
		for (String string : list) {
			System.out.println(string);
		}
//		
		String str = String.join("", list);
		System.out.println(str);
	
	}
}
