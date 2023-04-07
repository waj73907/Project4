package uga.edu.cs.project4quiz;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.viewpager2.widget.ViewPager2;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import java.io.IOException;
import java.util.concurrent.ExecutionException;

public class StartQuizFragment extends Fragment {

    private Button startQuiz;
    private Button viewPastQuizzes;

    public StartQuizFragment() {
        // Required empty public constructor
    }

    public static StartQuizFragment newInstance() {
        StartQuizFragment fragment = new StartQuizFragment();
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_start_quiz, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        startQuiz = getView().findViewById(R.id.button);
        viewPastQuizzes = getView().findViewById(R.id.button2);

        startQuiz.setOnClickListener( new StartQuizButtonClickListener() );
        viewPastQuizzes.setOnClickListener( new PastQuizzesButtonClickListener() );

    }

    private class StartQuizButtonClickListener implements View.OnClickListener {
        @Override
        public void onClick(View view) {
            // create the new fragment
            ViewPagerFragment viewPagerFragment = new ViewPagerFragment();

            // transition to the new fragment
            FragmentTransaction fragmentTransaction = getParentFragmentManager().beginTransaction();
            fragmentTransaction.replace(R.id.fragmentContainerView, viewPagerFragment);

            // add it to the back stack to enable the back button
            fragmentTransaction.addToBackStack("start quiz fragment");

            // commit the transaction, i.e. make the changes
            fragmentTransaction.commit();


        }
    }

    private class PastQuizzesButtonClickListener implements View.OnClickListener {
        @Override
        public void onClick(View view) {
            // create the new fragment
            PastQuizFragment viewPastQuizzes  = new PastQuizFragment();

            // transition to the new fragment
            FragmentTransaction fragmentTransaction = getParentFragmentManager().beginTransaction();
            fragmentTransaction.replace(R.id.fragmentContainerView, viewPastQuizzes);

            // add it to the back stack to enable the back button
            fragmentTransaction.addToBackStack("start quiz fragment");

            // commit the transaction, i.e. make the changes
            fragmentTransaction.commit();
        }
    }


}