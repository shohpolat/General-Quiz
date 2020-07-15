package com.example.generalquizapp.Views;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.text.Html;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import com.example.generalquizapp.Presenters.CategoriesPresenter;
import com.example.generalquizapp.R;
import com.google.android.material.navigation.NavigationView;

public class CategoriesPage extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    public static final String SHARED_PREFS = "shared_prefs";
    public static final String EXTRA_LANG = "language";
    public static final String ID_EXTRA = "id_extra";
    public static final String DIFFICULTY_EXTRA = "difficulty_extra";
    public static final String FIRST_TIME_EXTRA = "first_time_extra";
    public static final String CATEGORY_EXTRA = "category_name";

    private CategoriesPresenter presenter;
    private boolean isFirstTime;


    DrawerLayout drawerLayout;
    Toolbar toolbar;
    ActionBarDrawerToggle toggle;
    NavigationView navigationView;
    TextView title_Text;


    private String LANGUAGE;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drawer_activty);


        isFirstTime = getSharedPreferences(SHARED_PREFS,MODE_PRIVATE).getBoolean(FIRST_TIME_EXTRA,true);

        if (isFirstTime){
            Intent intentFirst = new Intent(CategoriesPage.this,startingActivity.class);
            startActivity(intentFirst);
            finish();
        }else {
            loadNavViewAndToolbar();



            Intent intent = getIntent();

            LANGUAGE = intent.getStringExtra(startingActivity.LANG);
            if (LANGUAGE==null){
                LANGUAGE = getSharedPreferences(SHARED_PREFS,MODE_PRIVATE).getString(EXTRA_LANG,"");
            }
            presenter = new CategoriesPresenter(this,LANGUAGE);


            if (LANGUAGE.equals("e")) {
                title_Text.setText(R.string.category);
                presenter.setLanguageToEng();
            } else {
                title_Text.setText(R.string.fanlar);
                presenter.setLanguageToUzbek();

            }



            presenter.loadCategories();


            navigationView.setNavigationItemSelectedListener(this);
        }

    }


    private void loadNavViewAndToolbar() {
        drawerLayout = findViewById(R.id.drawer_layout);
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.open, R.string.close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();
        setTitle("");
        navigationView = findViewById(R.id.nav_view);
        title_Text = findViewById(R.id.title_text);

    }


    public void showToast(String message) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show();
    }

    public void startQuiz(int id,String langugae,String difficulty,String category) {

        saveSharedPrefs();

        Intent intent = new Intent(this, MainActivity.class);
        intent.putExtra(ID_EXTRA, id);
        intent.putExtra(EXTRA_LANG, langugae);
        intent.putExtra(DIFFICULTY_EXTRA,difficulty);
        intent.putExtra(CATEGORY_EXTRA,category);
        startActivity(intent);

    }

    private void saveSharedPrefs() {

        SharedPreferences sharedPreferences = getSharedPreferences(SHARED_PREFS, MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(EXTRA_LANG, presenter.getLanguage());
        editor.apply();

    }

    @Override
    public void onBackPressed() {

        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {

            drawerLayout.closeDrawer(GravityCompat.START);
        } else {

            super.onBackPressed();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        presenter.loadCategories();
    }

    @Override
    protected void onStop() {
        super.onStop();

        saveSharedPrefs();

    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {

        presenter.clickNavigationItems(item);
        drawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }

    public void setToEnglish() {


        MenuItem item_languages = navigationView.getMenu().findItem(R.id.language);
        MenuItem item_english = navigationView.getMenu().findItem(R.id._english);
        MenuItem item_uzbek = navigationView.getMenu().findItem(R.id._uzbek);
        MenuItem item_about = navigationView.getMenu().findItem(R.id._about);
        MenuItem item_share = navigationView.getMenu().findItem(R.id._share);
        MenuItem item_history = navigationView.getMenu().findItem(R.id._history);

        SpannableString slanguage = new SpannableString("Languages");
        SpannableString senglish = new SpannableString("English");
        SpannableString suzbek = new SpannableString("Uzbek");
        SpannableString sabout = new SpannableString("About");
        SpannableString sshare = new SpannableString("Share");
        SpannableString shistory = new SpannableString("History");

        slanguage.setSpan(new ForegroundColorSpan(Color.GRAY), 0, slanguage.length(), 0);
        senglish.setSpan(new ForegroundColorSpan(Color.GREEN), 0, senglish.length(), 0);
        suzbek.setSpan(new ForegroundColorSpan(Color.BLACK), 0, suzbek.length(), 0);
        sabout.setSpan(new ForegroundColorSpan(Color.BLACK), 0, sabout.length(), 0);
        sshare.setSpan(new ForegroundColorSpan(Color.BLACK), 0, sshare.length(), 0);
        shistory.setSpan(new ForegroundColorSpan(Color.BLACK),0,shistory.length(),0);

        item_languages.setTitle(slanguage);
        item_english.setTitle(senglish);
        item_uzbek.setTitle(suzbek);
        item_about.setTitle(sabout);
        item_share.setTitle(sshare);
        item_history.setTitle(shistory);

    }


    public void setToUzbek() {

        MenuItem item_languages = navigationView.getMenu().findItem(R.id.language);
        MenuItem item_english = navigationView.getMenu().findItem(R.id._english);
        MenuItem item_uzbek = navigationView.getMenu().findItem(R.id._uzbek);
        MenuItem item_about = navigationView.getMenu().findItem(R.id._about);
        MenuItem item_share = navigationView.getMenu().findItem(R.id._share);
        MenuItem item_history = navigationView.getMenu().findItem(R.id._history);

        SpannableString slanguage = new SpannableString("Tillar");
        SpannableString senglish = new SpannableString("Ingliz tili");
        SpannableString suzbek = new SpannableString("O'zbek tili");
        SpannableString sabout = new SpannableString("Ma'lumotnoma");
        SpannableString sshare = new SpannableString("Ulashish");
        SpannableString shistory = new SpannableString("Arxiv");

        slanguage.setSpan(new ForegroundColorSpan(Color.GRAY), 0, slanguage.length(), 0);
        senglish.setSpan(new ForegroundColorSpan(Color.BLACK), 0, senglish.length(), 0);
        suzbek.setSpan(new ForegroundColorSpan(Color.GREEN), 0, suzbek.length(), 0);
        sabout.setSpan(new ForegroundColorSpan(Color.BLACK), 0, sabout.length(), 0);
        sshare.setSpan(new ForegroundColorSpan(Color.BLACK), 0, sshare.length(), 0);
        shistory.setSpan(new ForegroundColorSpan(Color.BLACK), 0, shistory.length(), 0);

        item_languages.setTitle(slanguage);
        item_english.setTitle(senglish);
        item_uzbek.setTitle(suzbek);
        item_about.setTitle(sabout);
        item_share.setTitle(sshare);
        item_history.setTitle(shistory);
    }

    public void setToolbarTitle(String title) {
        title_Text.setText(title);
    }
}
