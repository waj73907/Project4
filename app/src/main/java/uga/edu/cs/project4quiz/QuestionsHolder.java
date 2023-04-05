package uga.edu.cs.project4quiz;

import android.content.Context;

import java.util.ArrayList;

public class QuestionsHolder {
    ArrayList<Question> QuestionList = new ArrayList<>();

    Context context;


    public QuestionsHolder(Context c) {
        this.context = c;
    }
    public void generateQuestions() {
        Question q;
        for (int i = 0; i < 6; i++) {
            q = new Question(this.context);
            QuestionList.add(q);
        }
    }
}
