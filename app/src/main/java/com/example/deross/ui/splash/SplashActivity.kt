package com.example.deross.ui.splash

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import androidx.databinding.DataBindingUtil
import com.example.deross.R
import com.example.deross.ui.welcome.WelcomeActivity
import com.example.deross.databinding.ActivityMainBinding
import com.example.deross.helper.PrefManger
import com.example.deross.ui.hom.HomActivity
import com.example.deross.ui.rigstration.RigstrationActivity

class SplashActivity : AppCompatActivity() {
    private var mDelayHandler: Handler? = null
    private val SPLASH_DELAY: Long = 6000 //6 seconds
    private var prefManger : PrefManger ? = null
    internal val mRunnable: Runnable = Runnable {
        if (!isFinishing) {


            prefManger = PrefManger(applicationContext)
            if (prefManger!!.isFirstTimeLaunch()){

                val intent = Intent(applicationContext, WelcomeActivity::class.java)
                startActivity(intent)
                finish()
                prefManger!!.setFirstTimeLaunch(false)
            }else{

                val intent = Intent(applicationContext, RigstrationActivity::class.java)
                startActivity(intent)
                finish()
            }

        }
    }

    lateinit var binding : ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this,
            R.layout.activity_main
        )

        //Initialize the Handler
        mDelayHandler = Handler()

        //Navigate with delay
        mDelayHandler!!.postDelayed(mRunnable, SPLASH_DELAY)


    }



    public override fun onDestroy() {

        if (mDelayHandler != null) {
            mDelayHandler!!.removeCallbacks(mRunnable)
        }

        super.onDestroy()
    }
}
