package hr.fer.zemris.java.custom.collections;

/**
 * Implementation of a stack-like collection which uses ArrayIndexedCollection
 * as its internal storage of elements.
 * 
 * @author labramusic
 *
 */
public class ObjectStack {

	/**
	 * Internal storage of elements.
	 */
	private ArrayIndexedCollection array = new ArrayIndexedCollection();

	/**
	 * Checks if stack contains any elements, returns true if empty.
	 * 
	 * @return true if stack contains no elements, false otherwise
	 */
	public boolean isEmpty() {
		return array.isEmpty();
	}

	/**
	 * Returns the number of objects currently on this stack.
	 * 
	 * @return number of objects on stack
	 */
	public int size() {
		return array.size();
	}

	/**
	 * Pushes the given value on the stack. Throws IllegalArgumentException if a
	 * null value is attempted to be added.
	 * 
	 * @param value
	 *            object to be pushed on the stack
	 */
	public void push(Object value) {
		array.add(value);
	}

	/**
	 * Removes the last value pushed on the stack and returns it. Throws
	 * EmptyStackException if the stack is empty when this method is called.
	 * 
	 * @return value at the top of the stack
	 */
	public Object pop() {
		int size = size();
		if (isEmpty()) {
			throw new EmptyStackException();
		}
		Object value = array.get(size - 1);
		array.remove(size - 1);
		return value;
	}

	/**
	 * Returns last element placed on stack without removing it from the stack.
	 * Throws EmptyStackException if the stack is empty when this method is
	 * called.
	 * 
	 * @return value at the top of the stack
	 */
	public Object peek() {
		int size = size();
		if (isEmpty()) {
			throw new EmptyStackException();
		}
		return array.get(size - 1);
	}

	/**
	 * Removes all elements from the stack.
	 */
	public void clear() {
		array.clear();
	}

}
