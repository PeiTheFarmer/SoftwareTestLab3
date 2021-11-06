package com.example.lab3;

import android.annotation.SuppressLint;
import android.graphics.Paint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.Date;
import java.util.List;

public class RecycleViewAdapter extends RecyclerView.Adapter<RecycleViewAdapter.ItemHolder> {
    private List<NoteBean> mItems;

    RecycleViewAdapter(List<NoteBean> items) {
        mItems = items;
    }

    @Override
    public int getItemCount() {
        return mItems.size();
    }

    @Override
    public void onBindViewHolder(@NonNull ItemHolder holder, @SuppressLint("RecyclerView") int position) {

        holder.content.setText(mItems.get(position).getContent());
        holder.date.setText(String.valueOf(mItems.get(position).getDate()));
        if (mItems.get(position).getState()) {
            holder.checkBox.setChecked(false);
        } else {
            holder.checkBox.setChecked(true);
            holder.content.setPaintFlags(Paint.STRIKE_THRU_TEXT_FLAG);
        }

        holder.imageButton.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {
                deleteNote(mItems.get(position));
                mItems.remove(position);
                notifyDataSetChanged();
            }
        });

        holder.checkBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Boolean status = mItems.get(position).getState();
                mItems.get(position).setState(!status);
                updateNote(mItems.get(position).getContent(), mItems.get(position).getDate(), mItems.get(position).getState());
                notifyItemChanged(position);
            }
        });

    }

    private void deleteNote(NoteBean noteBean) {
        NoteDatabase.getDefault(MainActivity.getContext()).getNoteDao().delete(noteBean);
    }

    private void updateNote(String name, Date date, Boolean state) {
        NoteBean noteBean = new NoteBean(name, date, state);
        NoteDatabase.getDefault(MainActivity.getContext()).getNoteDao().update(noteBean);
    }

    @NonNull
    @Override
    public ItemHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ItemHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.note_bean, parent, false));
    }

    class ItemHolder extends RecyclerView.ViewHolder {
        TextView content;
        TextView date;
        CheckBox checkBox;
        ImageButton imageButton;

        ItemHolder(View item) {
            super(item);
            content = item.findViewById(R.id.content);
            date = item.findViewById(R.id.date);
            checkBox = item.findViewById(R.id.checkBox);
            imageButton = item.findViewById(R.id.imageButton);
        }
    };
}

