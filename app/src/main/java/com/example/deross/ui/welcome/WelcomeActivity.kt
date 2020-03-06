package com.example.deross.ui.welcome

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.ViewGroup.LayoutParams.WRAP_CONTENT
import android.widget.ImageView
import android.widget.LinearLayout
import androidx.core.content.ContextCompat
import androidx.core.view.get
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProviders
import androidx.viewpager2.widget.ViewPager2
import com.example.deross.R
import com.example.deross.databinding.ActivityWelcomeBinding
import com.example.deross.adapters.IntroSlideAdapter
import com.example.deross.helper.IntroSlide
import com.example.deross.ui.hom.HomActivity
import com.example.deross.ui.rigstration.RigstrationActivity
import kotlinx.android.synthetic.main.activity_welcome.*

class WelcomeActivity : AppCompatActivity() {

    private var viewModel: WelcomeViewModel ? = null
    private val introSlideAdapter : IntroSlideAdapter =
        IntroSlideAdapter(
            listOf(

                IntroSlide(
                    "deross"
                    , "deross is the best way to pass"
                    ,
                    R.drawable.intro_one
                ),

                IntroSlide(
                    "deross"
                    , "deross is the best way to pass"
                    ,
                    R.drawable.intro_tow
                ),

                IntroSlide(
                    "deross"
                    , "deross is the best way to pass"
                    ,
                    R.drawable.intro_three


                )
            )
        )

    lateinit var binding : ActivityWelcomeBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(WelcomeViewModel::class.java)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_welcome)
        binding.vr = viewModel
        binding.vpIntro.adapter = introSlideAdapter

        setupDots()


        vp_intro.registerOnPageChangeCallback(object :
            ViewPager2.OnPageChangeCallback(){

            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                setupCurrentDot(position)
            }
        })

        btn_next.setOnClickListener(View.OnClickListener {
            if (vp_intro.currentItem+1 <introSlideAdapter.itemCount){

                vp_intro.currentItem +=1

            }else{

                startActivity(Intent(applicationContext,RigstrationActivity::class.java))
                finish()
            }
        })

    }

  private  fun setupDots(){

        val dots = arrayOfNulls<ImageView>(introSlideAdapter.itemCount)
        val layoutparams : LinearLayout.LayoutParams
        = LinearLayout.LayoutParams(WRAP_CONTENT, WRAP_CONTENT)
        layoutparams.setMargins(8,0,8,0)

        for (i in dots.indices){

          dots[i] =  ImageView(applicationContext)
           dots[i].apply {
               this?.setImageDrawable(
                   ContextCompat.getDrawable(
                       applicationContext,
                       R.drawable.dot_active
                   )
               )

               this?.layoutParams = layoutparams
           }

            dots_container.addView(dots[i])
        }

    }


    private fun setupCurrentDot(index: Int){

        val chiledCount = dots_container.childCount
        for (i in 0 until chiledCount){

            val imageView = dots_container[i] as ImageView

            if (i==index){

                imageView.setImageDrawable(
                    ContextCompat.getDrawable(
                        applicationContext
                    ,
                        R.drawable.dot_in_active
                    )
                )

            }else{


                imageView.setImageDrawable(
                    ContextCompat.getDrawable(
                        applicationContext
                        ,
                        R.drawable.dot_active
                    )
                )
            }
        }

    }



}
