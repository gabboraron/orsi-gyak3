import java.io.*;
import java.util.*;
import java.net.*;

public class Kliens {

    public static void main(String[] args) throws Exception {
        String MACHINE = "localhost";
        // String MACHINE = "127.0.0.1";
        // String MACHINE = "::1";
        int PORT = 12345;
                Socket s = new Socket(MACHINE, PORT);                    //telefon
        try (
                Scanner sc = new Scanner(s.getInputStream());            //telefonálunk
                PrintWriter pw = new PrintWriter(s.getOutputStream());) {//vonal
                //System.out.println("Connecting to server...");
                
                //Integer tmp = 0;
                for(int idx = 1; idx < 10; ++idx){
                	tmp +=idx;
                	pw.println(idx);
                	pw.flush();
                //	System.out.println(tmp);
                }
                

                System.out.println("valasz");
                pw.println(0);
                pw.flush();
                
                //while (sc.hasNextLine()) {
                    String answer = sc.nextLine();
                    System.out.println(answer);
                //}
            }
    }
}