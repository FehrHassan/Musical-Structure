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
import com.fehr.nanodegree.musicalstructure.utils.Song;

import java.util.ArrayList;
import java.util.Arrays;

public class AlbumFragment extends Fragment {
    ArrayAdapter<Album> mAdapter;

    public static AlbumFragment newInstance() {
        AlbumFragment fragment = new AlbumFragment();
        return fragment;
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View album = inflater.inflate(R.layout.frag_album, container, false);

        Button buyAlbum = (Button) album.findViewById (R.id.buyAlbums);
        buyAlbum.setOnClickListener (new View.OnClickListener () {
            @Override
            public void onClick(View view) {
                startActivity (new Intent (getActivity (), PaymentActivity.class));
            }
        });

        final ArrayList<Album> items = new ArrayList<Album>();
        Song song1 = new Song(getString(R.string.song_1), getString(R.string.artist_1), getString(R.string.album_1), getString(R.string.song1_url), false);
        Song song2 = new Song(getString(R.string.song_2), getString(R.string.artist_2), getString(R.string.album_2), getString(R.string.song2_url), false);
        Song song3 = new Song(getString(R.string.song_3), getString(R.string.artist_1), getString(R.string.album_1), getString(R.string.song3_url), false);
        Song song4 = new Song(getString(R.string.song_4), getString(R.string.artist_2), getString(R.string.album_2), getString(R.string.song4_url), false);
        Album album1 = new Album(getString(R.string.album_1), new Song[]{song1, song3});
        Album album2 = new Album(getString(R.string.album_2), new Song[]{song2, song4});
        items.add(album1);
        items.add(album2);

        ListView listView = (ListView) album.findViewById(R.id.listView);

        mAdapter = new ArrayAdapter<Album>(getActivity(), android.R.layout.simple_list_item_1, items);
        listView.setAdapter(mAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                                            @Override
                                            public void onItemClick(AdapterView<?> adapter, View view, int position, long arg) {
                                                Intent intent = new Intent(getActivity(), ViewerActivity.class);
                                                intent.putExtra("album", items.get(position).getAlbumName());
                                                ArrayList<Song> songs = new ArrayList<Song>(Arrays.asList(items.get(position).getSongs()));
                                                intent.putParcelableArrayListExtra("song", songs);
                                                intent.putExtra("fragment", "Album");
                                                startActivity(intent);
                                            }
                                        }
        );
        return album;
    }
}
