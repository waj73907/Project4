package uga.edu.cs.project4quiz;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Lifecycle;
import androidx.viewpager2.adapter.FragmentStateAdapter;

public class ViewPagerAdapter extends FragmentStateAdapter {
    public ViewPagerAdapter(
            FragmentManager fragmentManager,
            Lifecycle lifecycle ) {
        super( fragmentManager, lifecycle );
    }

    @Override
    public Fragment createFragment(int position) {
        if (position == 6)
            return QuizResultsFragment.newInstance();
        else
            return QuizQuestionFragment.newInstance(position);
    }

    @Override
    public int getItemCount() {
        return 7;
    }
}
