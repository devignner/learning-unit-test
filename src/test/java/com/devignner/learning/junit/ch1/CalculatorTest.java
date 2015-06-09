package com.devignner.learning.junit.ch1;

import org.junit.Assert;
import org.junit.Test;

public class CalculatorTest {

	@Test
	public void testAdd(){
		Calculator calc = new Calculator();
		double result = calc.add(10, 50);
		
		Assert.assertEquals(60, result, 0);
		
//		Suite = " a set of test";
//		Runner = "테스트를 수행하는 객체"
	}

	@Test
	public void testMultiply(){
		Assert.fail();
	}
}
