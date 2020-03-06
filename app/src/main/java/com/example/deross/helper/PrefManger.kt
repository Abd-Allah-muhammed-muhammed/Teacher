package com.example.deross.helper

import android.content.Context
import android.content.SharedPreferences
import android.content.SharedPreferences.Editor


class PrefManger {

    lateinit var pref: SharedPreferences
    lateinit var editor: Editor
    var _context: Context? = null

    // shared pref mode
    var PRIVATE_MODE = 0
    // Shared preferences file name
    private val PREF_NAME = "dross_abdalah"
    private val IS_FIRST_TIME_LAUNCH = "IsFirstTimeLaunch"


    constructor (context: Context?) {
        _context = context
        pref = _context!!.getSharedPreferences(PREF_NAME, PRIVATE_MODE)
        editor =pref.edit()
    }


    fun setFirstTimeLaunch(isFirstTime: Boolean) {
        editor.putBoolean(IS_FIRST_TIME_LAUNCH, isFirstTime)
        editor.commit()
    }

    fun isFirstTimeLaunch(): Boolean {
        return pref.getBoolean(IS_FIRST_TIME_LAUNCH, true)
    }

}