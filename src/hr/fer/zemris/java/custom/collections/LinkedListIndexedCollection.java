package hr.fer.zemris.java.custom.collections;

/**
 * Implementation of a linked list-backed collection of objects. The general
 * contract of this collection is that it allows duplicate elements and doesn't
 * allow storage of null references.
 * 
 * @author labramusic
 *
 */
public class LinkedListIndexedCollection extends Collection {

	/**
	 * A list node of a doubly linked list.
	 * 
	 * @author labramusic
	 *
	 */
	private static class ListNode {

		/**
		 * Reference to the previous node in the list.
		 */
		ListNode prev;

		/**
		 * Reference to the next node in the list.
		 */
		ListNode next;

		/**
		 * Data which the node contains.
		 */
		Object data;
	}

	/**
	 * Number of elements stored in the collection (number of nodes in the
	 * list).
	 */
	private int size;

	/**
	 * Reference to the first node in the list.
	 */
	private ListNode first;

	/**
	 * Reference to the last node in the list.
	 */
	private ListNode last;

	/**
	 * Default constructor which creates an empty list.
	 */
	public LinkedListIndexedCollection() {
		first = last = null;
	}

	/**
	 * Constructor which accepts a reference to some other collection the
	 * elements of which are copied into this newly constructed collection.
	 * 
	 * @param other collection to be copied from
	 */
	public LinkedListIndexedCollection(Collection other) {
		this();
		addAll(other);
	}

	@Override
	public int size() {
		return size;
	}

	/**
	 * Adds the object to the first empty place, at the end of the list. Throws
	 * IllegalArgumentException if a null value is attempted to be added.
	 */
	@Override
	public void add(Object value) {
		if (value == null) {
			throw new IllegalArgumentException();
		}
		ListNode node = new ListNode();
		node.data = value;
		node.prev = last;
		node.next = null;

		if (first == null) {
			first = node;
		} else {
			last.next = node;
		}
		last = node;
		++size;
	}

	@Override
	public boolean contains(Object value) {
		for (ListNode node = first; node != null; node = node.next) {
			if (node.data.equals(value)) {
				return true;
			}
		}
		return false;
	}

	@Override
	public boolean remove(Object value) {
		for (ListNode node = first; node != null; node = node.next) {
			if (node.data.equals(value)) {

				if (first != node) {
					node.prev.next = node.next;
				} else {
					first = node.next;
				}
				if (last != node) {
					node.next.prev = node.prev;
				} else {
					last = node.prev;
				}
				node = null;
				--size;
				return true;
			}
		}
		return false;
	}

	/**
	 * Removes the element in the list at the given index. Throws
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
		ListNode node = first;
		for (int i = 0; i < size; ++i, node = node.next) {
			if (i == index) {
				break;
			}
		}

		if (first != node) {
			node.prev.next = node.next;
		} else {
			first = node.next;
		}
		if (last != node) {
			node.next.prev = node.prev;
		} else {
			last = node.prev;
		}
		node = null;
		--size;
	}

	@Override
	public Object[] toArray() {
		Object[] array = new Object[size];
		ListNode node = first;
		for (int i = 0; i < size; ++i, node = node.next) {
			array[i] = node.data;
		}
		return array;
	}

	@Override
	public void forEach(Processor processor) {
		for (ListNode node = first; node != null; node = node.next) {
			processor.process(node.data);
		}
	}

	/**
	 * Returns the object that is stored in the linked list at position index.
	 * Valid indexes are 0 to size-1. IndexOutOfBoundsException is thrown if
	 * index is invalid. The method searches only half of the elements,
	 * depending if the given index is lower or higher than half the size of the
	 * list.
	 * 
	 * @param index
	 *            index of element to be retrieved
	 * @return element at given index
	 */
	public Object get(int index) {
		if (index < 0 || index > size - 1) {
			throw new IndexOutOfBoundsException();
		}
		ListNode node;
		int half = size / 2;
		if (index < half) {
			node = first;
			for (int i = 0; i < half; ++i, node = node.next) {
				if (index == i) {
					break;
				}
			}
		} else {
			node = last;
			for (int i = size - 1; i >= half; --i, node = node.prev) {
				if (index == i) {
					break;
				}
			}
		}
		
		return node.data;
	}

	@Override
	public void clear() {
		first = last = null;
		size = 0;
	}

	/**
	 * Inserts (does not overwrite) the given value at the given position in the
	 * linked list. Elements starting from this position are shifted backwards
	 * for one position. The legal positions are 0 to size.
	 * IndexOutOfBoundsException is thrown if position is invalid. If the given
	 * value is null, IllegalArgumentException is thrown.
	 * 
	 * @param value
	 *            value to be inserted in the list
	 * @param position
	 *            position where the element should be inserted
	 */
	public void insert(Object value, int position) {
		if (value == null) {
			throw new IllegalArgumentException();
		}
		if (position < 0 || position > size) {
			throw new IndexOutOfBoundsException();
		}
		ListNode tempNode = first;
		ListNode newNode = new ListNode();
		newNode.prev = null;
		newNode.next = null;
		newNode.data = value;

		for (int i = 0; i <= size; ++i, tempNode = tempNode.next) {
			if (i == position) {
				break;
			}
		}

		if (first == null) {
			first = last = newNode;
		} else {

			if (position == 0) {
				newNode.next = first;
				first.prev = newNode;
				first = newNode;
			} else if (position == size) {
				newNode.prev = last;
				last.next = newNode;
				last = newNode;
			} else {
				newNode.prev = tempNode.prev;
				newNode.next = tempNode;
				tempNode.prev.next = newNode;
				tempNode.prev = newNode;
			}
		}
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
		ListNode node = first;
		for (int i = 0; i < size; ++i, node = node.next) {
			if (node.data.equals(value))
				return i;
		}
		return -1;
	}

}
