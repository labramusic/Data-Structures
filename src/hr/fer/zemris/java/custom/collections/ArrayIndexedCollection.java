package hr.fer.zemris.java.custom.collections;

/**
 * Implementation of a resizable array-backed collection of objects. The general
 * contract of this collection is that it allows duplicate elements and doesn't
 * allow storage of null references.
 * 
 * @author labramusic
 *
 */
public class ArrayIndexedCollection extends Collection {

	/**
	 * The default initial capacity.
	 */
	private static final int DEFAULT_CAPACITY = 16;
	
	/**
	 * Number of elements currently stored in the collection.
	 */
	private int size;

	/**
	 * Current capacity of allocated array of object references.
	 */
	private int capacity;

	/**
	 * An array of object references used as storage of elements.
	 */
	private Object[] elements;

	/**
	 * Default constructor which creates an instance with capacity set to 16.
	 */
	public ArrayIndexedCollection() {
		this(DEFAULT_CAPACITY);
	}

	/**
	 * Constructor which sets the capacity to the given value and preallocates
	 * the elements array of the same size. Throws IllegalArgumentException if
	 * the given capacity is less than 1.
	 * 
	 * @param initialCapacity
	 *            initial array capacity
	 */
	public ArrayIndexedCollection(int initialCapacity) {
		if (initialCapacity < 1) {
			throw new IllegalArgumentException("Capacity must be at least 1.");
		}
		capacity = initialCapacity;
		elements = new Object[capacity];
	}

	/**
	 * Constructor which accepts a reference to some other collection the
	 * elements of which are copied into this newly constructed collection with
	 * initial capacity of 16.
	 * 
	 * @param other
	 *            collection to be copied from
	 */
	public ArrayIndexedCollection(Collection other) {
		this(other, DEFAULT_CAPACITY);
	}

	/**
	 * Constructor which accepts a reference to some other collection the
	 * elements of which are copied into this newly constructed collection. The
	 * other argument is the desired capacity of the new collection. Throws
	 * IllegalArgumentException if the given capacity is less than 1.
	 * 
	 * @param other
	 *            collection to be copied from
	 * @param initialCapacity
	 *            desired capacity
	 */
	public ArrayIndexedCollection(Collection other, int initialCapacity) {
		this(initialCapacity);
		addAll(other);
	}

	@Override
	public int size() {
		return size;
	}

	/**
	 * Adds the object to the first empty place, at the end of the array. If the
	 * elements array is full, it is reallocated by doubling its size. Throws
	 * IllegalArgumentException if a null value is attempted to be added.
	 */
	@Override
	public void add(Object value) {
		if (value == null) {
			throw new IllegalArgumentException();
		}
		if (size == capacity) {
			capacity = 2 * capacity;
			ArrayIndexedCollection array = new ArrayIndexedCollection(this, capacity);
			elements = array.elements;
		}
		elements[size++] = value;
	}

	@Override
	public boolean contains(Object value) {
		for (int i = 0; i < size; ++i) {
			if (elements[i].equals(value))
				return true;
		}
		return false;
	}

	@Override
	public boolean remove(Object value) {
		for (int i = 0; i < size; ++i) {
			if (elements[i].equals(value)) {
				elements[i] = null;
				for (int j = i; j < size - 1; ++j) {
					elements[j] = elements[j + 1];
				}
				--size;
				elements[size] = null;
				return true;
			}
		}
		return false;
	}

	/**
	 * Removes the element in the elements array at the given index and shifts
	 * the elements after it one place backwards. Throws
	 * IndexOutOfBoundsException if the given index is not between 0 and size -
	 * 1.
	 * 
	 * @param index
	 *            index of element to be removed
	 */
	public void remove(int index) {
		if (index < 0 || index > size - 1) {
			throw new IndexOutOfBoundsException();
		}
		elements[index] = null;
		for (int i = index; i < size - 1; ++i) {
			elements[i] = elements[i + 1];
		}
		--size;
	}

	@Override
	public Object[] toArray() {
		ArrayIndexedCollection array = new ArrayIndexedCollection(this, size);
		return array.elements;
	}

	@Override
	public void forEach(Processor processor) {
		for (int i = 0; i < size; ++i) {
			processor.process(elements[i]);
		}
	}

	/**
	 * Returns the object that is stored in the backing array at position index.
	 * Valid indexes are 0 to size-1. Throws IndexOutOfBoundsException if index
	 * is invalid.
	 * 
	 * @param index
	 *            index of desired element
	 * @return element at given index
	 */
	public Object get(int index) {
		if (index < 0 || index > size - 1) {
			throw new IndexOutOfBoundsException();
		}
		return elements[index];
	}

	@Override
	public void clear() {
		elements = new Object[capacity];
		size = 0;
	}

	/**
	 * Inserts (does not overwrite) the given value at the given position in
	 * array. Before the actual insertion elements at position and at greater
	 * positions must be shifted one place toward the end, so that an empty
	 * place is created at position. The legal positions are 0 to size.
	 * IndexOutOfBoundsException is thrown if position is invalid. If the given
	 * value is null, IllegalArgumentException is thrown.
	 * 
	 * @param value
	 *            object to be inserted
	 * @param position
	 *            position in which value should be inserted
	 */
	public void insert(Object value, int position) {
		if (value == null) {
			throw new IllegalArgumentException();
		}
		if (position < 0 || position > size) {
			throw new IndexOutOfBoundsException();
		}
		if (size == capacity) {
			capacity = 2 * capacity;
			ArrayIndexedCollection array = new ArrayIndexedCollection(this, capacity);
			elements = array.elements;
		}
		for (int i = size; i > position; --i) {
			elements[i] = elements[i - 1];
		}
		elements[position] = value;
		++size;
	}

	/**
	 * Searches the collection and returns the index of the first occurrence of
	 * the given value or -1 if the value is not found.
	 * 
	 * @param value
	 *            object the index of which is being looked for
	 * @return index of the object being searched for
	 */
	public int indexOf(Object value) {
		for (int i = 0; i < size; ++i) {
			if (elements[i].equals(value))
				return i;
		}
		return -1;
	}

}
