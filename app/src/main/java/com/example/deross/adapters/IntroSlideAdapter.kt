package com.example.deross.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.deross.R
import com.example.deross.helper.IntroSlide

class IntroSlideAdapter(private val listIntroSlide: List<IntroSlide>) :
    RecyclerView.Adapter<IntroSlideAdapter.IntroSlideViewHolder>() {

    inner class IntroSlideViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        private val titelIntro: TextView = view.findViewById(R.id.tv_title)
        private val descIntro: TextView = view.findViewById(R.id.tv_desc)
        private val image_intro: ImageView = view.findViewById(R.id.image_into)

        fun bind(introSlidData: IntroSlide) {
            titelIntro.text = introSlidData.title
            descIntro.text = introSlidData.description
            image_intro.setImageResource(introSlidData.icon)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): IntroSlideViewHolder {

        return IntroSlideViewHolder(

            LayoutInflater.from(parent.context).inflate(R.layout.container_slide_intro, parent, false)
        )

    }

    override fun getItemCount(): Int {

        return listIntroSlide.size
    }

    override fun onBindViewHolder(holder: IntroSlideViewHolder, position: Int) {

        holder.bind(listIntroSlide[position])
    }
}
