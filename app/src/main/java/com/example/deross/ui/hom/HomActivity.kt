package com.example.deross.ui.hom

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.deross.R

class HomActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.hom_activity)
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.container, HomFragment.newInstance())
                .commitNow()
        }
    }
}
