package hr.fer.zemris.java.custom.collections.demo;

import hr.fer.zemris.java.custom.collections.ObjectStack;

/**
 * Demonstrates the usage of the ObjectStack class. Expects a single
 * command-line argument: expression which should be evaluated, in quotation
 * marks. The expression must be in postfix representation.
 * 
 * @author labramusic
 *
 */
public class StackDemo {

	/**
	 * The main method.
	 * 
	 * @param args
	 *            command line arguments.
	 */
	public static void main(String[] args) {
		String expression = args[0].replace("\"", "");
		String[] arguments = expression.split(" ");

		ObjectStack stack = new ObjectStack();
		for (String arg : arguments) {

			if (arg.matches("-?[0-9]")) {
				int number = Integer.parseInt(arg);
				stack.push(number);
				continue;
				
			} else {
				int num2 = (int) stack.pop();
				int num1 = (int) stack.pop();
				int result = 0;
				
				switch (arg) {
				case "+":
					result = num1 + num2;
					break;
					
				case "-":
					result = num1 - num2;
					break;
					
				case "*":
					result = num1 * num2;
					break;
					
				case "/":
					if (num2 == 0) {
						System.out.println("Cannot divide by zero.");
						break;
					}
					result = num1 / num2;
					break;
					
				case "%":
					result = num1 % num2;
					break;
				}
				
				stack.push(result);
			}
		}

		if (stack.size() != 1) {
			System.out.println("Error ocurred.");
		} else {
			System.out.println("The result is " + stack.pop() + ".");
		}

	}

}
