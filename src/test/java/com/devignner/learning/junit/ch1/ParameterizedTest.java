package com.devignner.learning.junit.ch1;

import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.Collection;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

@RunWith(value = Parameterized.class)
public class ParameterizedTest {
	
	private double expected;
	private double valueOne;
	private double valueTwo;
	
	@Parameters
	public static Collection<Integer[]> getTestParameter(){
		return Arrays.asList(new Integer[][]{
				{2,1,1},
				{3,2,1},
				{4,5,1}
		});
	}

	public ParameterizedTest(double expected, double valueOne, double valueTwo) {
		super();
		this.expected = expected;
		this.valueOne = valueOne;
		this.valueTwo = valueTwo;
	}

	@Test
	public void testAdd(){
		Calculate cal = new Calculate();
		
		assertEquals(expected,cal.add(valueOne,valueTwo),0);
	}
}
