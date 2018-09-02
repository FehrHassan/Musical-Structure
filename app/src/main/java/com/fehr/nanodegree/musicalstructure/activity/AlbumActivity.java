package com.fehr.nanodegree.musicalstructure.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.fehr.nanodegree.musicalstructure.R;
import com.fehr.nanodegree.musicalstructure.adapter.SongAdapter;
import com.fehr.nanodegree.musicalstructure.utils.Album;
import com.fehr.nanodegree.musicalstructure.utils.Song;

import java.util.ArrayList;
import java.util.Arrays;


public class AlbumActivity extends AppCompatActivity {
    SongAdapter mAdapter;
    ArrayList<Song> items;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate (savedInstanceState);
        setContentView (R.layout.activity_album);
        Toolbar toolbar = (Toolbar) findViewById (R.id.toolbar);
        setSupportActionBar (toolbar);
        getSupportActionBar ().setDisplayHomeAsUpEnabled (true);


        String albumName = getIntent ().getStringExtra ("album");
        getSupportActionBar ().setTitle (albumName);

        Song song1 = new Song (getString (R.string.song_1), getString (R.string.artist_1), getString (R.string.album_1), getString (R.string.song1_url), true);
        Song song2 = new Song (getString (R.string.song_2), getString (R.string.artist_2), getString (R.string.album_2), getString (R.string.song2_url), true);
        Song song3 = new Song (getString (R.string.song_3), getString (R.string.artist_1), getString (R.string.album_1), getString (R.string.song3_url), true);
        Song song4 = new Song (getString (R.string.song_4), getString (R.string.artist_2), getString (R.string.album_2), getString (R.string.song4_url), true);
        Album album1 = new Album (getString (R.string.album_1), new Song[]{song1, song3});
        Album album2 = new Album (getString (R.string.album_2), new Song[]{song2, song4});

        if (albumName.equals (album1.getAlbumName ())) {
            items = new ArrayList<> (Arrays.asList (album1.getSongs ()));
        }
        if (albumName.equals (album2.getAlbumName ())) {
            items = new ArrayList<> (Arrays.asList (album2.getSongs ()));
        }
        Log.v ("items", items.get (0).toString ());
        final ArrayList<Song> songs = items;
        Log.v ("songs", items.get (1).toString ());
        ListView listView = (ListView) findViewById (R.id.list_View_album);
        mAdapter = new SongAdapter (AlbumActivity.this, songs);
        listView.setAdapter (mAdapter);
        listView.setOnItemClickListener (new AdapterView.OnItemClickListener () {
                                             @Override
                                             public void onItemClick(AdapterView<?> adapter, View view, int position, long arg) {
                                                 Intent intent = new Intent (AlbumActivity.this, NowPlayingActivity.class);
                                                 intent.putExtra ("song", songs.get (position));
                                                 startActivity (intent);
                                             }
                                         }
        );
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId () == android.R.id.home) {
            finish ();
        }
        return super.onOptionsItemSelected (item);
    }
}
