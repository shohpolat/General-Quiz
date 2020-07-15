package com.example.generalquizapp.Presenters;

import android.content.Intent;
import android.graphics.Color;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
import android.view.MenuItem;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.generalquizapp.Models.Category_E;
import com.example.generalquizapp.Models.Category_U;
import com.example.generalquizapp.Models.Repository;
import com.example.generalquizapp.R;
import com.example.generalquizapp.Views.AboutPage;
import com.example.generalquizapp.Views.CategoriesPage;
import com.example.generalquizapp.Adapters.CategoryAdapter;
import com.example.generalquizapp.Views.HistoryPage;

import java.util.ArrayList;

public class CategoriesPresenter {

    private Repository repository;
    private CategoriesPage view;
    private ArrayList categoriesList;

    private String LANGUAGE;
    private CategoryAdapter adapter;


    public CategoriesPresenter(CategoriesPage view,String language) {
        this.view = view;
        repository = Repository.getInstance(this.view);
        this.LANGUAGE = language;
        setCategoriesList();
        adapter = new CategoryAdapter(categoriesList,view);
    }


    public void loadCategories() {

        setCategoriesList();
        adapter.setCategories(categoriesList);
        RecyclerView recyclerView = view.findViewById(R.id.recyclerCategory);

        recyclerView.setLayoutManager(new GridLayoutManager(view, 1, GridLayoutManager.VERTICAL, false));

        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(adapter);

        adapter.setOnClickListener(new CategoryAdapter.OnClickListener() {
            @Override
            public void OnClick(int index,String difficulty,String categrory) {

                view.startQuiz(index,LANGUAGE,difficulty,categrory);

            }
        });



    }

    public void clickNavigationItems(MenuItem item){



        switch (item.getItemId()){

            case R.id._english:
                Toast.makeText(view, R.string.english_selected, Toast.LENGTH_SHORT).show();
                setLanguageToEng();
                loadCategories();

                break;
            case R.id._uzbek:
                Toast.makeText(view, R.string.uzbek_selected, Toast.LENGTH_SHORT).show();
                setLanguageToUzbek();
                loadCategories();

                break;
            case R.id._history:
                Intent history_intent = new Intent(view, HistoryPage.class);
                history_intent.putExtra(CategoriesPage.EXTRA_LANG,LANGUAGE);
                view.startActivity(history_intent);
                break;

            case R.id._about:

                Intent about_intent = new Intent(view, AboutPage.class);
                about_intent.putExtra(CategoriesPage.EXTRA_LANG,LANGUAGE);
                view.startActivity(about_intent);

                break;
            case R.id._share:

                Intent sharingIntent = new Intent(android.content.Intent.ACTION_SEND);
                sharingIntent.setType("text/plain");
                String shareBody = "Here is the share content body";
                sharingIntent.putExtra(android.content.Intent.EXTRA_SUBJECT, "Subject Here");
                sharingIntent.putExtra(android.content.Intent.EXTRA_TEXT, shareBody);
                view.startActivity(Intent.createChooser(sharingIntent, "Share via"));
                break;

        }


    }

    public void setLanguageToEng(){

        LANGUAGE = "e";
        setCategoriesList();
        view.setToEnglish();
        view.setToolbarTitle("Categories");

    }

    public void setLanguageToUzbek(){
        LANGUAGE = "u";
        setCategoriesList();
        view.setToUzbek();
        view.setToolbarTitle("Fanlar");
    }

    public String getLanguage(){
        return LANGUAGE;
    }

    private void setCategoriesList(){

        if (LANGUAGE.equals("e")){
            categoriesList = repository.getAllEnglishCategories();
        }else {
            categoriesList = repository.getAllUzbekCategories();
        }

    }


}
