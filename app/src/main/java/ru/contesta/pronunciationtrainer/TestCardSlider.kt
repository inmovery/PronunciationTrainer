package ru.contesta.pronunciationtrainer

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

import ru.contesta.pronunciationtrainer.cardslider.CardSliderIndicator
import ru.contesta.pronunciationtrainer.cardslider.CardSliderViewPager
import kotlinx.android.synthetic.main.activity_test_card_slider.*

class TestCardSlider : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_test_card_slider)

        val movies = arrayListOf<Movie>()

        val posters = resources.obtainTypedArray(R.array.posters)

        for (i in resources.getStringArray(R.array.titles).indices) {
            movies.add(
                    Movie(
                            posters.getResourceId(i, -1),
                            resources.getStringArray(R.array.titles)[i],
                            resources.getStringArray(R.array.overviews)[i]
                    )
            )
        }

        posters.recycle()

        viewPager.adapter = MovieAdapter(movies)

        viewPager.smallScaleFactor = 0.7f
        viewPager.smallAlphaFactor = 0.5f
        viewPager.autoSlideTime = CardSliderViewPager.STOP_AUTO_SLIDING
        indicator.indicatorsToShow = CardSliderIndicator.UNLIMITED_INDICATORS
    }
}