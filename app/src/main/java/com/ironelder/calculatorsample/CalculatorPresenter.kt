package com.ironelder.calculatorsample

import android.content.SharedPreferences
import android.view.View
import io.reactivex.Observable


class CalculatorPresenter : CalculatorContract.Presenter {

    fun clickButton(calculatorData: StringBuilder, data: String, type: BUTTONTYPE) {
        if (type == BUTTONTYPE.NUMBER) {
            calculatorData.append(data)
        } else {
            if (calculatorData.toString().isNotEmpty() && calculatorData.substring(
                    calculatorData.length - 1,
                    calculatorData.length
                ).trim().isNotEmpty()
            ) {
                if (data != "=") {
                    calculatorData.append(" $data ")
                } else {
                    calculatorData.append(
                        calculatorData.substring(
                            calculatorData.lastIndexOf(" ") - 2,
                            calculatorData.length
                        )
                    )
                }
            } else if (calculatorData.toString().isNotEmpty() && calculatorData.substring(
                    calculatorData.length - 1,
                    calculatorData.length
                ).trim().isNullOrEmpty() && data != "c" && data != "="
            ) {
                calculatorData.replace(
                    calculatorData.length - 3,
                    calculatorData.length, " $data "
                )

            }
        }

        if (data == "c") {
            calculatorData.clear()
            EventBus.send("")
            EventBus.result("")
        } else {
            EventBus.send(calculatorData.toString())
            runCalculatorOperation(calculatorData.toString())
        }
    }

    fun getClickEventObservable(v: View): Observable<View?>? {
        return Observable.create { e ->
            v.setOnClickListener { value ->
                e.onNext(
                    value
                )
            }
        }
    }

    private fun runCalculatorOperation(calculatorData: String) {
        val calculatorList = calculatorData.split("\\s".toRegex()).map(String::trim)
        if (calculatorList.size > 2) {
            var operator = ""
            var number = 0
            calculatorList.forEach {
                when {
                    it.toIntOrNull() == null -> operator = it

                    operator == "-" -> {
                        number = minus(number, it.toInt())
                    }
                    operator == "x" -> {
                        number = multiple(number, it.toInt())
                    }
                    operator == "/" -> {
                        number = divide(number, it.toInt())
                    }
                    operator == "%" -> {
                        number = reminder(number, it.toInt())
                    }
                    else -> {
                        number = plus(number, it.toInt())
                    }
                }
            }
            EventBus.result(number.toString())
        }

    }

    fun loadDisplayData(pref: SharedPreferences?, key: String, calculatorData: StringBuilder) {
        val prefData = pref?.getString(key, "")?:""
        if(key == "result"){
            EventBus.result(prefData)
        } else {
            calculatorData.append(prefData)
            EventBus.send(calculatorData.toString())
        }

    }

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

}