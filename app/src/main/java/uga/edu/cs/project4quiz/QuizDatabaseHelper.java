package uga.edu.cs.project4quiz;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class QuizDatabaseHelper extends SQLiteOpenHelper {

    public static final String QUIZ_TABLE = "QUIZ_TABLE";
    public static final String COLUMN_AMOUNT_CORRECT = "AMOUNT_CORRECT";
    public static final String COLUMN_DATE_COMPLETED = "DATE_COMPELTED";
    public static final String COLUMN_ID = "ID";
    public QuizDatabaseHelper(@Nullable Context context) {
        super(context, "quizzes.db", null, 1);

    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTableStatement = "CREATE TABLE " + QUIZ_TABLE + "(" + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + COLUMN_AMOUNT_CORRECT + " INTEGER, " + COLUMN_DATE_COMPLETED + " TEXT)";
        db.execSQL(createTableStatement);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {}


}
