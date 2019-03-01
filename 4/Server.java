
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
		
		Vector<Integer> tmp = new Vector<Integer>();
		while (true){
			try (
				ServerSocket ss = new ServerSocket(PORT);
				Socket s = ss.accept();
				Scanner sc = new Scanner(s.getInputStream());
				PrintWriter pw = new PrintWriter(s.getOutputStream());
			) {
				
				List<String> list = new ArrayList<String>();
				String filename = sc.next();
				readFromFile(list, filename);

				answer(list, pw);
			}
		}
	}

	public static void readFromFile(List<String> list, String filename){
        File file = new File(filename);

        System.out.println("Fajlolvasas");
        try (
            BufferedReader reader = new BufferedReader(new FileReader(file));
        ) {
            String text = null;

            while ((text = reader.readLine()) != null) {
                list.add(text);
            }
        } catch (FileNotFoundException e) {
        	System.out.println("FILE NT FOUND");
        } catch (Exception e) {
            e.printStackTrace();
        }
	}

	public static void answer(List<String> list, PrintWriter pw){
            /*for (nr : tmp) {
		pw.println(nr);
            }*/
            list.forEach((n) -> pw.println(n));
            list.clear();
	}
}