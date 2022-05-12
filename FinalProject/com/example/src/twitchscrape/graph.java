package twitchscrape;

import java.util.*;
import java.util.Map.Entry;

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

    public String printGraph(Map<String, List<String[]>> streamers) {
        StringBuilder builder = new StringBuilder();

        for(T vertex : graph.keySet()) {          
            
            // for (Entry<String, List<String[]>> i : streamers.entrySet()) {
            //     for (String[] row : i.getValue()) {
            //         System.out.println("Streamer: " + i.getKey() +
            //                   ", Info: " + i.getValue());
            //     }            
            // }
    
            try {
                Set<String> keys = streamers.keySet();
                // System.out.println(keys);
                if (keys.contains(vertex)) {
                    continue;
                }
                else
                    builder.append("\n" + vertex.toString() + " --> ");
                    for(T node: graph.get(vertex)) {
                        builder.append(node.toString() + " ");
                    }
                    builder.append("\n");    
                    
                
            }   
            catch (Exception e) {
                System.out.println("\nException caught");
            }
            
        }
        return builder.toString();
    }

    private void addVertex(T vertex) {
        graph.put(vertex, new LinkedList<T>());
    }
}