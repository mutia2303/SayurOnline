package com.mobile.mutia.sayuronline.adapter;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.mobile.mutia.sayuronline.fragment.ChatFragment;
import com.mobile.mutia.sayuronline.fragment.PasarFragment;
import com.mobile.mutia.sayuronline.fragment.ProfileFragment;

public class TabsAccessorAdapter extends FragmentPagerAdapter {

    public TabsAccessorAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int i) {

        switch (i) {
            case 0:
                PasarFragment pasarFragment = new PasarFragment();
                return pasarFragment;

            case 1:
                ChatFragment chatFragment = new ChatFragment();
                return chatFragment;

            case 2:
                ProfileFragment profileFragment = new ProfileFragment();
                return profileFragment;

            default:
                return null;
        }

    }

    @Override
    public int getCount() {
        return 3;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        switch (position) {
            case 0:
                return "Pasar";

            case 1:
                return "Chat";

            case 2:
                return "Profile";

            default:
                return null;
        }
    }
}
