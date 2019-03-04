package com.simasdanilo.training;

// import android.support.test.filters.SmallTest;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

/**
 * JUnit4 unit tests for the calculator logic. These are local unit tests; no device needed
 */
@RunWith(JUnit4.class)

// @SmallTest
public class CalculatorTest {

    private Calculator mCalculator;

    /**
     * Set up the environment for testing
     */
    @Before
    public void setUp() {
        mCalculator = new Calculator();
    }

    /**
     * Test for simple addition
     */
    @Test
    public void addTwoNumbers() {
        double resultAdd = mCalculator.add(1d, 1d);
        assertThat(resultAdd, is(equalTo(2d)));
    }

    @Test
    public void addTwoNumbersNegative() {
        double resultAdd = mCalculator.add(-1d, 2d);
        assertThat(resultAdd, is(equalTo(1d)));
    }

    @Test
    public void subTwoNumbers() {
        double resultSub = mCalculator.sub(5d,1d);
        assertThat(resultSub,is(equalTo(4d)));
    }

    @Test
    public void subWorksWithNegativeResults() {
        double resultSub = mCalculator.sub(1d,5d);
        assertThat(resultSub,is(equalTo(-4d)));
    }

    @Test
    public void mulTwoNumbers() {
        double resultMul = mCalculator.mul(5d,2d);
        assertThat(resultMul,is(equalTo(10d)));
    }

    @Test
    public void mulTwoNumbersZero() {
        double resultMul = mCalculator.mul(5d,0d);
        assertThat(resultMul,is(equalTo(0d)));
    }

    @Test
    public void divTwoNumbers() {
        double resultDiv = mCalculator.div(5d,2d);
        assertThat(resultDiv,is(equalTo(2.5d)));
    }

    @Test
    public void divTwoNumbersZero() {
        double resultDiv = mCalculator.div(5d,0d);
        assertThat(resultDiv,is(equalTo(Double.POSITIVE_INFINITY)));
    }

}