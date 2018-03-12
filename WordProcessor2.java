
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.*;

public class WordProcessor2 {

	public static void main(String[] args) {
		
		BSTBag<String> shoppingBag = new BSTBag<String>();
		BSTBag<String> shoppingBag2 = new BSTBag<String>();
		int wordCounter = 0;
		Scanner in;
		BufferedReader readFile;

		// for each input file (assume 3 arguments, each the name of a file)
		for (int i = 0; i < args.length; i++) {
			String fileName = args[i].substring(1, args[i].length() - 1);

			try {
				readFile = new BufferedReader(new FileReader(fileName));
				in = new Scanner(readFile);
				while (in.hasNext()) {
					String w = in.next();
					System.out.println(w);
					wordCounter++;

					shoppingBag.add(w);
					shoppingBag2.add(w);
				}
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
		}
		System.out.println(shoppingBag2.equals(shoppingBag));
		shoppingBag2.remove("ant");
		System.out.println(shoppingBag2.size());
		System.out.println(shoppingBag2.equals(shoppingBag));
		shoppingBag2.remove("cow");
		System.out.println(shoppingBag2.size());
		System.out.println(shoppingBag2.equals(shoppingBag));
		shoppingBag2.add("cow");
		System.out.println(shoppingBag2.size());
		System.out.println(shoppingBag2.equals(shoppingBag));
		shoppingBag2.add("ant");
		System.out.println(shoppingBag2.size());
		System.out.println(shoppingBag2.equals(shoppingBag));
	}
}


