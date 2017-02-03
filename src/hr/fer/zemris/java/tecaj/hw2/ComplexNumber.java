package hr.fer.zemris.java.tecaj.hw2;

import java.text.DecimalFormat;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Represents an immutable complex number.
 * 
 * @author labramusic
 *
 */
public class ComplexNumber {

	/**
	 * Real part of the complex number.
	 */
	private double real;

	/**
	 * Imaginary part of the complex number.
	 */
	private double imaginary;

	/**
	 * Magnitude of the complex number.
	 */
	private double magnitude;

	/**
	 * Angle in radians, from 0 to 2 Pi.
	 */
	private double angle;

	/**
	 * Constructor which creates a new complex number out of its real and
	 * imaginary parts. The magnitude and angle are automatically calculated.
	 * 
	 * @param real
	 *            real part of complex number
	 * @param imaginary
	 *            imaginary part of complex number
	 */
	public ComplexNumber(double real, double imaginary) {
		this.real = real;
		this.imaginary = imaginary;
		magnitude = Math.sqrt(Math.pow(real, 2) + Math.pow(imaginary, 2));
		// PI is added so the angle is between 0 and 2 PI
		angle = Math.atan2(imaginary, real) + Math.PI;
	}

	/**
	 * Returns a new complex number with the given real part and its imaginary
	 * part set to zero.
	 * 
	 * @param real
	 *            real part to be converted
	 * @return complex number with given real part
	 */
	public static ComplexNumber fromReal(double real) {
		return new ComplexNumber(real, 0);
	}

	/**
	 * Returns a new complex number with the given imaginary part and its real
	 * part set to zero.
	 * 
	 * @param imaginary
	 *            imaginary part to be converted
	 * @return complex number with the given imaginary part
	 */
	public static ComplexNumber fromImaginary(double imaginary) {
		return new ComplexNumber(0, imaginary);
	}

	/**
	 * Calculates a new complex number from the given magnitude and angle.
	 * 
	 * @param magnitude
	 *            magnitude of the complex number
	 * @param angle
	 *            angle of the complex number
	 * @return complex number with the given magnitude and angle
	 */
	public static ComplexNumber fromMagnitudeAndAngle(double magnitude, double angle) {
		double real = magnitude * Math.cos(angle);
		double imaginary = magnitude * Math.sin(angle);
		return new ComplexNumber(real, imaginary);
	}

	/**
	 * Parses the given string for a complex number. Accepts strings such as:
	 * "3.51", "-3.17", "-2.71i", "i", "1", "-2.71-3.15i", "2+i".
	 * 
	 * @param s
	 *            string representation of the complex number
	 * @return complex number parsed from the given string
	 */
	public static ComplexNumber parse(String s) {
		Pattern pattern = Pattern
				.compile("(-?\\d*\\.?\\d+) ?(([+-] ?(\\d*\\.?\\d+)?)i)|"
						+ "(-?\\d*\\.?\\d+)|((-?(\\d*\\.?\\d+)?)i)");
		Matcher matcher = pattern.matcher(s);
		if (!matcher.matches()) {
			throw new IllegalArgumentException();

		} else {
			double real = 0;
			double imaginary = 0;

			if (matcher.group(1) != null) {
				// real and imaginary part are not zero
				real = Double.parseDouble(matcher.group(1));

			} else if (matcher.group(5) != null) {
				// real part is not zero, imaginary is
				real = Double.parseDouble(matcher.group(5));
			}

			if (matcher.group(2) != null) {
				// real and imaginary part are not zero
				String expression = matcher.group(3).replaceAll(" ", "");
				if (expression.equals("+")) {
					// i
					imaginary = 1;
				} else if (expression.equals("-")) {
					// -i
					imaginary = -1;
				} else {
					imaginary = Double.parseDouble(expression);
				}

			} else if (matcher.group(6) != null) {
				// imaginary part is not zero, real is
				String expression = matcher.group(7);
				if (expression.isEmpty()) {
					// i
					imaginary = 1;
				} else if (expression.equals("-")) {
					// -i
					imaginary = -1;
				} else {
					imaginary = Double.parseDouble(expression);
				}
			}

			return new ComplexNumber(real, imaginary);
		}
	}

	/**
	 * Returns the real part.
	 * 
	 * @return real part
	 */
	public double getReal() {
		return real;
	}

