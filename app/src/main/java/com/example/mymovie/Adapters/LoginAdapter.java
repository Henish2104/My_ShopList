package com.example.mymovie.Adapters;

import android.content.Context;
import android.widget.Switch;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.example.mymovie.LoginTabFragment;
import com.example.mymovie.SignupTabFragment;

public class LoginAdapter extends FragmentPagerAdapter {
    private Context context;
    int totalTabs;
    public LoginAdapter(@NonNull FragmentManager fm, Context context, int totalTabs) {
        super(fm);
        this.context=context;
        this.totalTabs=totalTabs;
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0:

                return new LoginTabFragment(context);
            case 1:

                return new SignupTabFragment(context);
            default:
                return null;

        }
        }

    @Override
    public int getCount() {
        return totalTabs;
    }
}
