package com.example.myguideapp;

import android.content.Intent;

import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;

import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;

import androidx.recyclerview.widget.RecyclerView;


public class FavourActivity extends AppCompatActivity {

    private Sight[] favArr;
    private RecyclerView lw;
    private User user;
    private Sight[] allsights;
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favour);
        Intent intent = getIntent();
        favArr = (Sight[])intent.getSerializableExtra("favsights");
        user = (User)intent.getSerializableExtra("user");
        allsights = (Sight[])intent.getSerializableExtra("allsights");
        String[] favNames = new String[favArr.length];
        for (int j = 0; j < favArr.length; ++j){
            favNames[j] = favArr[j].getName();
        }
        if (favArr.length ==0){
            Toast.makeText(FavourActivity.this,"В избранное пока ничего не добавлено",
                    Toast.LENGTH_LONG).show();
        }
        System.out.println(favNames.length);
        androidx.cardview.widget.CardView card = findViewById(R.id.cardFav);
        GradientDrawable border = new GradientDrawable();

        border.setCornerRadius(20);
        card.setBackground(border);
        lw = findViewById(R.id.recyclerFav);
        MyAdapter myAdapter = new MyAdapter(favNames);
        lw.setAdapter(myAdapter);
        lw.setLayoutManager(new GridLayoutManager(this, 1));
    }

    public void onFavSightClick(View view){
        // Получение позиции элемента при клике на него
        lw.addOnItemTouchListener(new RecyclerView.OnItemTouchListener() {
            @Override
            public boolean onInterceptTouchEvent(@NonNull RecyclerView rv, @NonNull MotionEvent e) {
                return true;
            }

            @Override
            public void onTouchEvent(@NonNull RecyclerView rv, @NonNull MotionEvent e) {
                View childView = rv.findChildViewUnder(e.getX(), e.getY());
                if (childView != null && e.getAction() == MotionEvent.ACTION_UP) {
                    int pos = rv.getChildAdapterPosition(childView);
                    Sight currentSight = favArr[pos];
                    Intent intent = new Intent(FavourActivity.this, MapActivity.class);
                    intent.putExtra("sight", currentSight);
                    intent.putExtra("allsights", allsights);
                    startActivity(intent);
                }
            }

            @Override
            public void onRequestDisallowInterceptTouchEvent(boolean disallowIntercept) {}
        });

    }


    public void onMainClick(View view){
        Intent intent = new Intent(FavourActivity.this, SightsActivity.class);
        intent.putExtra("user", user);
        startActivity(intent);
    }
}
