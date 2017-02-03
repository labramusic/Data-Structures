package hr.fer.zemris.java.tecaj.hw2;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class ComplexTests {

	@Test
	public void parseTest() {
		ComplexNumber c1 = ComplexNumber.parse("0");
		ComplexNumber c2 = ComplexNumber.parse("0i");
		ComplexNumber c3 = ComplexNumber.parse("0+i");
		ComplexNumber c4 = ComplexNumber.parse("0 - i");
		ComplexNumber c5 = ComplexNumber.parse("i");
		ComplexNumber c6 = ComplexNumber.parse("-1 + 0i");
		ComplexNumber c7 = ComplexNumber.parse("0 - 1i");

		assertEquals(new ComplexNumber(0, 0), c1);
		assertEquals(new ComplexNumber(0, 0), c2);
		assertEquals(new ComplexNumber(0, 1), c3);
		assertEquals(new ComplexNumber(0, -1), c4);
		assertEquals(new ComplexNumber(0, 1), c5);
		assertEquals(new ComplexNumber(-1, 0), c6);
		assertEquals(new ComplexNumber(0, -1), c7);
	}

}
