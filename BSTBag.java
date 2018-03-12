// Student number : 0908715d, Student name: Rosa Downing
import java.util.Iterator;
import java.util.NoSuchElementException;


public class BSTBag<E extends Comparable<E>> implements Bag<E> {

	private Node<E> root;

	public BSTBag() {
		root = null;
	}

	// Inner Node Class
	public static class Node<E extends Comparable<E>> {
		protected CountedElement<E> elem;
		protected Node<E> left, right;
		protected boolean present; // boolean value represents whether a Node's CountedElement has 1 or more
									// elements

		// Node constructor
		protected Node(E element) {
			this.elem = new CountedElement<E>(element);
			this.present = true;
			this.left = null;
			this.right = null;
		}
	}

	/////////// Accessors ////////////

	// A BSTBag object is empty if its root node is set to null.
	// Returns a boolean representing whether or not the root node is null.
	public boolean isEmpty() {
		if (root == null) {
			return true;
		} else
			return false;
	}

	// size() method which calls the another recursive size method
	public int size() {
		return (size(root));
	}
	
	// Recursive size method which gets the count of every element in the tree
	private int size(Node<E> nodeSize) {
		if (nodeSize == null) {
			return 0;
		} else {
			return (nodeSize.elem.getCount()) + (size(nodeSize.left)) + (size(nodeSize.right));
		}
	}

	// Method which calculates whether one BSTBag object is equal to another,
	// containing the same amount of the same elements. Returns a boolean which
	// represents whether the two BSTBags are the same or not (true or false).
	public boolean equals(Bag<E> that) {

		// Instantiates two Iterator objects for each bag
		Iterator<E> x = this.iterator();
		Iterator<E> y = that.iterator();

		// While both iterators have a next element, do the following:
		while (x.hasNext() && y.hasNext()) {
			// If the next element in x is not equal to the next element of y return false
			if (!x.next().equals(y.next())) {
				return false;
			}
		}
		// If both iterator no longer have elements and all of them have matched up,
		// return true.
		return true;
	}
	
	// Method to see if a BSTBag object contains an element of the declared type E
	public boolean contains(E element) {
		int direction = 0;
		BSTBag.Node<E> con = new BSTBag.Node<E>(element); // new node object is given the element
		BSTBag.Node<E> parent = null, curr = root;
		// Repeat until a boolean is returned
		for (;;) {
			// if the root is null then return false
			if (curr == null) {
				return false;
				// else find the element's position in the tree
			} else {
				direction = con.elem.compareTo(curr.elem);
				// if curr == con and there is one or more elements (present = true) return true
				if (direction == 0) {
					if (curr.present == true)
						return true;
					else if (curr.present == false)
						return false;
				}
			}
			// If the elements did not match, move position and loop again
			parent = curr;
			if (direction < 0)
				curr = parent.left;
			else
				curr = parent.right;
		}
	}

	/////////// Transformers ////////////

	public void clear() {
		root = null;
	}

	// Method adds and element to the BSTBag
	public void add(E element) {
		int direction = 0;
		BSTBag.Node<E> newNode = new BSTBag.Node<E>(element);
		BSTBag.Node<E> curr = root, parent = null;
		for (;;) {
			// if curr is null do the following:
			if (curr == null) {
				// if the root is null, set newNode to the root
				if (root == null)
					root = newNode;
				// if the direction is less than 0, set newNode to be the parent's left node
				else if (direction < 0)
					parent.left = newNode;
				// if the direction is more than 0, set newNode to be the parent's right node
				else if (direction > 0)
					parent.right = newNode;
				// exit the loop
				return;
			}
			// if curr was not null do the following and loop again:
			// compare the newNode's element with cur
			direction = newNode.elem.compareTo(curr.elem);
			
			// if the newNode's element matches curr's element, increment curr's element and
			// set its boolean present to true
			if (direction == 0) {
				curr.elem.increment();
				curr.present = true;
				return;
			}
			// if newNode and curr do not match, find the next position to move to in the
			// tree
			parent = curr;
			if (direction < 0)
				curr = curr.left;
			else
				curr = curr.right;
		}
	}

	// Method removes an element from the BSTBag
	public void remove(E element) {
		int direction = 0;
		BSTBag.Node<E> del = new BSTBag.Node<E>(element);
		BSTBag.Node<E> parent = null, curr = root;
		for (;;) {
			// if curr is null, the tree is empty so return
			if (curr == null)
				return;
			// else, compare the element to be deleted with the curr (current) element in
			// the tree
			direction = del.elem.compareTo(curr.elem);

			// if the elements match, get the count of the element
			if (direction == 0) {
				int count = curr.elem.getCount();
				// if the count is 1 or more, decrement the element's count
				if (count >= 1) {
					curr.elem.decrement();
					// if the count was 1 or less before decrementing set curr's present boolean to
					// false
					// the item is no longer considered 'present' in the BSTBag
					if (count <= 1) {
						curr.present = false;
					}
					return;
				}
			}
			// if the element to be deleted and curr did not match, find the next position
			// to move to in the
			// tree
			parent = curr;
			if (direction < 0)
				curr = parent.left;
			else
				// direction > 0
				curr = parent.right;
		}
	}

	// Method returns an Iterator object
	public Iterator<E> iterator() {

		return new InOrderIterator();
	}

	// Inner class which creates an Iterator object.
	private class InOrderIterator implements Iterator<E> {

		// Instance variable
		private Stack<BSTBag.Node<E>> track;

		// Constructor for an Iterator creates a new LinkedStack and loops through the
		// BSTBag's left subtree, adding each element to the Stack as many times as its
		// count.
		private InOrderIterator() {
			track = new LinkedStack<BSTBag.Node<E>>();
			for (BSTBag.Node<E> curr = root; curr != null; curr = curr.left) {
				int count = curr.elem.getCount();
				for (int i = 0; i < count; i++) {
					if (count > 0)
						track.push(curr);
				}
			}
		}

		// Returns a boolean corresponding to whether or not the Stack track is empty
		public boolean hasNext() {
			return (!track.empty());
		}

		// Method to return the next element in a stack 
		public E next() {
			if (track.empty())
				throw new NoSuchElementException();

			
			BSTBag.Node<E> place = track.pop();
			for (BSTBag.Node<E> curr = place.right; curr != null; curr = curr.left) {
				int count = curr.elem.getCount();
				for (int i = 0; i < count; i++) {
					if (count > 0)
						track.push(curr);
				}
			}
			return place.elem.getElement();
		}
	}

}
