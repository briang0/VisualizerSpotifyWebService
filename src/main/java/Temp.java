import client.Client;
import com.wrapper.spotify.SpotifyApi;
import com.wrapper.spotify.exceptions.SpotifyWebApiException;
import com.wrapper.spotify.model_objects.miscellaneous.AudioAnalysis;
import domain.AuthorizationCode;
import domain.Song;
import oauth.Authorization;

import java.io.IOException;

import static oauth.Authorization.*;

public class Temp {

    public static void main(String[] args) {
        authorize();
        AuthorizationCode authorizationCode = getAuthorizationCode();
        String songId = "0kQjr0GMEuELsHpmIC6Wr4";
        authorizationCode_Sync();
        SpotifyApi spotifyApi = getSpotifyApi();
        Client client = new Client(spotifyApi);
        Song data = null;
        try {
            data = client.getAudioData(songId);
            System.out.println(data.getSegementsJson());
        } catch (IOException e) {
            e.printStackTrace();
        } catch (SpotifyWebApiException e) {
            e.printStackTrace();
        }
    }

}
