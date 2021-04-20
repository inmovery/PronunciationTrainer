package ru.contesta.pronunciationtrainer.ui.introduction

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.view.Window
import androidx.appcompat.app.AppCompatActivity

import ru.contesta.pronunciationtrainer.R
import ru.contesta.pronunciationtrainer.TestCardSlider
import ru.contesta.pronunciationtrainer.TestInkPageIndicator

class SplashScreenActivity : AppCompatActivity() {
    private fun getSplashScreenDuration() = 2000L

    override fun onCreate(savedInstanceState: Bundle?) {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)

        scheduleSplashScreen()
    }

    private fun scheduleSplashScreen() {
        val splashScreenDuration = getSplashScreenDuration()
        Handler().postDelayed(
                {
                    val intentToSelectNativeLanguage = Intent(SplashScreenActivity@this, TestInkPageIndicator::class.java)
                    startActivity(intentToSelectNativeLanguage)
                    finish()
                },
                splashScreenDuration
        )
    }
}