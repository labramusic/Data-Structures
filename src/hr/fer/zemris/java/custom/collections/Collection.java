package hr.fer.zemris.java.custom.collections;

/**
 * Represents a general collection of objects. Has the functionality of adding
 * and removing elements, giving general information about its contents, as well
 * as converting the collection to an array.
 * 
 * @author labramusic
 *
 */
public class Collection {

	/**
	 * Default constructor which creates a new collection.
	 */
	protected Collection() {

	}

	/**
	 * Checks if collection contains any elements, returns true if empty.
	 * 
	 * @return true if collection contains no elements, false otherwise
	 */
	public boolean isEmpty() {
		if (size() == 0) {
			return true;
		}
		return false;
	}

	/**
	 * Returns the number of currently stored objects in this collection.
	 * 
	 * @return number of currently stored objects
	 */
	public int size() {
		return 0;
	}

	/**
	 * Adds the given object into this collection.
	 * 
	 * @param value
	 *            object to be added
	 */
	public void add(Object value) {

	}

	/**
	 * Returns true only if the collection contains the given value, as
	 * determined by the method equals.
	 * 
	 * @param value
	 *            object being looked for
	 * @return true only if collection contains the given value
	 */
	public boolean contains(Object value) {
		return false;
	}

	/**
	 * Returns true only if the collection contains the given value, as
	 * determined by the method equals, and removes one occurrence of it.
	 * 
	 * @param value
	 *            object to be removed
	 * @return true only if collection contains given value
	 */
	public boolean remove(Object value) {
		return false;
	}

	/**
	 * Allocates new array with size equals to the size of this collections,
	 * fills it with collection content and returns the array. Never returns
	 * null.
	 * 
	 * @return array with elements of the collection
	 */
	public Object[] toArray() {
		throw new UnsupportedOperationException();
	}

	/**
	 * Calls the process method of the given processor instance for each element
	 * of this collection.
	 * 
	 * @param processor
	 *            processor instance with the processing method to be called
	 */
	public void forEach(Processor processor) {

	}

	/**
	 * Adds all elements from the given collection.
	 * This other collection remains unchanged.
	 * 
	 * @param other
	 *            collection to add elements from
	 */
	public void addAll(Collection other) {
		class AddingProcessor extends Processor {
			@Override
			public void process(Object value) {
				add(value);
			}
		}
		Processor processor = new AddingProcessor();
		other.forEach(processor);
	}

	/**
	 * Removes all elements from this collection.
	 */
	public void clear() {

	}

}
