
//import classes for file input - scanner etc.
import java.util.*;
import java.util.Set;
//import implementing set (eg. TreeSet)


public class WordProcessor {
	private static <E> String displaySet(Set<E> inputSet){
		//implement this static method to create a
		// String representation of set - 5 comma separated elements per line
		// assume that type E has a toString method
		StringBuilder display = new StringBuilder();
		
		for (E e : inputSet) {
			display.append(e + ", ");}
		return display.toString();
		
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		//create a set of type String called wordSet
		Set <String> wordSet = new TreeSet <String>();
		
		//create a set of type CountedElement<String> called countedWordSet 
		Set <CountedElement <String>> countedWordSet = new TreeSet <CountedElement <String>>();
		
		//for each input file (assume 3 arguments, each the name of a file)
		//  for each word w
		//     if wordset doesnt contain w:
		//        add w to wordset
	    //        add new element to countedWordSet
		//     else
		//        increment numeric part of element in countedWordSet containing w
		
		for (String w : args)
			if (!wordSet.contains(w)) {
				wordSet.add(w);
				countedWordSet.add(new CountedElement<String> (w));
			}
			else {
				Iterator <CountedElement <String>> it = countedWordSet.iterator();
				while (it.hasNext()) {
					CountedElement <String> x = it.next();
					String elem = x.getElement();
					if (elem.equals(w)) {
						int count = x.getCount();
						x.setCount(count++);
					}
				}
			}
	
	System.out.println(displaySet(countedWordSet));

	}
}