	/**
	 * Returns the imaginary part.
	 * 
	 * @return imaginary part
	 */
	public double getImaginary() {
		return imaginary;
	}

	/**
	 * Returns the magnitude.
	 * 
	 * @return magnitude
	 */
	public double getMagnitude() {
		return magnitude;
	}

	/**
	 * Returns the angle.
	 * 
	 * @return angle
	 */
	public double getAngle() {
		return angle;
	}

	/**
	 * Adds two complex numbers.
	 * 
	 * @param c
	 *            complex number being added to the first
	 * @return result of addition as new complex number
	 */
	public ComplexNumber add(ComplexNumber c) {
		double real = this.real + c.real;
		double imaginary = this.imaginary + c.imaginary;
		return new ComplexNumber(real, imaginary);
	}

	/**
	 * Subtracts the given complex number from the first.
	 * 
	 * @param c
	 *            complex number being subtracted from the first
	 * @return result of subtraction as a new complex number
	 */
	public ComplexNumber sub(ComplexNumber c) {
		double real = this.real - c.real;
		double imaginary = this.imaginary - c.imaginary;
		return new ComplexNumber(real, imaginary);
	}

	/**
	 * Multiplies two complex numbers.
	 * 
	 * @param c
	 *            complex number to be multiplied with the first
	 * @return result of multiplication as a new complex number
	 */
	public ComplexNumber mul(ComplexNumber c) {
		double magnitude = this.magnitude * c.magnitude;
		double angle = this.angle + c.angle;
		return fromMagnitudeAndAngle(magnitude, angle);
	}

	/**
	 * Divides the first complex number with the given complex number. Throws
	 * ArithmeticException in case of division by zero.
	 * 
	 * @param c
	 *            complex number which divides the first
	 * @return result of division as a new complex number
	 */
	public ComplexNumber div(ComplexNumber c) {
		if (c.magnitude == 0) {
			//undefined ?
			throw new ArithmeticException("Cannot divide by zero.");
		}
		double magnitude = this.magnitude / c.magnitude;
		double angle = this.angle - c.angle;
		return fromMagnitudeAndAngle(magnitude, angle);
	}

	/**
	 * Calculates the nth power of a complex number. Throws
	 * IllegalArgumentException if n is less than zero.
	 * 
	 * @param n
	 *            the power to which the number should be raised
	 * @return result of the nth power as a new complex number
	 */
	public ComplexNumber power(int n) {
		if (n < 0) {
			throw new IllegalArgumentException("The exponent n must be a number equal to or higher than zero.");
		}
		double magnitude = Math.pow(this.magnitude, n);
		double angle = this.angle * n;
		return fromMagnitudeAndAngle(magnitude, angle);
	}

	/**
	 * Calculates the nth roots of a complex number. Throws
	 * IllegalArgumentException if n is less than zero.
	 * 
	 * @param n
	 *            number of roots to be calculated
	 * @return nth roots of the given number as an array of complex numbers
	 */
	public ComplexNumber[] root(int n) {
		if (n < 0) {
			throw new IllegalArgumentException("The exponent n must be a number equal to or higher than zero.");
		}
		ComplexNumber[] roots = new ComplexNumber[n];
		double magnitude = Math.pow(this.magnitude, 1.0 / n);
		for (int k = 0; k < n; ++k) {
			double angle = (this.angle + 2.0 * k * Math.PI) / n;
			roots[k] = fromMagnitudeAndAngle(magnitude, angle);
		}
		return roots;
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		DecimalFormat formatter = new DecimalFormat("#.##");
		if (real == 0 && imaginary == 0) {
			sb.append(0);
		}
		if (real != 0) {
			sb.append(formatter.format(real));
		}
		if (imaginary != 0) {
			if (imaginary > 0 && real != 0) {
				sb.append("+");
			}
			if (imaginary != 1 && imaginary != -1) {
				sb.append(formatter.format(imaginary));
			}
			sb.append("i");
		}
		return sb.toString();
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		long temp;
		temp = Double.doubleToLongBits(imaginary);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(real);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ComplexNumber other = (ComplexNumber) obj;
		if (Double.doubleToLongBits(imaginary) != Double.doubleToLongBits(other.imaginary))
			return false;
		if (Double.doubleToLongBits(real) != Double.doubleToLongBits(other.real))
			return false;
		return true;
	}

}
