package uga.edu.cs.project4quiz;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;


public class PastQuizFragment extends Fragment {
    LinearLayout layout;
    public PastQuizFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_past_quiz, container, false);

        layout = view.findViewById(R.id.linear);

        ArrayList<Quiz> quizzes = ((MainActivity) getActivity()).getAllQuizzes();
        for (int i = 0; i < quizzes.size(); i++) {
            TextView textView = new TextView(this.getContext());
            textView.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
            textView.setText(quizzes.get(i).toString());
            layout.addView(textView);
        }

        return view;
    }
}