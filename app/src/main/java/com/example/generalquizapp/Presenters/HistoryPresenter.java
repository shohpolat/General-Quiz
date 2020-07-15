package com.example.generalquizapp.Presenters;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.generalquizapp.Adapters.HistoryAdapter;
import com.example.generalquizapp.Models.Repository;
import com.example.generalquizapp.R;
import com.example.generalquizapp.Views.HistoryPage;

import java.util.ArrayList;

public class HistoryPresenter {

    private HistoryPage view;
    private Repository repository;
    private String LANGUAGE;
    private ArrayList histories;
    private HistoryAdapter adapter;

    public HistoryPresenter(HistoryPage view, String languge){

        this.view = view;
        repository = Repository.getInstance(view);
        LANGUAGE = languge;

    }


    public void showHistory(){

        setHistories();
        adapter = new HistoryAdapter(histories);
        RecyclerView recyclerView = view.findViewById(R.id.history_recyclerview);
        recyclerView.setLayoutManager(new LinearLayoutManager(view));
        recyclerView.setAdapter(adapter);


    }

    public void setHistories(){
        if (LANGUAGE.equals("e")){
            histories = repository.getAllHistory("e");
        }else {
            histories = repository.getAllHistory("u");
        }
    }

}
