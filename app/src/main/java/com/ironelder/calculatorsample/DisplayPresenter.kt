package com.ironelder.calculatorsample

import android.content.SharedPreferences

class DisplayPresenter:DisplayContract.Presenter {
    override fun saveDisplayData(pref:SharedPreferences?, key:String, data: String) {
        val editor = pref?.edit()
        editor?.putString(key, data)
        editor?.apply()
    }
}