package com.example.myguideapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;

public class ListAdapter extends ArrayAdapter<String> {
    private List<String> dataList; // Список переданных строк
    private List<String> favList;

    public ListAdapter(Context context, int resource, List<String> data, List<String> favData) {
        super(context, resource, data);
        dataList = data;
        favList = favData;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        ViewHolder holder;

        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.list_sights, parent, false);

            holder = new ViewHolder();
            holder.button = convertView.findViewById(R.id.buttonSight);
            holder.checkbox = convertView.findViewById(R.id.customCheckBox);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        String buttonText = getItem(position);
        holder.button.setText(buttonText);

        // Проверяем, содержится ли текст кнопки в переданном списке строк
        if (favList.contains(buttonText)) {
            holder.checkbox.setChecked(true);
        } else {
            holder.checkbox.setChecked(false);
        }

        return convertView;
    }

    private static class ViewHolder {
        Button button;
        CheckBox checkbox;
    }
}
