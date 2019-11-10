package io;

import java.io.*;

public class UserPrompt {

    public static void openUserPrompt() throws IOException, InterruptedException {
        Runtime runtime = Runtime.getRuntime();
        String url = "https://accounts.spotify.com/authorize?client_id=f1682390d7d34e909f284a11f9e5b3f8&" +
                "response_type=code&show_dialog=false&scope=user-read-private%20user-read-currently-playing%" +
                "20user-read-playback-state&redirect_uri=http://localhost:8888/callback";
        String browser = "C:/Program Files (x86)/Google/Chrome/Application/chrome.exe ";
        Process process = runtime.exec(browser + url);
        process.waitFor();

    }

}
