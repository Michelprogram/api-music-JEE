package com.example.ApiMusic21.Utils;

import com.example.ApiMusic21.dao.AlbumDao;
import com.example.ApiMusic21.dao.ArtistDao;
import com.example.ApiMusic21.dao.GenreDao;
import com.example.ApiMusic21.model.Album;
import com.example.ApiMusic21.model.Artist;
import com.example.ApiMusic21.model.Genre;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class SQLData implements ApplicationRunner {

    @Autowired
    private GenreDao genreDao;

    @Autowired
    private ArtistDao artistDao;

    @Autowired
    private AlbumDao albumDao;

    @Override
    public void run(ApplicationArguments args) throws Exception {

        List<Genre> genres = new ArrayList<>();
        List<Artist> artists = new ArrayList<>();
        List<Album> albums = new ArrayList<>();

        genres.add(new Genre(1, "rap us"));
        genres.add(new Genre(2, "mumble rap"));
        genres.add( new Genre(3, "synthwave"));
        genres.add(new Genre(4, "house music"));

        artists.add(new Artist(1,"xxxtentacion", "etats-unis"));
        artists.add(new Artist(2,"stromae", "france"));
        artists.add(new Artist(3,"polog", "etats-unis"));

        Set set = new LinkedHashSet();

        set.add(genres.get(0));

        albums.add(new Album(1,10,"?","2018", (float) 21.21,set,artists.get(0)));

        set.remove(genres.get(0));

        set.add(genres.get(3));

        albums.add(new Album(2,8,"racine carrée","2013", (float) 12.21,set,artists.get(1)));


        for(Genre genre: genres){
            genreDao.save(genre);
        }

        for(Artist artist: artists){
            artistDao.save(artist);
        }

        for(Album album: albums){
            albumDao.save(album);
        }


    }
}

/*


*/
