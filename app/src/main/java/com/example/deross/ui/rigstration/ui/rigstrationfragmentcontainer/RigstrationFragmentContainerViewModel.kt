package com.example.deross.ui.rigstration.ui.rigstrationfragmentcontainer

import android.view.View
import android.widget.Toast
import androidx.lifecycle.ViewModel

class RigstrationFragmentContainerViewModel : ViewModel() {

    private var phone:String = ""
    private var pass:String = ""


    fun phoneNumber(
        s: CharSequence,
        start: Int,
        before: Int,
        count: Int
    ) {

    phone=   s.toString()

    }


    fun passwordNumber(
        s: CharSequence,
        start: Int,
        before: Int,
        count: Int
    ) {
pass= s.toString()

    }


    fun login(v:View){

        Toast.makeText(v.context,phone+pass,Toast.LENGTH_LONG).show()


    }
}
