package uga.edu.cs.project4quiz;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.viewpager2.widget.ViewPager2;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import java.util.Random;

public class QuizQuestionFragment extends Fragment {
    private TextView questionNumber;
    private TextView countryQuestion;
    private TextView swipeInstruction;
    private Button nextQuestion;
    private RadioGroup radio;
    private int counter = 1;
    private int questionNum; // displays question number every time a new fragment is shown

    public QuizQuestionFragment() {
        // Required empty public constructor
    }

    public static QuizQuestionFragment newInstance(int questionNum) {
        QuizQuestionFragment fragment = new QuizQuestionFragment();
        Bundle args = new Bundle();
        args.putInt( "questionNum", questionNum );
        fragment.setArguments( args );
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_view_pager, container, false );
        if( getArguments() != null ) {
            questionNum = getArguments().getInt( "questionNum" );
        }
        ViewPager2 pager = view.findViewById(R.id.viewPager);
        ViewPagerAdapter avpAdapter = new ViewPagerAdapter(
                getParentFragmentManager(), getLifecycle());
        pager.setOrientation(ViewPager2.ORIENTATION_HORIZONTAL);
        pager.setAdapter(avpAdapter);

        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_quiz_question, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        questionNumber = view.findViewById(R.id.textView7);
        countryQuestion = view.findViewById(R.id.textView4);
        swipeInstruction = view.findViewById(R.id.textView3);
        radio = view.findViewById(R.id.radioGroup);

        Question question = new Question(getContext());

        // Sets the question number
        questionNumber.setText("Question " + (questionNum+1) + "/6");

        // Sets the question for each fragment
        countryQuestion.setText(question.questionToString());

        // Sets the answers for each fragment
        String strMain = question.answersToString();
        String[] arrSplit = strMain.split(",");
        for (int oCounter=0; oCounter < radio.getChildCount(); oCounter++) {
            ((RadioButton) radio.getChildAt(oCounter)).setText(arrSplit[oCounter]);
        }

        // Tells the user how to go to the next question
        if (questionNum != 5)
            swipeInstruction.setText("Swipe left to proceed to Question " + (questionNum+2));
        else
            swipeInstruction.setText("Click button below to see quiz results!");

        if (questionNum == 5) { // last question fragment

        }
//        nextQuestion.setOnClickListener( new NextQuestionButtonClickListener() );
    }

    private class NextQuestionButtonClickListener implements View.OnClickListener {
        @Override
        public void onClick(View view) {
            counter++;

            if (counter <= 6) {
                questionNumber.setText("Question " + counter);
            }
            if (counter == 6) {
                nextQuestion.setText("Calculate Results");
            }
            if (counter > 6) {
                // create a new fragment to go to the quiz results screen
                QuizResultsFragment quizResultsFragment = new QuizResultsFragment();

                // transition to the new fragment
                FragmentTransaction fragmentTransaction = getParentFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.fragmentContainerView, quizResultsFragment);

                // add it to the back stack to enable the back button
                fragmentTransaction.addToBackStack("start quiz fragment");

                // commit the transaction, i.e. make the changes
                fragmentTransaction.commit();
            }
        }
    }
}