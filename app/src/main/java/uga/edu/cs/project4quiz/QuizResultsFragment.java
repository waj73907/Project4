package uga.edu.cs.project4quiz;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

public class QuizResultsFragment extends Fragment {
    TextView score;
    View view;

    public QuizResultsFragment() {
        // Required empty public constructor
    }

    public static QuizResultsFragment newInstance() {
        QuizResultsFragment fragment = new QuizResultsFragment();
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_past_quiz, container, false);

        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_quiz_results, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        score = view.findViewById(R.id.textView5);

        int amountCorrect = ((MainActivity) getActivity()).getCorrectAmount();
        score.setText("Your Score: " + amountCorrect + "/6");
    }

    @Override
    public void onResume() {
        super.onResume();
        ((MainActivity) getActivity()).writeToDatabase();
    }
}