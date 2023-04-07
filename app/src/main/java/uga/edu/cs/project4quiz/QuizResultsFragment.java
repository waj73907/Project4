package uga.edu.cs.project4quiz;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class QuizResultsFragment extends Fragment {
    TextView score; // grade at the end of quiz

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

    // Only calls this method when it gets to this actual fragment (which is inside the viewpager)
    @Override
    public void onResume() {
        super.onResume();
        ((MainActivity) getActivity()).writeToDatabase();
    }
}