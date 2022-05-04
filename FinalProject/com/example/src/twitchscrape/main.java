package twitchscrape;

import java.util.*;
import org.jsoup.Jsoup;
import org.jsoup.nodes.*;

public class main {
    public static void main(String[] args) {
        final String url = 
                "http://twitchtracker.com/channels/ranking";
        
        Hashtable<Integer, String>
            hm = new Hashtable<Integer, String>();
        
        try {
            final Document document = Jsoup.connect(url).get();

            // System.out.println(document.outerHtml());
            
            for (Element row : document.select(
                "table.table-condensed.text-center tr")) {
                if (row.select("td.color-viewers").text().equals("")) {
                    continue;
                }
                else {
                    final String tempViews = 
                           row.select("td.color-viewers").text();
                    final Integer views = Integer.parseInt(tempViews);

                    final String name = 
                           row.select("td a").text();
                   
                    hm.put(views, name);
                }
            }
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }

        // TreeMap for Sorting
        TreeMap<Integer, String> tm
            = new TreeMap<Integer, String>(hm);

        // Keyset for Datatable
        Set<Integer> keys = tm.keySet();
        Iterator<Integer> itr = keys.iterator();
  
        // Traversing TreeMap using iterator
        while (itr.hasNext()) {
            Integer i = itr.next();
            System.out.println("Avg Viewers: " + i + " - Streamer: " + tm.get(i));
        }

        // System.out.println(hm);
        
    }
}
