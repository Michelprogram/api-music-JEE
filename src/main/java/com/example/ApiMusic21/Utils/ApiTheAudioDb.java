package com.example.ApiMusic21.Utils;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;
import java.util.*;

import com.example.ApiMusic21.dao.AlbumDao;
import com.example.ApiMusic21.dao.ArtistDao;
import com.example.ApiMusic21.dao.GenreDao;
import com.example.ApiMusic21.model.Album;
import com.example.ApiMusic21.model.Artist;
import com.example.ApiMusic21.model.Genre;
import org.json.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;


@Component
public class ApiTheAudioDb implements ApplicationRunner {

    @Autowired
    private GenreDao genreDao;

    @Autowired
    private ArtistDao artistDao;

    @Autowired
    private AlbumDao albumDao;

    private float randomPrice(){
        double MAX = 50.0, MIN = 10.0;
        double result = (Math.random() * (MAX - MIN) + 1) + MIN;

        String tempo = String.format("%.2f",result).replace(",",".");

        return Float.parseFloat(tempo);
    }

    private int randomTracks(){
        int MAX = 15, MIN = 5;
        return (int) ((Math.random() * (MAX - MIN) + 1) + MIN);

    }

    public void request() throws InterruptedException, IOException {

        HashMap<Artist,Integer> artists = new HashMap();

        artists.put(new Artist(1, "The Weeknd","canada"), 112024);
        artists.put(new Artist(2, "Lil uzi vert","etat-unis"), 147724);
        artists.put(new Artist(3, "stromae","belgique"), 117012);


        for(Map.Entry<Artist, Integer> entry: artists.entrySet()){

            Artist artist = entry.getKey();
            Integer id = entry.getValue();

            String url = "https://theaudiodb.com/api/v1/json/2/album.php?i="+id;

            HttpClient client = HttpClient.newHttpClient();

            HttpRequest request = HttpRequest.newBuilder(URI.create(url))
                    .header("accept", "application/json")
                    .build();

            HttpResponse<String> response = client.send(request, BodyHandlers.ofString());

            JSONObject jsonObject = new JSONObject(response.body());

            JSONArray albums = jsonObject.getJSONArray("album");

            artistDao.save(artist);

            for (int i = 0; i < albums.length(); i++) {
                JSONObject album = albums.getJSONObject(i);

                Genre genre = null;

                String genreName = album.isNull("strGenre") ? "" : album.getString("strGenre");
                String albumName = album.getString("strAlbum");

                List<Genre> genres = genreDao.findByNameContaining(genreName);

                if ( !genreName.isEmpty()){
                    if (genres.size() < 1){

                        genre = new Genre(genreName);

                        genreDao.save(genre);

                    } else{
                        genre = genres.get(0);
                    }
                }

                if(albumDao.findByNameContaining(albumName).size() < 1){

                    Integer tracks = randomTracks();
                    float price = randomPrice();

                    String years = album.getString("intYearReleased");

                    Set<Genre> set = new LinkedHashSet<>();

                    set.add(genre);

                    Album album1 = new Album(i, tracks, albumName, years, price, set, artist);

                    albumDao.save(album1);

                }

            }

        }

    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        request();
    }
}
