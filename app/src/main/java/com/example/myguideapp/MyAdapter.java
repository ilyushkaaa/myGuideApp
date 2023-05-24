package com.example.myguideapp;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {

    private String[] mData;

    public MyAdapter(String[] data) {
        mData = data;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // Создаем новый элемент списка, используя макет для элемента списка
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_favourite,
                parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        // Заполняем данные элемента списка на основе его позиции
        String item = mData[position];
        holder.button.setText(item);
    }

    @Override
    public int getItemCount() {
        // Возвращаем количество элементов в списке
        return mData.length;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public Button button;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            button = itemView.findViewById(R.id.buttonFav);
        }
    }
}
