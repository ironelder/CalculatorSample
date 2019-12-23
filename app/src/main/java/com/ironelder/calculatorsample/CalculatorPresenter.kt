package com.ironelder.calculatorsample


class CalculatorPresenter:CalculatorContract.Presenter {
    private var number = ""
    private var calculator = ""
    private val numList = arrayListOf<Int>()


    fun clickNumber( num:String ){
        EventBus.send(num)
        number += num
    }

    fun clickCalculator(cal:String){
        if(number.isNotEmpty()){
            if(numList.size < 2){
                numList.add(Integer.parseInt(number))
            } else {
                numList[1] = Integer.parseInt(number)
            }
        }
        if(cal == "=" && calculator.isNotEmpty()){
            if(numList.size > 1 && calculator != cal){
                EventBus.result(calculatorFunc(calculator, numList[0], numList[1]).toString())
                EventBus.send("\n")
            }
            calculator = ""
            numList.clear()
        } else if (cal == "+") {
            if(numList.size > 1 && calculator != cal){
                numList[0] = calculatorFunc(calculator, numList[0], numList[1])
                numList.removeAt(1)
                EventBus.result(numList[0].toString())
            }
            if(numList.size > 1){
                numList[0] = plus(numList[0], numList[1])
                EventBus.result(numList[0].toString())
                if(number.isNullOrEmpty() && calculator == "+"){
                    EventBus.send( numList[1].toString() + "+")
                } else {
                    EventBus.send( "+" )
                }
            } else {
                EventBus.send( "+" )
            }
            calculator = "+"
        } else if (cal == "x") {
            if(numList.size > 1 && calculator != cal){
                numList[0] = calculatorFunc(calculator, numList[0], numList[1])
                numList.removeAt(1)
                EventBus.result(numList[0].toString())
            }

            if(numList.size > 1){
                numList[0] = multiple(numList[0], numList[1])
                EventBus.result(numList[0].toString())
                if(number.isNullOrEmpty() && calculator == "x"){
                    EventBus.send( numList[1].toString() + "x")
                } else {
                    EventBus.send( "x" )
                }
            } else {
                EventBus.send( "x" )
            }
            calculator = "x"
        }  else if (cal == "-") {
            if(numList.size > 1 && calculator != cal){
                numList[0] = calculatorFunc(calculator, numList[0], numList[1])
                numList.removeAt(1)
                EventBus.result(numList[0].toString())
            }

            if(numList.size > 1){
                numList[0] = minus(numList[0], numList[1])
                EventBus.result(numList[0].toString())
                if(number.isNullOrEmpty() && calculator == "-"){
                    EventBus.send( numList[1].toString() + "-")
                } else {
                    EventBus.send( "-" )
                }
            } else {
                EventBus.send( "-" )
            }
            calculator = "-"
        } else if (cal == "/") {
            if(numList.size > 1 && calculator != cal){
                numList[0] = calculatorFunc(calculator, numList[0], numList[1])
                numList.removeAt(1)
                EventBus.result(numList[0].toString())
            }

            if(numList.size > 1){
                numList[0] = divide(numList[0], numList[1])
                EventBus.result(numList[0].toString())
                if(number.isNullOrEmpty() && calculator == "/"){
                    EventBus.send( numList[1].toString() + "/")
                } else {
                    EventBus.send( "/" )
                }
            } else {
                EventBus.send( "/" )
            }
            calculator = "/"
        } else if (cal == "%") {
            if(numList.size > 1 && calculator != cal){
                numList[0] = calculatorFunc(calculator, numList[0], numList[1])
                numList.removeAt(1)
                EventBus.result(numList[0].toString())
            }

            if(numList.size > 1){
                numList[0] = reminder(numList[0], numList[1])
                EventBus.result(numList[0].toString())
                if(number.isNullOrEmpty() && calculator == "%"){
                    EventBus.send( numList[1].toString() + "%")
                } else {
                    EventBus.send( "%" )
                }
            } else {
                EventBus.send( "%" )
            }
            calculator = "%"
        } else if (cal == "c"){
            calculator = ""
            number = ""
            numList.clear()
            EventBus.send("c")
        }
        number = ""
    }

    private fun calculatorFunc(type:String, num1:Int, num2:Int):Int{
        if(type == "+"){
            return plus(num1, num2)
        } else if (type == "x"){
            return multiple(num1, num2)
        } else if (type == "-"){
            return minus(num1, num2)
        } else if (type == "/"){
            return divide(num1, num2)
        } else {
            return reminder(num1, num2)
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

    override fun clear() {
    }

    override fun equal() {
    }

    fun addDisplayString(before:String, after:String):String{
        return before + after
    }
}