package ru.contesta.pronunciationtrainer;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;

import java.util.ArrayList;
import java.util.List;

import ru.contesta.pronunciationtrainer.InkPageIndicator.InkPageIndicator;
import ru.contesta.pronunciationtrainer.adapters.SliderPagerAdapter;

public class TestInkPageIndicator extends AppCompatActivity {

    SliderPagerAdapter sliderPagerAdapter;
    ViewPager viewPager;
    InkPageIndicator inkPageIndicator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTheme(R.style.Theme_AppCompat);
        setContentView(R.layout.activity_test_indicator);

        viewPager = findViewById(R.id.pager);
        inkPageIndicator = findViewById(R.id.indicator);

        List<Fragment> listFragments = new ArrayList<>();
        listFragments.add(new FirstFragment(viewPager));
        listFragments.add(new SecondFragment());
        listFragments.add(new ThirdFragment());

        sliderPagerAdapter = new SliderPagerAdapter(getSupportFragmentManager(), listFragments);

        viewPager.setAdapter(sliderPagerAdapter);

        inkPageIndicator.setViewPager(viewPager);
    }
}