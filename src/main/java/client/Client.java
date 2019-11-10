package client;

import com.wrapper.spotify.SpotifyApi;
import com.wrapper.spotify.exceptions.SpotifyWebApiException;
import com.wrapper.spotify.model_objects.miscellaneous.CurrentlyPlaying;
import com.wrapper.spotify.model_objects.miscellaneous.CurrentlyPlayingContext;
import com.wrapper.spotify.model_objects.specification.Track;
import com.wrapper.spotify.requests.data.player.GetInformationAboutUsersCurrentPlaybackRequest;
import com.wrapper.spotify.requests.data.player.GetUsersCurrentlyPlayingTrackRequest;
import com.wrapper.spotify.requests.data.tracks.GetAudioAnalysisForTrackRequest;
import domain.Song;

import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedList;


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

    public String getCurrentPlayingSong() throws IOException, SpotifyWebApiException {
        final GetUsersCurrentlyPlayingTrackRequest getUsersCurrentlyPlayingTrackRequest = spotifyApi
                .getUsersCurrentlyPlayingTrack()
                .build();
        final CurrentlyPlaying currentlyPlaying = getUsersCurrentlyPlayingTrackRequest.execute();
        Track track = currentlyPlaying.getItem();
        return track.getName() + " by " + track.getArtists()[0].getName() + " on " + track.getAlbum().getName();
    }

    public LinkedList<HashMap<String, String>> getCurrentlyPlayedSongInformation() throws IOException, SpotifyWebApiException {
        HashMap<String, String> data = new HashMap<>();
        LinkedList<HashMap<String, String>> output = new LinkedList<>();
        final GetUsersCurrentlyPlayingTrackRequest getUsersCurrentlyPlayingTrackRequest = spotifyApi
                .getUsersCurrentlyPlayingTrack()
                .build();
        final GetInformationAboutUsersCurrentPlaybackRequest getInformationAboutUsersCurrentPlaybackRequest =
                spotifyApi.getInformationAboutUsersCurrentPlayback().build();

        final CurrentlyPlaying currentlyPlaying = getUsersCurrentlyPlayingTrackRequest.execute();
        final CurrentlyPlayingContext currentlyPlayingContext = getInformationAboutUsersCurrentPlaybackRequest.execute();
        Track track = currentlyPlaying.getItem();
        String trackId = track.getId();
        String trackName = track.getName();
        String artistName = track.getArtists()[0].getName();
        String albumName = track.getAlbum().getName();
        String duration = "" + track.getDurationMs();
        String progress = "" + currentlyPlaying.getProgress_ms();

        data.put("track_id", trackId);
        data.put("track_name", trackName);
        data.put("artist_name", artistName);
        data.put("album_name", albumName);
        data.put("duration", duration);
        data.put("progress", progress);

        output.add(data);

        return output;
    }

}
