package com.example.generalquizapp.Models;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.security.PublicKey;
import java.util.ArrayList;

public class Repository {

    private DATABASE_HELPER dbhelper;
    private SQLiteDatabase database;

    private static Repository instance;

    private Repository(Context context) {
        dbhelper = new DATABASE_HELPER(context);
        database = dbhelper.getReadableDatabase();
    }

    public static synchronized Repository getInstance(Context context) {
        if (instance == null) {
            instance = new Repository(context);
        }
        return instance;
    }


    public ArrayList<Question> getAllQuestions() {

        Cursor cursor = database.rawQuery("SELECT * FROM " + Tables_Contracts.QuestoinsTable.TABLE_NAME, null);
        ArrayList<Question> list = new ArrayList<>();

        if (cursor.moveToFirst()) {
            do {
                Question question = new Question();

                question.setQuestion(cursor.getString(cursor.getColumnIndex(Tables_Contracts.QuestoinsTable.COLUMN_QUESTION)));
                question.setOption1(cursor.getString(cursor.getColumnIndex(Tables_Contracts.QuestoinsTable.COLUMN_OPTION1)));
                question.setOption2(cursor.getString(cursor.getColumnIndex(Tables_Contracts.QuestoinsTable.COLUMN_OPTION2)));
                question.setOption3(cursor.getString(cursor.getColumnIndex(Tables_Contracts.QuestoinsTable.COLUMN_OPTION3)));
                question.setOption4(cursor.getString(cursor.getColumnIndex(Tables_Contracts.QuestoinsTable.COLUMN_OPTION4)));
                question.setAnswer(cursor.getInt(cursor.getColumnIndex(Tables_Contracts.QuestoinsTable.COLUMN_ANSWER)));
                question.setCategory(cursor.getInt(cursor.getColumnIndex(Tables_Contracts.QuestoinsTable.COLUMN_CATEGORY)));

                list.add(question);

            } while (cursor.moveToNext());
        }
        cursor.close();
        return list;
    }


    public ArrayList<Category_E> getAllEnglishCategories() {

        Cursor cursor = database.rawQuery("SELECT * FROM " + Tables_Contracts.EnglishCategories.TABLE_NAME, null);
        ArrayList<Category_E> categories = new ArrayList<>();

        if (cursor.moveToFirst()) {
            do {

                Category_E category_e = new Category_E();
                category_e.set_ID(cursor.getInt(cursor.getColumnIndex(Tables_Contracts.EnglishCategories._ID)));
                category_e.setName(cursor.getString(cursor.getColumnIndex(Tables_Contracts.EnglishCategories.COLUMN_NAME)));
                category_e.setEasy_score(cursor.getInt(cursor.getColumnIndex(Tables_Contracts.EnglishCategories.COLUMN_EASY_SCORE)));
                category_e.setMedium_score(cursor.getInt(cursor.getColumnIndex(Tables_Contracts.EnglishCategories.COLUMN_MEDIUM_SCORE)));
                category_e.setHard_score(cursor.getInt(cursor.getColumnIndex(Tables_Contracts.EnglishCategories.COLUMN_HARD_SCORE)));

                categories.add(category_e);

            } while (cursor.moveToNext());
        }
        cursor.close();
        return categories;
    }

    public ArrayList<Category_U> getAllUzbekCategories() {


        Cursor cursor = database.rawQuery("SELECT * FROM " + Tables_Contracts.UzbekCategories.TABLE_NAME, null);
        ArrayList<Category_U> categories = new ArrayList<>();

        if (cursor.moveToFirst()) {
            do {

                Category_U category_u = new Category_U();

                category_u.set_Id(cursor.getInt(cursor.getColumnIndex(Tables_Contracts.UzbekCategories._ID)));
                category_u.setName(cursor.getString(cursor.getColumnIndex(Tables_Contracts.UzbekCategories.COLUMN_NAME)));
                category_u.setEasy_score(cursor.getInt(cursor.getColumnIndex(Tables_Contracts.UzbekCategories.COLUMN_EASY_SCORE)));
                category_u.setMedium_score(cursor.getInt(cursor.getColumnIndex(Tables_Contracts.UzbekCategories.COLUMN_MEDIUM_SCORE)));
                category_u.setHard_score(cursor.getInt(cursor.getColumnIndex(Tables_Contracts.UzbekCategories.COLUMN_HARD_SCORE)));

                categories.add(category_u);

            } while (cursor.moveToNext());
        }

        cursor.close();
        return categories;

    }

