package com.example.myguideapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private String pathFile = "guideeee.bin";

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        try {
            receiveInfo();
        } catch (IOException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
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
    private void receiveInfo() throws IOException, ClassNotFoundException {
        File file = new File(getFilesDir(), pathFile);
        if(file.length() == 0){
            return;
        }
        FileInputStream fis = openFileInput(pathFile);
        ObjectInputStream ois = new ObjectInputStream(fis);
        int numOfUsers = ois.readInt();
        for (int i = 0; i < numOfUsers; ++i){
            User us = (User)ois.readObject();
            User newUser = new User(us.getName(), us.getSurname(), us.getEmail(), us.getFavourSights());
            SessionInfo.addUser(newUser);
        }
        ois.close();
        fis.close();



    }
    public void onGoOnClick(View view){
        EditText editSurname = findViewById(R.id.surname_field);
        EditText editName = findViewById(R.id.name_field);
        EditText editEmail = findViewById(R.id.email_field);
        if (allFieldsPassed(editName, editSurname, editEmail)){
            User user;
            int index = indexOfUser(editName.getText().toString(), editSurname.getText().toString(),
                    editEmail.getText().toString());
            if (index == -1){
                user = new User(editName.getText().toString(), editSurname.getText().toString(),
                        editEmail.getText().toString(), new ArrayList<>());
                SessionInfo.addUser(user);
            }
            else {
                user = SessionInfo.getAllUsers().get(index);
            }
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
    private void saveInfo() throws IOException {
        FileOutputStream fos = openFileOutput(pathFile, Context.MODE_PRIVATE);
        ObjectOutputStream oos = new ObjectOutputStream(fos);
        oos.writeInt(SessionInfo.getAllUsers().size());
        for (User us: SessionInfo.getAllUsers()){
            oos.writeObject(us);
        }
        oos.close();
        fos.close();
    }
    private int indexOfUser(String name, String surname, String email){
        for (int i = 0; i < SessionInfo.getAllUsers().size(); ++i){
            if (SessionInfo.getAllUsers().get(i).getEmail().equals(email)
            && SessionInfo.getAllUsers().get(i).getName().equals(name)
            && SessionInfo.getAllUsers().get(i).getSurname().equals(surname)){
                return i;
            }
        }
        return -1;
    }
    @Override
    protected void onPause() {
        try {
            saveInfo();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        super.onPause();
    }
    @Override
    protected void onStop() {
        try {
            saveInfo();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        super.onStop();
    }
    @Override
    protected void onDestroy() {
        try {
            saveInfo();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        super.onDestroy();
    }
    @Override
    public void onBackPressed(){
        super.onBackPressed();
        try {
            saveInfo();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    @Override
    protected void onRestart(){
        super.onRestart();
        try {
            saveInfo();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

}