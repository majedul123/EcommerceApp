package com.itmedicious.spliff.activity

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import androidx.appcompat.app.AppCompatActivity
import com.itmedicious.spliff.R

class SplashScreenActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_spalash_screen)

        val mSplashImage: View = findViewById(R.id.splash_logo)
        val splashAnimImage = AnimationUtils.loadAnimation(this, R.anim.splash_anim_img)
        mSplashImage.startAnimation(splashAnimImage)
        splashAnimImage.setAnimationListener(object : Animation.AnimationListener {
            override fun onAnimationStart(animation: Animation) {}
            override fun onAnimationEnd(animation: Animation) {
                val intent= Intent(this@SplashScreenActivity,PrimaryActivity::class.java)
                startActivity(intent)
            }

            override fun onAnimationRepeat(animation: Animation) {}
        })




    }
}