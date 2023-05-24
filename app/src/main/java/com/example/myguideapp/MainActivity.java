package com.example.myguideapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        androidx.cardview.widget.CardView card = findViewById(R.id.card);
        GradientDrawable border = new GradientDrawable();
        //border.setColor(); // цвет фона
        border.setStroke(5, Color.parseColor("#080301"));
        border.setCornerRadius(20);
        int color = Color.argb(128, 128, 128, 128);
        border.setColor(color);

        card.setBackground(border);
        // устанавливаем фон

    }
    public void onGoOnClick(View view){
        EditText editSurname = findViewById(R.id.surname_field);
        EditText editName = findViewById(R.id.name_field);
        EditText editEmail = findViewById(R.id.email_field);
        if (allFieldsPassed(editName, editSurname, editEmail)){
            User user = new User(editName.getText().toString(), editSurname.getText().toString(),
                    editEmail.getText().toString());
            Intent intent = new Intent(MainActivity.this, SightsActivity.class);
            intent.putExtra("user", user);
            startActivity(intent);
            //finish();

        }
        else {
            Toast.makeText(MainActivity.this, "Все поля должны быть заполнены",
                    Toast.LENGTH_LONG).show();        }


    }
    private boolean allFieldsPassed(EditText e1, EditText e2, EditText e3){
        return !e1.getText().toString().equals("") && !e2.getText().toString().equals("") &&
                !e3.getText().toString().equals("");
    }
    //сделать с регулярными выражениями

}