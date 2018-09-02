package com.fehr.nanodegree.musicalstructure.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import com.fehr.nanodegree.musicalstructure.R;
import com.fehr.nanodegree.musicalstructure.activity.PaymentActivity;
import com.fehr.nanodegree.musicalstructure.activity.ViewerActivity;
import com.fehr.nanodegree.musicalstructure.utils.Album;
import com.fehr.nanodegree.musicalstructure.utils.Playlist;
import com.fehr.nanodegree.musicalstructure.utils.Song;

import java.util.ArrayList;
import java.util.Arrays;

public class PlaylistFragment extends Fragment {
    ArrayAdapter<Playlist> mAdapter;
//    String[] items = {"Playlist 1", "Playlist 2"};

    public static PlaylistFragment newInstance() {
        PlaylistFragment fragment = new PlaylistFragment();
        return fragment;
    }

    @Override
    public View onCreateView(final LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View playlist = inflater.inflate(R.layout.frag_playlist, container, false);

        Button buyAlbum = (Button) playlist.findViewById (R.id.buyAlbums_playlists);
        buyAlbum.setOnClickListener (new View.OnClickListener () {
            @Override
            public void onClick(View view) {
                startActivity (new Intent (getActivity (), PaymentActivity.class));
            }
        });

        ListView listView = (ListView) playlist.findViewById(R.id.listView);

        final ArrayList<Playlist> items = new ArrayList<Playlist>();
        Song song1 = new Song(getString(R.string.song_1), getString(R.string.artist_1), getString(R.string.album_1), getString(R.string.song1_url), false);
        Song song2 = new Song(getString(R.string.song_2), getString(R.string.artist_2), getString(R.string.album_2), getString(R.string.song2_url), false);
        Song song3 = new Song(getString(R.string.song_3), getString(R.string.artist_1), getString(R.string.album_1), getString(R.string.song3_url), false);
        Song song4 = new Song(getString(R.string.song_4), getString(R.string.artist_2), getString(R.string.album_2), getString(R.string.song4_url), false);
        Album album1 = new Album(getString(R.string.album_1), new Song[]{song1, song3});
        Album album2 = new Album(getString(R.string.album_2), new Song[]{song2, song4});
        Playlist playlist1 = new Playlist(getString(R.string.playlist_1), new Song[]{song3, song4});
        Playlist playlist2 = new Playlist(getString(R.string.playlist_2), new Song[]{song2, song4});
        song2.setPlaylist(new Playlist[]{playlist2});
        song3.setPlaylist(new Playlist[]{playlist1});
        song4.setPlaylist(new Playlist[]{playlist1, playlist2});
        items.add(playlist1);
        items.add(playlist2);

        mAdapter = new ArrayAdapter<Playlist>(getActivity(), android.R.layout.simple_list_item_1, items);
        listView.setAdapter(mAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                                            @Override
                                            public void onItemClick(AdapterView<?> adapter, View view, int position, long arg) {
                                                Intent intent = new Intent(getContext(), ViewerActivity.class);
                                                intent.putExtra("playlist", items.get(position).getPlaylistName());
                                                ArrayList<Song> songs = new ArrayList<Song>(Arrays.asList(items.get(position).getSongs()));
                                                intent.putParcelableArrayListExtra("song", songs);
                                                intent.putExtra("fragment", "Playlist");
                                                startActivity(intent);
                                            }
                                        }
        );
        return playlist;
    }
}
