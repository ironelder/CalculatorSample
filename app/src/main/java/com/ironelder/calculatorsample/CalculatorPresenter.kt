package com.ironelder.calculatorsample

class CalculatorPresenter:CalculatorContract.Presenter {
    override fun plus(first: Int, second: Int): Int {
        return first + second
    }

    override fun minus(first: Int, second: Int): Int {
        return first - second
    }

    override fun multiple(first: Int, second: Int): Int {
        return first * second
    }

    override fun divide(first: Int, second: Int): Int {
        return first / second
    }

    override fun reminder(first: Int, second: Int): Int {
        return first % second
    }

    override fun clear() {
    }

    override fun equal() {
    }
}