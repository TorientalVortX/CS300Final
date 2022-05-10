package twitchscrape;

import java.util.*;
import org.jsoup.Jsoup;
import org.jsoup.nodes.*;

public class scrape {
    public void scrape(String a){
        HashMap<String, String>
        hm = new HashMap<String, String>();
        final String url = a;
    
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
               
                hm.put(name, game);
                
            }
        }
    }
    catch (Exception ex) {
        ex.printStackTrace();
    }
    System.out.println(hm);

    graph<Integer> graphObject = new graph<>();
    Map<String, String> hmSorted = sortByValue(hm);
    for (Map.Entry<String, String> i : hmSorted.entrySet()) {
        graphObject.addEdge(i.getValue(), i.getKey(),  true);
        System.out.println("Streamer: " + i.getKey() +
                      ", Game: " + i.getValue());
                     
    }
    System.out.println("\nGraph:\n"
        + graphObject.printGraph());
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
