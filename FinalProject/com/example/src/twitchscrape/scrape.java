package twitchscrape;

import java.util.*;
import org.jsoup.Jsoup;
import org.jsoup.nodes.*;

public class scrape {
    public void scrape(String a){
        HashMap<String, String>
        hm = new HashMap<String, String>();
        HashMap<String, List<String[]>>
        hm2 = new HashMap<String, List<String[]>>();

        final String url = a;
        List<String[]> streamers = new ArrayList<String[]>();
    
        try {
        final Document document = Jsoup.connect(url).get();

        // System.out.println(document.outerHtml());
        
        for (Element row : document.select(
            "table.table-condensed.text-center tr")) {
            if (row.select("td div.meta").first().text().equals("")) {
                continue;
            }
            else {
                final String tempViews = 
                       row.select("td span.viewers-value").text();
                final Integer views = Integer.parseInt(tempViews);

                final String name = 
                       row.select("td a").text();

                final String game = 
                       row.select("td div.meta").first().text();
                
                streamers.add(new String[] { name, game, tempViews });
                hm.put(name, game);
                hm2.put(name,streamers);
                
            }
        }
    }
    catch (Exception ex) {
        ex.printStackTrace();
    }


    graph<Integer> graphObject = new graph<>();
    Map<String, List<String[]>> hm2ref = hm2;
    Map<String, String> hmSorted = sortByValue(hm);
    for (Map.Entry<String, String> i : hmSorted.entrySet()) {
        graphObject.addEdge(i.getValue(), i.getKey(),  false);
        System.out.println("Streamer: " + i.getKey() +
                      ", Game: " + i.getValue());
                     
    }
    System.out.println("\nGraph:\n"
        + graphObject.printGraph(hm2ref));

    for (String[] row : streamers) {
        System.out.println("Streamer " + row[0] + " | Map Connections: | Game: " + row[1] + " | Current Views: " + row[2]);
    }
}

public static HashMap<String, String> sortByValue(HashMap<String, String> hm)
{
    // Create a list from elements of HashMap
    List<Map.Entry<String, String> > list =
           new LinkedList<Map.Entry<String, String> >(hm.entrySet());

    // Sort the list
    Collections.sort(list, new Comparator<Map.Entry<String, String> >() {
        public int compare(Map.Entry<String, String> o1,
                           Map.Entry<String, String> o2)
        {
            return (o1.getValue()).compareTo(o2.getValue());
        }
    });
     
    // put data from sorted list to hashmap
    HashMap<String, String> temp = new LinkedHashMap<String, String>();
    for (Map.Entry<String, String> aa : list) {
        temp.put(aa.getKey(), aa.getValue());
    }
    return temp;
}



}