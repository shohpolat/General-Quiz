package com.example.generalquizapp.Views;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.generalquizapp.R;

public class AboutPage extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);

        Button back = findViewById(R.id.about_back);
        TextView text = findViewById(R.id.about_text);

        String LANGUAGE = getIntent().getStringExtra(CategoriesPage.EXTRA_LANG);

        if (LANGUAGE.equals("e")){
            text.setText(R.string.about_eng);
            back.setText(R.string.about_back_eng);
        }else {
            text.setText(R.string.about_uzb);
            back.setText(R.string.about_back_uzb);
        }

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });


    }
}
