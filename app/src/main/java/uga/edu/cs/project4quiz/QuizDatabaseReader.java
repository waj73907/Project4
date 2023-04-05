package uga.edu.cs.project4quiz;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.AsyncTask;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

/*
This class represents an object that can aynchronously read from the quizzes database.

 In order to read from the quizzes database, create a new ArrayList and instantiate it to the
 QuizDatabaseReaders execute function.

 EX:
 QuizDtabaseReader quizReader = new QuizDatabaseReader();
 ArrayList<Quiz> quizzes = quizReader.execute().get() //execute().get() will return the array list for you to interact with.
 */


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

        String queryString = "SELECT * FROM " + this.helper.QUIZ_TABLE;
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
