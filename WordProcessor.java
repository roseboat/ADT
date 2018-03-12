// Student number : 0908715d, Student name: Rosa Downing

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.*;

public class WordProcessor {
	
	/**
	 * Method to create a String for printing
	 * @param inputSet - a set of elements from a file
	 * @return <E> - a String representation of the parameter inputSet
	 */
	private static <E> String displaySet(Set<E> inputSet) {
	
		StringBuilder display = new StringBuilder();
		int counter = 0;

		// for each element in inputSet get their String representation and add them to
		// the StringBuilder followed by a comma. 
		for (E elem : inputSet) {
			display.append(elem.toString() + ", ");
			counter++;
			if (counter == 5) { // Every 5 elements, produce a new line.
				display.append("\r\n"); 
				counter = 0;
			}
		}
		return display.toString();
	}

	/**
	 * Main method which takes file input and uses a Set ADT to store its elements
	 * @param args
	 */
	public static void main(String[] args) {
		// A set of type String called wordSet
		Set<String> wordSet = new TreeSet<String>();

		// A set of type CountedElement<String> called countedWordSet
		Set<CountedElement<String>> countedWordSet = new TreeSet<CountedElement<String>>();

		// Prepares a Scanner and BufferedReader object
		Scanner in;
		BufferedReader readFile;

		// for each input file, do the following:
		for (int i = 0; i < args.length; i++) {
			
			// Get the file name passed as an argument
			String fileName = args[i].substring(1, args[i].length() - 1);

			try {
				readFile = new BufferedReader(new FileReader(fileName));
				in = new Scanner(readFile);
				while (in.hasNext()) { // while the Scanner has a new object
					// for each word w
					String w = in.next();
					// if wordset doesnt contain w add w to wordset
					if (!wordSet.contains(w)) {
						wordSet.add(w);
						// add new element to countedWordSet
						countedWordSet.add(new CountedElement<String>(w));
					} else {
						// increment numeric part of element in countedWordSet containing w
						Iterator<CountedElement<String>> it = countedWordSet.iterator();
						while (it.hasNext()) {
							CountedElement<String> x = it.next();
							String elem = x.getElement();
							if (elem.equals(w)) {
								int count = x.getCount();
								x.setCount(++count);
							}
						}
					}
				}
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
		} 
		// Calls the displaySet method
		// Prints out a String representation of all CountedElements
		System.out.println(displaySet(countedWordSet));
	}
}


