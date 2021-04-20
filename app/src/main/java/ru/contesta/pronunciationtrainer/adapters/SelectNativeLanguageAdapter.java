package ru.contesta.pronunciationtrainer.adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.core.content.res.ResourcesCompat;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

import ru.contesta.pronunciationtrainer.FirstFragment;
import ru.contesta.pronunciationtrainer.R;
import ru.contesta.pronunciationtrainer.models.ChoiceLanguage;

public class SelectNativeLanguageAdapter extends RecyclerView.Adapter<SelectNativeLanguageAdapter.SelectNativeLanguageViewHolder> {
    private List<ChoiceLanguage> listLanguages;
    private OnSelectionListener mOnSelectionListener;

    private int selectedPosition = -1;

    /*
    * -1 : no default selection
    *  0..* : 1st item selected
    */

    public void SetChoiceLanguages(List<ChoiceLanguage> listLanguages) {
        this.listLanguages = new ArrayList<>();
        this.listLanguages = listLanguages;
        notifyDataSetChanged();
    }

    public SelectNativeLanguageAdapter(List<ChoiceLanguage> listLanguages, OnSelectionListener onSelectionListener) {
        this.listLanguages = listLanguages;
        this.mOnSelectionListener = onSelectionListener;
    }

    @NonNull
    @Override
    public SelectNativeLanguageViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.single_native_language, parent, false);
        return new SelectNativeLanguageViewHolder(view, mOnSelectionListener);
    }

    @Override
    public void onBindViewHolder(@NonNull SelectNativeLanguageViewHolder holder, int position) {
        holder.actionButton.setText(listLanguages.get(position).getLanguageTitle());

        holder.actionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectedPosition = position;
                notifyDataSetChanged();
                holder.onSelectionListener.OnSelectionClick(position);
            }
        });

        if (selectedPosition == position) {
            holder.actionButton.setBackground(ResourcesCompat.getDrawable(holder.actionButton.getContext().getResources(), R.drawable.button_white_pressed_background, null));
        } else {
            holder.actionButton.setBackground(ResourcesCompat.getDrawable(holder.actionButton.getContext().getResources(), R.drawable.button_white_background, null));
        }
    }

    @Override
    public int getItemCount() {
        return listLanguages.size();
    }

    public class SelectNativeLanguageViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        Button actionButton;
        OnSelectionListener onSelectionListener;

        public SelectNativeLanguageViewHolder(@NotNull View itemView, OnSelectionListener onSelectionListener) {
            super(itemView);
            actionButton = (Button)itemView.findViewById(R.id.actionButton);
            this.onSelectionListener = onSelectionListener;

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            onSelectionListener.OnSelectionClick(getBindingAdapterPosition());
        }
    }

    public ChoiceLanguage getSelectedLanguage() {
        if (selectedPosition != -1) {
            return listLanguages.get(selectedPosition);
        }

        return null;
    }

    public interface OnSelectionListener {
        void OnSelectionClick(int position);
    }
}
