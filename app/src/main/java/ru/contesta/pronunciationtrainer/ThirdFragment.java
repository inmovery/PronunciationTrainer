package ru.contesta.pronunciationtrainer;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

import ru.contesta.pronunciationtrainer.timeselector.PickerView;
import ru.contesta.pronunciationtrainer.ui.auth.SignInActivity;

public class ThirdFragment extends Fragment {

    PickerView hoursSelector;
    PickerView minutesSelector;

    Button completeIntroSettings;
    TextView doNotNotify;

    public ThirdFragment() {
    }

    public static ThirdFragment newInstance() {
        ThirdFragment fragment = new ThirdFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NotNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        ViewGroup rootView = (ViewGroup)inflater.inflate(
                R.layout.fragment_third, container,
                false);

        hoursSelector = rootView.findViewById(R.id.hoursSelector);
        minutesSelector = rootView.findViewById(R.id.minutesSelector);

        completeIntroSettings = rootView.findViewById(R.id.complete_intro_settings);
        doNotNotify = rootView.findViewById(R.id.don_t_notify_btn);

        completeIntroSettings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentToLogInActivity = new Intent(getActivity(), SignInActivity.class);
                startActivity(intentToLogInActivity);
            }
        });

        doNotNotify.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentToLogInActivity = new Intent(getActivity(), SignInActivity.class);
                startActivity(intentToLogInActivity);
            }
        });

        List<String> hoursData = new ArrayList<>();
        List<String> minutesData = new ArrayList<>();

        // Fill hours data
        for (int i = 0; i < 24; i++) {
            if (i < 10) {
                hoursData.add("0" + i);
            } else {
                hoursData.add(String.valueOf(i));
            }
        }

        // Fill minutes data
        for (int i = 0; i < 60; i++) {
            minutesData.add(i < 10 ? "0" + i : "" + i);
        }

        hoursSelector.setData(hoursData);
        hoursSelector.setOnSelectListener(new PickerView.onSelectListener() {
            @Override
            public void onSelect(String text) {

            }
        });
        hoursSelector.setSelected(0);

        minutesSelector.setData(minutesData);
        minutesSelector.setOnSelectListener(new PickerView.onSelectListener() {
            @Override
            public void onSelect(String text) {

            }
        });
        minutesSelector.setSelected(30);

        return rootView;
    }
}