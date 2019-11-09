package client;

import com.wrapper.spotify.SpotifyApi;
import com.wrapper.spotify.exceptions.SpotifyWebApiException;
import com.wrapper.spotify.model_objects.miscellaneous.AudioAnalysis;
import com.wrapper.spotify.requests.data.tracks.GetAudioAnalysisForTrackRequest;
import domain.Song;

import java.io.IOException;


public class Client {

    private SpotifyApi spotifyApi;

    public Client (SpotifyApi spotifyApi) {
        this.spotifyApi = spotifyApi;
    }

    public Song getAudioData(String id) throws IOException, SpotifyWebApiException {
        final GetAudioAnalysisForTrackRequest audioAnalysisRequest = spotifyApi
                .getAudioAnalysisForTrack(id)
                .build();

        return new Song(audioAnalysisRequest.execute());
    }

}
