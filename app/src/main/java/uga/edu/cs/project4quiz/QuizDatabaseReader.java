package uga.edu.cs.project4quiz;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.AsyncTask;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class QuizDatabaseReader extends AsyncTask<Void, Void, ArrayList<Quiz>> {
    Context ct;
    QuizDatabaseHelper helper;

    public QuizDatabaseReader(Context c) {
        this.ct = c;
        this.helper = new QuizDatabaseHelper(this.ct);
    }

    @Override
    protected ArrayList<Quiz> doInBackground(Void... voids) {
        return readAllQuizzes();
    }

    public ArrayList<Quiz> readAllQuizzes() {
        ArrayList<Quiz> returnList = new ArrayList<>();

        String queryString = "SELECT * FROM " + helper.QUIZ_TABLE;
        SQLiteDatabase db = this.helper.getReadableDatabase();
        Cursor cursor = db.rawQuery(queryString, null);

        if (cursor.moveToFirst()) {
            do {
                int quizID = cursor.getInt(0);
                int amountCorrect = cursor.getInt(1);
                String dateCompleted = cursor.getString(2);
                Quiz q = new Quiz(quizID, amountCorrect, dateCompleted);
                returnList.add(q);
            } while(cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return returnList;
    }
}
