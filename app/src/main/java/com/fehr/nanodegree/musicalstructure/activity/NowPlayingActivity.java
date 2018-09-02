package com.fehr.nanodegree.musicalstructure.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.fehr.nanodegree.musicalstructure.R;
import com.fehr.nanodegree.musicalstructure.utils.Song;

public class NowPlayingActivity extends AppCompatActivity {

    public static final int ARTIST_FRAG = 1;
    public static final int ALBUM_FRAG = 2;
    public static final int SONG_FRAG = 3;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate (savedInstanceState);
        setContentView (R.layout.activity_nowplaying);

        Toolbar toolbar = (Toolbar) findViewById (R.id.toolbar);
        setSupportActionBar (toolbar);
        getSupportActionBar ().setDisplayHomeAsUpEnabled (true);
        getSupportActionBar ().setTitle (getString (R.string.activity_nowplaying_title));

        TextView textView = (TextView) findViewById (R.id.textview_nowplaying);
        Song song = getIntent ().getParcelableExtra ("song");
        textView.setText (song.getName ());

        ImageView imageView = (ImageView) findViewById (R.id.image_view_for_now_playing);
        Glide.with (imageView.getContext ())
                .load (song.getPhotoUrl ())
                .into (imageView);

        Button playlist = (Button) findViewById (R.id.playlist_button);
        playlist.setOnClickListener (new View.OnClickListener () {
            @Override
            public void onClick(View view) {
                Intent i = new Intent (NowPlayingActivity.this, MainActivity.class);
                startActivity (i);
            }
        });

        Button artist = (Button) findViewById (R.id.artist_button);
        artist.setOnClickListener (new View.OnClickListener () {
            @Override
            public void onClick(View view) {
                Intent i = new Intent (NowPlayingActivity.this, MainActivity.class);
                i.putExtra ("fragment", ARTIST_FRAG);
                startActivity (i);
            }
        });

        Button album = (Button) findViewById (R.id.album_button);
        album.setOnClickListener (new View.OnClickListener () {
            @Override
            public void onClick(View view) {
                Intent i = new Intent (NowPlayingActivity.this, MainActivity.class);
                i.putExtra ("fragment", ALBUM_FRAG);
                startActivity (i);
            }
        });

        Button songButton = (Button) findViewById (R.id.song_button);
        songButton.setOnClickListener (new View.OnClickListener () {
            @Override
            public void onClick(View view) {
                Intent i = new Intent (NowPlayingActivity.this, MainActivity.class);
                i.putExtra ("fragment", SONG_FRAG);
                startActivity (i);
            }
        });

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId () == android.R.id.home) {
            finish ();
        }
        return super.onOptionsItemSelected (item);
    }
}
