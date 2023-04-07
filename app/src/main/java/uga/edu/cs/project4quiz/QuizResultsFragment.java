package uga.edu.cs.project4quiz;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

public class QuizResultsFragment extends Fragment {
    private Button returnHome;

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
        returnHome = view.findViewById(R.id.button4);

        returnHome.setOnClickListener( new HomeButtonClickListener() );
    }

    private class HomeButtonClickListener implements View.OnClickListener {
        @Override
        public void onClick(View view) {
            // create the new fragment
            StartQuizFragment startQuizFragment = new StartQuizFragment();

            // transition to the new fragment
            FragmentTransaction fragmentTransaction = getParentFragmentManager().beginTransaction();
            fragmentTransaction.replace(R.id.fragmentContainerView, startQuizFragment);

            // add it to the back stack to enable the back button
            fragmentTransaction.addToBackStack("start quiz fragment");

            // commit the transaction, i.e. make the changes
            fragmentTransaction.commit();
        }
    }
}