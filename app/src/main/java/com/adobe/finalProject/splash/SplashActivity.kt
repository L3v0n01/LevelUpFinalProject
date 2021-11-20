package com.adobe.finalProject.splash

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatImageView
import com.adobe.finalProject.MainActivity
import com.adobe.finalProject.R
import com.adobe.finalProject.runDelayed

class SplashActivity:AppCompatActivity(R.layout.activity_splash) {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        runDelayed(5500) {
            startActivity(Intent(this,MainActivity::class.java))
        }
        val nImage : AppCompatImageView = findViewById(R.id.nTimeIv)
        val nAnimationPhoto: Animation = AnimationUtils.loadAnimation(this , R.anim.ntime_anim)
        nImage.startAnimation(nAnimationPhoto)
    }
}
