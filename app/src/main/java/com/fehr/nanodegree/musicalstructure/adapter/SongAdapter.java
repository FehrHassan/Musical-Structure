package com.fehr.nanodegree.musicalstructure.adapter;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.fehr.nanodegree.musicalstructure.R;
import com.fehr.nanodegree.musicalstructure.utils.Song;

import java.util.ArrayList;

/**
 * Created by Fehr on 13-Aug-17.
 */

public class SongAdapter extends ArrayAdapter<Song> {
    public SongAdapter(Context context, ArrayList<Song> song) {
        super(context, 0, song);
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = ((Activity) getContext()).getLayoutInflater().inflate(R.layout.song_item, parent, false);
        }

        ImageView photoImageView = (ImageView) convertView.findViewById(R.id.image_song);
        TextView messageTextView = (TextView) convertView.findViewById(R.id.song_name);

        Song currentSong = getItem(position);

        Glide.with(photoImageView.getContext())
                .load(currentSong.getPhotoUrl())
                .into(photoImageView);
        if (currentSong.getIsToString())
            messageTextView.setText(currentSong.toString());
        else
            messageTextView.setText(currentSong.getName());

        return convertView;
    }
}
