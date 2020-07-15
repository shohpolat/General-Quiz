package com.example.generalquizapp.Views;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.SystemClock;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.generalquizapp.R;

public class startingActivity extends AppCompatActivity{

    public static final String LANG = "language";

    Button english;
    Button uzbek;
    private long lastClicktime = 0;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_starting);

        english = findViewById(R.id.english_lang);
        uzbek = findViewById(R.id.uzbek_lang);


    }

    public void englishClick(View view){

        if (SystemClock.elapsedRealtime() - lastClicktime < 3000){
            Toast.makeText(startingActivity.this, "In progress", Toast.LENGTH_SHORT).show();
            return;
        }
        lastClicktime = SystemClock.elapsedRealtime();

        saveSharedPref();
        Intent intent = new Intent(startingActivity.this,CategoriesPage.class);
        intent.putExtra(LANG,"e");
        startActivity(intent);
        finish();

    }
    public void uzbekClick(View view){

        if (SystemClock.elapsedRealtime() - lastClicktime < 3000){
            Toast.makeText(startingActivity.this, "In progress", Toast.LENGTH_SHORT).show();
            return;
        }
        lastClicktime = SystemClock.elapsedRealtime();

        saveSharedPref();
        Intent intent = new Intent(startingActivity.this,CategoriesPage.class);
        intent.putExtra(LANG,"u");
        startActivity(intent);
        finish();

    }

    private void saveSharedPref(){

        SharedPreferences sharedPreferences = getSharedPreferences(CategoriesPage.SHARED_PREFS,MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putBoolean(CategoriesPage.FIRST_TIME_EXTRA,false);
        editor.apply();

    }

}
