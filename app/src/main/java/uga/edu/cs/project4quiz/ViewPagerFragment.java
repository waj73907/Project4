package uga.edu.cs.project4quiz;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;
import androidx.viewpager2.widget.ViewPager2;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class ViewPagerFragment extends Fragment {
    ViewPager2 pager;
    ViewPagerAdapter adapter;
    int pos; // current page

    public ViewPagerFragment() {}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return (ViewGroup) inflater.inflate(
                R.layout.fragment_view_pager, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        pager = view.findViewById(R.id.viewPager);

        adapter = new ViewPagerAdapter(
                getChildFragmentManager(), getLifecycle());
        pager.setOrientation(ViewPager2.ORIENTATION_HORIZONTAL);
        pager.setAdapter(adapter);

        if (pager.getCurrentItem() >= 5) {
            Log.d("ViewPagerFragment:", "You are on the 7th page");
            ((MainActivity) getActivity()).addToDatabase(view);
        }
    }

    // Saving/restoring data methods
    @Override
    public void onPause() {
        super.onPause();
        pos = pager.getCurrentItem();
    }

    @Override
    public void onResume() {
        super.onResume();
        pager.setCurrentItem(pos);
    }

}