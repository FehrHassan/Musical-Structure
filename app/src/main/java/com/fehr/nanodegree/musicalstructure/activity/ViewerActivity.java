package com.fehr.nanodegree.musicalstructure.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.fehr.nanodegree.musicalstructure.R;
import com.fehr.nanodegree.musicalstructure.adapter.SongAdapter;
import com.fehr.nanodegree.musicalstructure.utils.Song;

import java.util.ArrayList;

public class ViewerActivity extends AppCompatActivity {
    ArrayAdapter<String> mAdapter;
    SongAdapter songAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate (savedInstanceState);
        setContentView (R.layout.activity_viewer);

        Toolbar toolbar = (Toolbar) findViewById (R.id.toolbar);
        setSupportActionBar (toolbar);
        getSupportActionBar ().setDisplayHomeAsUpEnabled (true);

        String fragment = getIntent ().getStringExtra ("fragment");

        TextView defineActivity = (TextView) findViewById (R.id.text_view_define_activity);
        defineActivity.setText (fragment + "s Activity");


        if (fragment.equals ("Playlist")) {
            TextView describeTheActivity = (TextView) findViewById (R.id.describe_the_activity);
            describeTheActivity.setText ("This activity shows a list of Songs");
            String playlist = getIntent ().getStringExtra ("playlist");
            getSupportActionBar ().setTitle (playlist);
            final ArrayList<Song> songs = getIntent ().getParcelableArrayListExtra ("song");
            ListView listView = (ListView) findViewById (R.id.list_View);
            songAdapter = new SongAdapter (ViewerActivity.this, songs);
            listView.setAdapter (songAdapter);
            listView.setOnItemClickListener (new AdapterView.OnItemClickListener () {
                                                 @Override
                                                 public void onItemClick(AdapterView<?> adapter, View view, int position, long arg) {
                                                     Intent intent = new Intent (ViewerActivity.this, NowPlayingActivity.class);
                                                     intent.putExtra ("song", songs.get (position));
                                                     startActivity (intent);
                                                 }
                                             }
            );
        }

        if (fragment.equals ("Album")) {
            TextView describeTheActivity = (TextView) findViewById (R.id.describe_the_activity);
            describeTheActivity.setText ("This activity shows a list of Songs");
            String album = getIntent ().getStringExtra ("album");
            getSupportActionBar ().setTitle (album);
            final ArrayList<Song> songs = getIntent ().getParcelableArrayListExtra ("song");
            ListView listView = (ListView) findViewById (R.id.list_View);
            songAdapter = new SongAdapter (ViewerActivity.this, songs);
            listView.setAdapter (songAdapter);
            listView.setOnItemClickListener (new AdapterView.OnItemClickListener () {
                                                 @Override
                                                 public void onItemClick(AdapterView<?> adapter, View view, int position, long arg) {
                                                     Intent intent = new Intent (ViewerActivity.this, NowPlayingActivity.class);
                                                     intent.putExtra ("song", songs.get (position));
                                                     startActivity (intent);
                                                 }
                                             }
            );
        }

        if (fragment.equals ("Artist")) {
            TextView describeTheActivity = (TextView) findViewById (R.id.describe_the_activity);
            describeTheActivity.setText ("This activity shows a list of Albums");
            String artist = getIntent ().getStringExtra ("artist");
            getSupportActionBar ().setTitle (artist);
            final String[] albumNames = getIntent ().getStringArrayExtra ("albums");
            ListView listView = (ListView) findViewById (R.id.list_View);
            mAdapter = new ArrayAdapter<String> (ViewerActivity.this, android.R.layout.simple_list_item_1, albumNames);
            listView.setAdapter (mAdapter);
            listView.setOnItemClickListener (new AdapterView.OnItemClickListener () {
                                                 @Override
                                                 public void onItemClick(AdapterView<?> adapter, View view, int position, long arg) {
                                                     Intent intent = new Intent (ViewerActivity.this, AlbumActivity.class);
                                                     intent.putExtra ("album", albumNames[position]);
                                                     startActivity (intent);
                                                 }
                                             }
            );
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId () == android.R.id.home) {
            finish ();
        }
        return super.onOptionsItemSelected (item);
    }
}
