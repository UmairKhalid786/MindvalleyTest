package com.mindvalley.test.ui.splash

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import androidx.appcompat.app.AppCompatActivity
import com.mindvalley.test.R
import com.mindvalley.test.ui.home.HomeListActivity
import kotlinx.android.synthetic.main.activity_splash.*


class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        val myAnim: Animation = AnimationUtils.loadAnimation(this, R.anim.bounce)
        logo.startAnimation(myAnim)

        Handler().postDelayed(takeNext() , 1000)
    }

    private fun takeNext(): Runnable {
      return Runnable {
          startActivity(Intent(applicationContext , HomeListActivity::class.java))
          finish()
      }
    }
}