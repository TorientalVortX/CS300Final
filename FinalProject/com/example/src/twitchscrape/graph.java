package twitchscrape;

import java.util.*;
import org.jsoup.Jsoup;
import org.jsoup.nodes.*;

public class graph<T> {
    private Map<T, List<T>> graph = new HashMap<>();

    public void addEdge(String source, String destination, boolean biDirectional) {
        if (!graph.containsKey(source)) {
            addVertex((T) source);
        }

        if (!graph.containsKey(destination)) {
            addVertex((T) destination);
        }

        graph.get(source).add((T) destination);
        if(biDirectional == true) {
            graph.get(destination).add((T) source);
        }
    }

    public void hasVertex(T vertex) {
        if(graph.containsKey(vertex)) {
            System.out.println("The Graph contains " + vertex + " as a vertex");
        }else {
            System.out.println("The Graph does not contain " + vertex + " as a vertex");
        }
    }

    public void hasEdge(T source, T destination) {
        if(graph.get(source).contains(destination)) {
            System.out.println("The Graph has an edge between " + source + " and " + destination);
        }else {
            System.out.println("The Graph has no edge between " + source + " and " + destination);
        }
    }
    
    public String printGraph(String a) {
        StringBuilder builder = new StringBuilder();
        final String url = a;
        final Document document = Jsoup.connect(url).get();
        for (Element row : document.select(
            "table.table-condensed.text-center tr")) {
            if (row.select("td div.meta").first().text().equals("")) {
                continue;
            }
            else {
                final String tempViews = 
                       row.select("td span.viewers-value").text();
            }
        for(T vertex : graph.keySet()) {
            builder.append("\n" + vertex.toString() + " is now streaming with" + Arrays.toString(row) + "viewers");
            for(T node: graph.get(vertex)) {
                builder.append(node.toString() + " ");
            }
            builder.append("\n");
        }
        return builder.toString();
    }
    
        private void addVertex(T vertex) {
            graph.put(vertex, new LinkedList<T>());
        }
    }
}
