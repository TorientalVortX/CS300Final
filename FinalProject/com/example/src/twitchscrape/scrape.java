package twitchscrape;

import java.util.*;
import org.jsoup.Jsoup;
import org.jsoup.nodes.*;

public class scrape {
    public void scrape(String a){
        HashMap<String, Integer>
        hm = new HashMap<String, Integer>();
        final String url = a;
    
        try {
        final Document document = Jsoup.connect(url).get();

        // System.out.println(document.outerHtml());
        
        for (Element row : document.select(
            "table.table-condensed.text-center tr")) {
            if (row.select("td.viewers-value").text().equals("")) {
                continue;
            }
            else {
                final String tempViews = 
                       row.select("td span.viewers-value").text();
                final Integer views = Integer.parseInt(tempViews);

                final String name = 
                       row.select("td a").text();
               
                hm.put(name, views);
                
            }
        }
    }
    catch (Exception ex) {
        ex.printStackTrace();
    }

    graph<Integer> graphObject = new graph<>();
    Map<String, Integer> hmSorted = sortByValue(hm);
    for (Map.Entry<String, Integer> i : hmSorted.entrySet()) {
        graphObject.addEdge(i.getKey(), i.getValue(),  false);
        System.out.println("Streamer: " + i.getKey() +
                      ", Avg Viewers: " + i.getValue());
                     
    }
    System.out.println("\nGraph:\n"
        + graphObject.printGraph());
}

public static HashMap<String, Integer> sortByValue(HashMap<String, Integer> hm)
{
    // Create a list from elements of HashMap
    List<Map.Entry<String, Integer> > list =
           new LinkedList<Map.Entry<String, Integer> >(hm.entrySet());

    // Sort the list
    Collections.sort(list, new Comparator<Map.Entry<String, Integer> >() {
        public int compare(Map.Entry<String, Integer> o1,
                           Map.Entry<String, Integer> o2)
        {
            return (o1.getValue()).compareTo(o2.getValue());
        }
    });
     
    // put data from sorted list to hashmap
    HashMap<String, Integer> temp = new LinkedHashMap<String, Integer>();
    for (Map.Entry<String, Integer> aa : list) {
        temp.put(aa.getKey(), aa.getValue());
    }
    return temp;
}



}
