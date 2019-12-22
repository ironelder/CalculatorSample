package com.ironelder.calculatorsample

import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test


/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class CalculatorUnitTest {
    private lateinit var calculator: CalculatorPresenter

    @Before
    fun setUp() {
        calculator = CalculatorPresenter()
    }

    @Test
    fun plusTest() {
        assertEquals(4, calculator.plus(2, 2))
    }

    @Test
    fun minusTest() {
        assertEquals(-2, calculator.minus(2, 4))
    }

    @Test
    fun multipleTest() {
        assertEquals(6, calculator.multiple(3, 2))
    }

    @Test
    fun divideTest() {
        assertEquals(1, calculator.divide(2, 2))
    }

    @Test
    fun reminderTest() {
        assertEquals(1, calculator.reminder(5, 2))
    }



}
