package com.fehr.nanodegree.musicalstructure.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import com.fehr.nanodegree.musicalstructure.R;
import com.fehr.nanodegree.musicalstructure.activity.NowPlayingActivity;
import com.fehr.nanodegree.musicalstructure.activity.PaymentActivity;
import com.fehr.nanodegree.musicalstructure.adapter.SongAdapter;
import com.fehr.nanodegree.musicalstructure.utils.Album;
import com.fehr.nanodegree.musicalstructure.utils.Playlist;
import com.fehr.nanodegree.musicalstructure.utils.Song;

import java.util.ArrayList;

public class SongFragment extends Fragment {
    SongAdapter mAdapter;

    public static SongFragment newInstance() {
        SongFragment fragment = new SongFragment();
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View song = inflater.inflate(R.layout.frag_song, container, false);

        Button buyAlbum = (Button) song.findViewById (R.id.buyAlbums_songs);
        buyAlbum.setOnClickListener (new View.OnClickListener () {
            @Override
            public void onClick(View view) {
                startActivity (new Intent (getActivity (), PaymentActivity.class));
            }
        });

        final ArrayList<Song> items = new ArrayList<Song>();
        Song song1 = new Song(getString(R.string.song_1), getString(R.string.artist_1), getString(R.string.album_1), getString(R.string.song1_url), true);
        Song song2 = new Song(getString(R.string.song_2), getString(R.string.artist_2), getString(R.string.album_2), getString(R.string.song2_url), true);
        Song song3 = new Song(getString(R.string.song_3), getString(R.string.artist_1), getString(R.string.album_1), getString(R.string.song3_url), true);
        Song song4 = new Song(getString(R.string.song_4), getString(R.string.artist_2), getString(R.string.album_2), getString(R.string.song4_url), true);
        Album album1 = new Album(getString(R.string.album_1), new Song[]{song1, song3});
        Album album2 = new Album(getString(R.string.album_2), new Song[]{song2, song4});
        Playlist playlist1 = new Playlist(getString(R.string.playlist_1), new Song[]{song3, song4});
        Playlist playlist2 = new Playlist(getString(R.string.playlist_2), new Song[]{song2, song4});
        song2.setPlaylist(new Playlist[]{playlist2});
        song3.setPlaylist(new Playlist[]{playlist1});
        song4.setPlaylist(new Playlist[]{playlist1, playlist2});
        items.add(song1);
        items.add(song2);
        items.add(song3);
        items.add(song4);

        ListView listView = (ListView) song.findViewById(R.id.listView);

        mAdapter = new SongAdapter(getActivity(), items);
        listView.setAdapter(mAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                                            @Override
                                            public void onItemClick(AdapterView<?> adapter, View view, int position, long arg) {
                                                Intent intent = new Intent(getActivity(), NowPlayingActivity.class);
                                                intent.putExtra("song", items.get(position));
                                                startActivity(intent);
                                            }
                                        }
        );
        return song;
    }
}