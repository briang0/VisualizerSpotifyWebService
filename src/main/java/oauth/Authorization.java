package oauth;

import com.wrapper.spotify.SpotifyApi;
import com.wrapper.spotify.SpotifyHttpManager;
import com.wrapper.spotify.exceptions.SpotifyWebApiException;
import com.wrapper.spotify.model_objects.credentials.AuthorizationCodeCredentials;
import com.wrapper.spotify.requests.authorization.authorization_code.AuthorizationCodeRequest;
import domain.AuthorizationCode;
import org.springframework.web.client.RestTemplate;

import java.io.File;
import java.io.IOException;
import java.net.URI;

import static io.UserPrompt.openUserPrompt;
import static java.lang.Thread.sleep;

public class Authorization {
    private static final String CLIENT_ID = "";
    private static final String CLIENT_SECRET = "";
    private static final URI REDIRECT_URI = SpotifyHttpManager.makeUri("http://localhost:8888/callback");
    private static AuthorizationCode authCode = null;
    private static String authString = null;
    private static long expirationTime;

    private static final SpotifyApi spotifyApi = new SpotifyApi.Builder()
            .setClientId(CLIENT_ID)
            .setClientSecret(CLIENT_SECRET)
            .setRedirectUri(REDIRECT_URI)
            .build();

    private static AuthorizationCodeRequest authorizationCodeRequest;


    public static void authorize() {
        try {
            getCode();
        } catch (InterruptedException | IOException e) {
            e.printStackTrace();
        }
        authorizationCode_Sync();
    }

    public static SpotifyApi getSpotifyApi() {
        return spotifyApi;
    }

    public static AuthorizationCode getAuthorizationCode() {
        return authCode;
    }

    public static void authorizationCode_Sync() {
        try {
            final AuthorizationCodeCredentials authorizationCodeCredentials = authorizationCodeRequest.execute();

            spotifyApi.setAccessToken(authorizationCodeCredentials.getAccessToken());
            spotifyApi.setRefreshToken(authorizationCodeCredentials.getRefreshToken());

            int expirationTimeSeconds = authorizationCodeCredentials.getExpiresIn();
            expirationTime = expirationTimeSeconds * 1000 + System.currentTimeMillis();
            authCode = new AuthorizationCode(authString, expirationTime);
            System.out.println("Expires in: " + authorizationCodeCredentials.getExpiresIn());
        } catch (IOException | SpotifyWebApiException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private static void getCode() throws InterruptedException, IOException {
        openUserPrompt();
        boolean ready = checkIfAuthorizationIsReady();
        while (!ready) {
            System.out.println("Waiting for prompt... ");
            ready = checkIfAuthorizationIsReady();
            sleep(250);
        }
        System.out.println("Response Recieved");
        String uri = "http://localhost:8888/getAuthCode";
        RestTemplate restTemplate = new RestTemplate();
        authString = restTemplate.getForObject(uri, String.class);
        authorizationCodeRequest = spotifyApi.authorizationCode(authString).build();
        System.out.println("Auth code: " + authString);
    }

    private static Boolean checkIfAuthorizationIsReady() {
        String uri = "http://localhost:8888/checkStatus";
        RestTemplate restTemplate = new RestTemplate();
        return restTemplate.getForObject(uri, Boolean.class);
    }

}