    public ArrayList<Question> getQuestions(int id, String language,String difficulty) {
        String[] selections = new String[]{String.valueOf(id), language,difficulty};
        Cursor cursor = database.rawQuery("SELECT * FROM " + Tables_Contracts.QuestoinsTable.TABLE_NAME + " WHERE " +
                Tables_Contracts.QuestoinsTable.COLUMN_CATEGORY + " = ? " + " AND " + Tables_Contracts.QuestoinsTable.COLUMN_LANGUAGE + " = ? " + " AND " + Tables_Contracts.QuestoinsTable.COLUMN_DIFFICULTY + " = ?", selections);

        ArrayList<Question> selectedQuestions = new ArrayList<>();

        if (cursor.moveToFirst()) {
            do {

                Question question = new Question();

                question.setQuestion(cursor.getString(cursor.getColumnIndex(Tables_Contracts.QuestoinsTable.COLUMN_QUESTION)));
                question.setOption1(cursor.getString(cursor.getColumnIndex(Tables_Contracts.QuestoinsTable.COLUMN_OPTION1)));
                question.setOption2(cursor.getString(cursor.getColumnIndex(Tables_Contracts.QuestoinsTable.COLUMN_OPTION2)));
                question.setOption3(cursor.getString(cursor.getColumnIndex(Tables_Contracts.QuestoinsTable.COLUMN_OPTION3)));
                question.setOption4(cursor.getString(cursor.getColumnIndex(Tables_Contracts.QuestoinsTable.COLUMN_OPTION4)));
                question.setAnswer(cursor.getInt(cursor.getColumnIndex(Tables_Contracts.QuestoinsTable.COLUMN_ANSWER)));
                question.setCategory(cursor.getInt(cursor.getColumnIndex(Tables_Contracts.QuestoinsTable.COLUMN_CATEGORY)));
                question.set_ID(cursor.getInt(cursor.getColumnIndex(Tables_Contracts.QuestoinsTable._ID)));
                question.setLanguage(cursor.getString(cursor.getColumnIndex(Tables_Contracts.QuestoinsTable.COLUMN_LANGUAGE)));
                question.setDifficulty(cursor.getString(cursor.getColumnIndex(Tables_Contracts.QuestoinsTable.COLUMN_DIFFICULTY)));

                selectedQuestions.add(question);

            } while (cursor.moveToNext());
        }
        cursor.close();
        return selectedQuestions;
    }

    public void updateEnglishCategory(int top_score, int cat_id,String difficulty) {

        String[] id = new String[]{String.valueOf(cat_id)};
        ContentValues contentValues = new ContentValues();

        if (difficulty.equals("easy")){
            contentValues.put(Tables_Contracts.EnglishCategories.COLUMN_EASY_SCORE,top_score);
        }else if (difficulty.equals("normal")){
            contentValues.put(Tables_Contracts.EnglishCategories.COLUMN_MEDIUM_SCORE,top_score);
        }else {
            contentValues.put(Tables_Contracts.EnglishCategories.COLUMN_HARD_SCORE,top_score);
        }

        database.update(Tables_Contracts.EnglishCategories.TABLE_NAME, contentValues, Tables_Contracts.EnglishCategories._ID + " = ?", id);

    }

    public void updateUzbekCategory(int top_score, int cat_id,String difficulty) {

        String[] id = new String[]{String.valueOf(cat_id)};
        ContentValues contentValues = new ContentValues();

        if (difficulty.equals("easy")){
            contentValues.put(Tables_Contracts.UzbekCategories.COLUMN_EASY_SCORE,top_score);
        }else if (difficulty.equals("normal")){
            contentValues.put(Tables_Contracts.UzbekCategories.COLUMN_MEDIUM_SCORE,top_score);
        }else {
            contentValues.put(Tables_Contracts.UzbekCategories.COLUMN_HARD_SCORE,top_score);
        }

        database.update(Tables_Contracts.UzbekCategories.TABLE_NAME, contentValues, Tables_Contracts.UzbekCategories._ID + " = ?", id);
    }



    public void insertToHistory(String category, String difficulty, String score,String language){

        ContentValues contentValues = new ContentValues();

        contentValues.put(Tables_Contracts.HistoryTable.COLUMN_NAME,category);
        contentValues.put(Tables_Contracts.HistoryTable.COLUMN_DIFFICULTY,difficulty);
        contentValues.put(Tables_Contracts.HistoryTable.COLUMN_SCORE,score);
        contentValues.put(Tables_Contracts.HistoryTable.COLUMN_LANGUAGE,language);

        database.insert(Tables_Contracts.HistoryTable.TABLE_NAME,null,contentValues);


    }

    public ArrayList<History> getAllHistory(String language){

        String[] selectionArgs = new String[]{language};
        String selection = Tables_Contracts.HistoryTable.COLUMN_LANGUAGE + " = ?";

        ArrayList<History> histories = new ArrayList<>();
        Cursor cursor = database.query(
                Tables_Contracts.HistoryTable.TABLE_NAME,
                null,
                selection,
                selectionArgs,
                null,
                null,
                Tables_Contracts.HistoryTable.COLUMN_TIME + " DESC"
        );

      if (cursor.moveToFirst()){

          do {

              History history = new History();
              history.setName(cursor.getString(cursor.getColumnIndex(Tables_Contracts.HistoryTable.COLUMN_NAME)));
              history.setDifficulty(cursor.getString(cursor.getColumnIndex(Tables_Contracts.HistoryTable.COLUMN_DIFFICULTY)));
              history.setScore(cursor.getString(cursor.getColumnIndex(Tables_Contracts.HistoryTable.COLUMN_SCORE)));
              history.setTime(cursor.getString(cursor.getColumnIndex(Tables_Contracts.HistoryTable.COLUMN_TIME)));

              histories.add(history);

          }while (cursor.moveToNext());
      }
        cursor.close();
      return histories;
    }

}
