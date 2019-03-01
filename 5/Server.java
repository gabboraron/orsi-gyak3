
import java.io.*;
import java.util.*;
import java.net.*;

public class Server {
	public static void main(String[] args) throws Exception {
		// protokoll
			// interfesz
		// 0-65535 (1024-65535)
		int PORT = 12345;
		// try-with-resources (AutoCloseable)
		
		Integer nr = 0;
		while (true){
			try (
				ServerSocket ss = new ServerSocket(PORT);
				Socket s = ss.accept();
				Scanner sc = new Scanner(s.getInputStream());
				PrintWriter pw = new PrintWriter(s.getOutputStream());
			) {
				++nr;
				pw.println("You are the " + nr + " th one. End of connection.");
			}
		}
	}
}