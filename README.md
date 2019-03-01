# Gyak3

## Feladat 4
> A kliens átküld egy fájlnevet a szervernek. A szerver küldje vissza a fájl tartalmát soronként, ha a fájl létezik, különben pedig egy szöveges hibaüzenetet.

Megoldás: `4` es mappa.
Lényeges rész, a fájlolvasás a visszaküldés:
````Java
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
		        pw.println(nr);
            list.forEach((n) -> pw.println(n));
            list.clear();
	}
````

## feladat 5
> A szerver tárolja el, hogy hány kliens kapcsolódott már hozzá. Amikor egy kliens kapcsolódik, a szerver írja ezt vissza neki, majd rögtön bontsa a kapcsolatot, és várakozzon újabb kliensre.

Megoldás: `5`-ös mappa.
Lényeges rész a ciklus elhelyezése
````Java
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
````
## feladat 6
> A szerver tároljon el egy számot, ennek értéke legyen kezdetben `0`. Miután kapcsolódott, a kliens egymás után egész számokat küld át a szervernek; a szerver a kapott értékkel megnöveli a benne tárolt értéket, és ezt át is küldi a kliensnek. A kliens ki is léphet (pl. ha nullát küldene), ekkor a szerver kezdjen várakozni egy újabb klienstől jövő kapcsolatra. A tárolt szám maradjon meg, ne törlődjön.

Megoldás `6`-os mappa.
Lényeges rész:
````Java
Integer nr = 0;
		while (true){
			try (
				ServerSocket ss = new ServerSocket(PORT);
				Socket s = ss.accept();
				Scanner sc = new Scanner(s.getInputStream());
				PrintWriter pw = new PrintWriter(s.getOutputStream());
			) {
				while(sc.hasNextInt()){
					Integer number = sc.nextInt();
					nr += number;
					if(number == 0){
						answer(nr, pw);
					}
				}
			}
		}
````

## feladat amoba
> játszunk amőbát a szerverrel, mi küldünk két koordinátát `x` és `y` és a server egy vectoron eltáárolja egy 3X3-mas játéktábla adatait, mindig vissszaküldi, hogy épp, hogy állunk, ha győztünk vége, szakítja a kapcsolatot.

Megoldás `amoba` mappa.
Lényeges rész a megfelelő koordinátk kiszámolása és a tábla kirajtzolása:
````Java
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
````

# Fontos info
> Ajánlott a server socketet, a portot `ServerSocket ss = new ServerSocket(PORT);` még a program elején lefoglalni, a hallgatózás előtt, mert ha utánna foglaljuk le akkor nem enged másokat kapcsolódni amí] mi vagyunk a serveren.
