package com.ironelder.calculatorsample

interface CalculatorContract {
    interface View {

    }

    interface Presenter {

        fun plus( first:Int, second:Int ) : Int
        fun minus( first:Int, second:Int ) : Int
        fun multiple( first:Int, second:Int ) : Int
        fun divide( first:Int, second:Int ) : Int
        fun reminder( first:Int, second:Int ) : Int
        fun clear()
        fun equal()

    }
}