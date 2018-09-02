package com.fehr.nanodegree.musicalstructure.adapter;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.fehr.nanodegree.musicalstructure.R;
import com.fehr.nanodegree.musicalstructure.fragment.AlbumFragment;
import com.fehr.nanodegree.musicalstructure.fragment.ArtistFragment;
import com.fehr.nanodegree.musicalstructure.fragment.PlaylistFragment;
import com.fehr.nanodegree.musicalstructure.fragment.SongFragment;

public class TabPagerAdapter extends FragmentPagerAdapter {
    final int PAGE_COUNT = 4;
    private Context context;

    public TabPagerAdapter(FragmentManager fm, Context context) {
        super(fm);
        this.context = context;
    }

    @Override
    public Fragment getItem(int i) {
        switch (i) {
            case 0:
                //Fragement for PlaylistFragment Tab
                return new PlaylistFragment().newInstance();
            case 1:
                //Fragment for ArtistFragment Tab
                return new ArtistFragment().newInstance();
            case 2:
                return new AlbumFragment().newInstance();
            case 3:
                return new SongFragment().newInstance();
        }
        return null;

    }


    @Override
    public int getCount() {
        return PAGE_COUNT; //No of Tabs
    }

    @Override
    public CharSequence getPageTitle(int position) {
        // Generate title based on item position
        switch (position) {
            case 0:
                return context.getString(R.string.tab_playlist);
            case 1:
                return context.getString(R.string.tab_artist);
            case 2:
                return context.getString(R.string.tab_album);
            case 3:
                return context.getString(R.string.tab_song);
            default:
                return null;
        }
    }

}
