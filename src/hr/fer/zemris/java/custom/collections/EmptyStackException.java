package hr.fer.zemris.java.custom.collections;

/**
 * Exception thrown in case of trying to pop from an empty stack.
 * 
 * @author labramusic
 *
 */
public class EmptyStackException extends RuntimeException {

	/**
	 * Default serial version UID.
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Default constructor which creates a new EmptyStackException.
	 */
	public EmptyStackException() {

	}

	/**
	 * Constructor which prints the given message to the standard output.
	 * 
	 * @param message
	 *            error message.
	 */
	public EmptyStackException(String message) {
		System.out.println(message);
	}

}
