package twitchscrape;

public class main {
    public static void main(String[] args) {
        scrape twitch = new scrape();
        twitch.scrape("https://twitchtracker.com/channels/live");
    }
}
