// Student number : 0908715d, Student name: Rosa Downing
public class CountedElement<E extends Comparable<E>> implements Comparable<CountedElement<E>> {
	private E element;
	private int count;

	// Default constructor
	public CountedElement(E e, int count){
		this.element = e;
		this.count = count;
	}
	
	// Constructor 
	public CountedElement(E e){
		this.element = e;
		this.count = 1;
	}

	// Accessor Methods
	public E getElement(){
		return element;
	}
	
	public int getCount() {
		return count;
	}
	
	public String toString() {

		String x = "(" + element.toString() + "," + count + ")";
		return x;
	}
	
	// Mutator Methods
	public void setElement(E e) {
		this.element = e;
	}
	
	public void setCount(int count) {
		this.count = count;
	}
	
	public void increment() {
		++count;
	}
	
	public void decrement() {
		--count;
	}
	
	// Compare to method which takes a CountedElement<E> object as a parameter
	// Gets the element of each CountedElement object and compares them
	public int compareTo(CountedElement<E> other) {
		if (this.getElement().compareTo(other.getElement()) > 0)
			return 1;
		else if (this.getElement().compareTo(other.getElement()) == 0)
			return 0;
		else
			return -1;
	}
}
