package twitchscrape;

import java.util.*;

public class graph<T> {
    private Map<T, List<T>> graph = new HashMap<>();

    public void addEdge(String source, T destination, boolean biDirectional) {
        if (!graph.containsKey(source)) {
            addVertex((T) source);
        }

        if (!graph.containsKey(destination)) {
            addVertex(destination);
        }

        graph.get(source).add(destination);
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

    public String printGraph() {
        StringBuilder builder = new StringBuilder();

        for(T vertex : graph.keySet()) {
            builder.append("\n" + vertex.toString() + "->");
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
