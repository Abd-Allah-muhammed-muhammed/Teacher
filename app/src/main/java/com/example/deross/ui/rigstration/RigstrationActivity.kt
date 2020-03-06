package com.example.deross.ui.rigstration

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.deross.R
import com.example.deross.ui.rigstration.sign_up.SignUpFragment
import com.example.deross.ui.rigstration.ui.rigstrationfragmentcontainer.RigstrationFragmentContainer

class RigstrationActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.rigstration_activity)
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.container, RigstrationFragmentContainer.newInstance())
                .commitNow()
        }
    }
}
