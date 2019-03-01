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
		
		Vector<Integer> tiles = new Vector<Integer>(9);
		for (Integer jdx = 0; jdx<9; ++jdx) {
			//tiles.set(jdx, null);
			tiles.add(null);
		}
		Integer idx = 0;
		Integer coordX = 0;
		Integer coordY = 0;
		Boolean done = false;
		while (true){
			try (
				ServerSocket ss = new ServerSocket(PORT);
				Socket s = ss.accept();
				Scanner sc = new Scanner(s.getInputStream());
				PrintWriter pw = new PrintWriter(s.getOutputStream());
			) {
				printTable(tiles, pw);
				while(sc.hasNextInt() && !done){
					Integer number = sc.nextInt();
					if (idx == 0) {
						coordX = number;
						++idx;
					} else if(idx == 1){
						coordY = number;
						idx = 0;
						setNext(tiles, coordX, coordY, pw);
						done = checkIfDone(tiles, pw);
					}else{
						pw.println("ERROR");
					}
		//				answer(idx, pw);
					pw.println("");
					pw.flush();
				}
			}
		}
	}

	public static void setNext(Vector<Integer> tiles, Integer x, Integer y, PrintWriter pw){
		Integer coord = 0;
		coord = (x-1)*3+y-1;
		if(tiles.get(coord) == null ){
			tiles.set(coord, 1);
		} else {
			answer("Already occupied!", pw);
		} 
		printTable(tiles, pw);
	}
	
	public static Boolean checkIfDone(Vector<Integer> tiles, PrintWriter pw){
		if((tiles.get(1) != null) && (tiles.get(2) != null) && (tiles.get(3) != null)){
			pw.println("YOU HAVE WON in 1 row!");
			pw.flush();
			return true;
		} else if((tiles.get(4) != null) && (tiles.get(5) != null) && (tiles.get(6) != null)){
			pw.println("YOU HAVE WON in 2 row!");
			pw.flush();
			return true;
		} else if((tiles.get(7) != null) && (tiles.get(8) != null) && (tiles.get(9) != null)){
			pw.println("YOU HAVE WON in 3 row!");
			pw.flush();
			return true;
		} else if((tiles.get(1) != null) && (tiles.get(4) != null) && (tiles.get(7) != null)){
			pw.println("YOU HAVE WON in 1 column!");
			pw.flush();
			return true;
		} else if((tiles.get(2) != null) && (tiles.get(5) != null) && (tiles.get(8) != null)){
			pw.println("YOU HAVE WON in 2 column!");
			pw.flush();
			return true;
		} else if((tiles.get(3) != null) && (tiles.get(6) != null) && (tiles.get(9) != null)){
			pw.println("YOU HAVE WON in 3 column!");
			pw.flush();
			return true;
		} else if((tiles.get(1) != null) && (tiles.get(4) != null) && (tiles.get(9) != null)){
			pw.println("YOU HAVE WON in diagonal from left!");
			pw.flush();
			return true;
		} else if((tiles.get(3) != null) && (tiles.get(4) != null) && (tiles.get(7) != null)){
			pw.println("YOU HAVE WON in diagonal from right!");
			pw.flush();
			return true;
		} else {
			return false;
		}
	}


	public static void printTable(Vector<Integer> tiles, PrintWriter pw){
		Integer rownr = 1;
		for (Integer tile : tiles) {
			if (tile == null) {
				pw.print("_");
				pw.flush();
			} else {
				pw.print("X");
				pw.flush();
			}
			if(rownr%3 == 0){
				pw.println("");
				pw.flush();
			}
			++rownr;
		}
	}

	public static void answer(String nr, PrintWriter pw){
		pw.println(nr);
		pw.flush();
	}
}