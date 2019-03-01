import java.io.*;
import java.util.*;
import java.net.*;

public class Kliens {

    public static void main(String[] args) throws Exception {
        String MACHINE = "localhost";
        // String MACHINE = "127.0.0.1";
        // String MACHINE = "::1";
        int PORT = 12345;
        try (
                Socket s = new Socket(MACHINE, PORT);
                Scanner sc = new Scanner(s.getInputStream());
                PrintWriter pw = new PrintWriter(s.getOutputStream());) {
            /*pw.println("6");
			pw.flush();
                        
			pw.println("2");
			pw.flush();
                        
                        pw.println("a");
			pw.flush();*/

            System.out.println("Kliens kuld");
            pw.println("files.txt");
            pw.flush();
            
            while (sc.hasNext()) {
                String valasz = sc.next();
                System.out.println(valasz);
            }
        }
    }
}