package com.ironelder.calculatorsample

import android.content.SharedPreferences

interface DisplayContract {
    interface View{}
    interface Presenter{
        fun saveDisplayData(pref: SharedPreferences?, key:String, data:String)
        fun loadDisplayData(pref: SharedPreferences?, key: String):String
    }
}