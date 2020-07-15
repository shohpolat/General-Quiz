package com.example.generalquizapp.Models;

import android.app.ActionBar;
import android.provider.BaseColumns;

public class Tables_Contracts {

    private Tables_Contracts(){

    }


    public static class QuestoinsTable implements BaseColumns {

        public static final String TABLE_NAME = "questions_table";
        public static final String COLUMN_QUESTION = "questoins";
        public static final String COLUMN_OPTION1 = "optoin1";
        public static final String COLUMN_OPTION2 = "optoin2";
        public static final String COLUMN_OPTION3 = "optoin3";
        public static final String COLUMN_OPTION4 = "optoin4";
        public static final String COLUMN_ANSWER = "answers";
        public static final String COLUMN_CATEGORY = "category";
        public static final String COLUMN_LANGUAGE = "language";
        public static final String COLUMN_DIFFICULTY = "difficulty";

    }

    public static class EnglishCategories implements BaseColumns{
        public static final String TABLE_NAME = "english_categories_table";
        public static final String COLUMN_NAME = "category_name";
        public static final String COLUMN_EASY_SCORE = "easy_scores";
        public static final String COLUMN_MEDIUM_SCORE = "medium_scores";
        public static final String COLUMN_HARD_SCORE = "hard_scores";
    }


    public static class UzbekCategories implements BaseColumns{

        public static final String TABLE_NAME = "uzbek_categories_table";
        public static final String COLUMN_NAME = "category_name";
        public static final String COLUMN_EASY_SCORE = "easy_scores";
        public static final String COLUMN_MEDIUM_SCORE = "medium_scores";
        public static final String COLUMN_HARD_SCORE = "hard_scores";

    }

    public static class HistoryTable implements BaseColumns{

        public static final String TABLE_NAME = "history";
        public static final String COLUMN_NAME = "name";
        public static final String COLUMN_DIFFICULTY = "difficulty";
        public static final String COLUMN_SCORE = "score";
        public static final String COLUMN_TIME = "time";
        public static final String COLUMN_LANGUAGE = "language";


    }


}
