package de.ludetis.firstapp;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import static de.ludetis.firstapp.DetailActivity.KEY_POSITION;

public class ViewPagerFragmentAdapter extends FragmentStateAdapter {

    private final int cardIndex;

    public ViewPagerFragmentAdapter(@NonNull FragmentActivity fragmentActivity, int cardIndex) {
        super(fragmentActivity);
        this.cardIndex = cardIndex;
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        Fragment fragment = null;
        switch (position) {
            case 0 :
                fragment = new DetailFragment();
                break;
            case 1 :
                fragment = new EditablDetailFragment();
                break;
        }
        Bundle bundle = new Bundle();
        bundle.putInt(KEY_POSITION, cardIndex);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public int getItemCount() {
        return 2;
    }
}
