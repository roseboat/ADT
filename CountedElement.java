public class CountedElement<E extends Comparable<E>> implements Comparable<CountedElement<E>> {
	private E element;
	private int count;

	public CountedElement(E e, int count){
		this.element = e;
		this.count = count;
	}
	
	public CountedElement(E e){
		this.element = e;
		this.count = 1;
	}

	public E getElement(){
		return element;
	}
	
	public int getCount() {
		return count;
	}
	
	public void setElement(E e) {
		this.element = e;
	}
	
	public void setCount(int count) {
		this.count = count;
	}
	
	public String toString() {
		String x = element.toString();
		return x;
	}
	
	public int compareTo(CountedElement<E> sC1) {
		if (count < sC1.getCount()) {
			return -1;
		} else if (count > sC1.getCount()) {
			return 1;
		} else
			return 0;
		}
}
