package com.devignner.learning.junit.ch1;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;


@RunWith(value = Suite.class)
@SuiteClasses(
		value = {
				CalculateTest.class,
				ParameterizedTest.class
		})
public class CalcTestSuite {

	
	
}
