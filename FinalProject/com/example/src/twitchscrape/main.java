package twitchscrape;

public class main {
    public static void main(String[] args) {
        scrape twitch = new scrape();
        twitch.scrape("http://twitchtracker.com/channels/ranking");
    }
}
