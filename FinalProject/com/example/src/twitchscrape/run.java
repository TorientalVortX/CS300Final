package twitchscrape;
import java.io.*;
import java.awt.GraphicsEnvironment;
import java.net.URISyntaxException;

public class run {
    public static void main(String[] args) throws IOException, InterruptedException, URISyntaxException{
        scrape twitch = new scrape();
        twitch.scrape("https://twitchtracker.com/channels/live");
        
    }
}
