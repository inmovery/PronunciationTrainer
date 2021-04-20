package ru.contesta.pronunciationtrainer;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

import ru.contesta.pronunciationtrainer.adapters.SelectNativeLanguageAdapter;
import ru.contesta.pronunciationtrainer.models.ChoiceLanguage;

public class FirstFragment extends Fragment  implements SelectNativeLanguageAdapter.OnSelectionListener {

    RecyclerView recyclerView;
    List<ChoiceLanguage> languagesList;
    ViewPager viewPager;

    public FirstFragment(ViewPager viewPager) {
        this.viewPager = viewPager;
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
                R.layout.fragment_first, container,
                false);

        recyclerView = rootView.findViewById(R.id.recycler);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        languagesList = new ArrayList<>();

        languagesList = getLanguagesList();

        recyclerView.setAdapter(new SelectNativeLanguageAdapter(languagesList, this));

        return rootView;
    }

    private List<ChoiceLanguage> getLanguagesList() {
        String[] languagesTitles = getResources().getStringArray(R.array.native_languages);
        List<ChoiceLanguage> languagesList = new ArrayList<>();
        for (String language : languagesTitles) {
            languagesList.add(new ChoiceLanguage(language));
        }

        return languagesList;
    }

    @Override
    public void OnSelectionClick(int position) {
        viewPager.setCurrentItem(1, true);
    }
}