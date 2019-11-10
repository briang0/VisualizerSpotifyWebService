package service;

import client.Client;
import com.wrapper.spotify.SpotifyApi;
import com.wrapper.spotify.exceptions.SpotifyWebApiException;
import domain.AuthorizationCode;
import domain.Song;
import oauth.Authorization;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
public class VisualizerDataController {

    private SpotifyApi spotifyApi = null;
    private AuthorizationCode authCode = null;
    private Client client = null;

    private void checkAuthorization() {
        if ((spotifyApi == null || authCode == null) || authCode.isExpired()) {
            Authorization.authorize();
            authCode = Authorization.getAuthorizationCode();
            spotifyApi = Authorization.getSpotifyApi();
            client = new Client(spotifyApi);
        }
    }

    @RequestMapping(value = "/getSegmentData", method= RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public String getSegmentData(@RequestParam(value="songid") String songId) {
        checkAuthorization();
        Song s;
        try {
            s = client.getAudioData(songId);
            return s.getSegementsJson();
        } catch (IOException | SpotifyWebApiException e) {
            e.printStackTrace();
            return e.getMessage();
        }
    }

    @RequestMapping(value = "/getSectionData", method= RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public String getSectionData(@RequestParam(value="songid") String songId) {
        checkAuthorization();
        Song s;
        try {
            s = client.getAudioData(songId);
            return s.getSectionsJson();
        } catch (IOException | SpotifyWebApiException e) {
            e.printStackTrace();
            return e.getMessage();
        }
    }

}
