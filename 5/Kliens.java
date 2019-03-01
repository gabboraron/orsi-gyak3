import java.io.*;
import java.util.*;
import java.net.*;

public class Kliens {

    public static void main(String[] args) throws Exception {
        String MACHINE = "localhost";
        // String MACHINE = "127.0.0.1";
        // String MACHINE = "::1";
        int PORT = 12345;
        for (int idx=0; idx<10; idx++) {
                Socket s = new Socket(MACHINE, PORT);                    //telefon
        try (
                Scanner sc = new Scanner(s.getInputStream());            //telefonálunk
                PrintWriter pw = new PrintWriter(s.getOutputStream());) {//vonal
                System.out.println("Connecting to server...");
                pw.flush();
                
                while (sc.hasNextLine()) {
                    String answer = sc.nextLine();
                    System.out.println(answer);
                }
            }

        }
    }
}