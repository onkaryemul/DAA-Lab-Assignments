package assignment8;

/*
   3. Modify the Dijkstra’s algorithm to find shortest path.
*/

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

class Edge {
    int to;
    int weight;

    public Edge(int t, int w) {
        to = t;
        weight = w;
    }
}


public class Example3 {
    public static List<Integer> modifiedDijkstra(List<List<Edge>> graph, int source) {
        int V = graph.size();
        List<Integer> dist = new ArrayList<>(V);
        for (int i = 0; i < V; i++) {
            dist.add(Integer.MAX_VALUE);
        }
        dist.set(source, 0);

        PriorityQueue<Pair> pq = new PriorityQueue<>();
        pq.add(new Pair(0, source));

        while (!pq.isEmpty()) {
            Pair top = pq.poll();
            int u = top.vertex;

            for (Edge edge : graph.get(u)) {
                int v = edge.to;
                int weight = edge.weight;

                if (dist.get(u) != Integer.MAX_VALUE && dist.get(u) + weight < dist.get(v)) {
                    dist.set(v, dist.get(u) + weight);
                    pq.add(new Pair(dist.get(v), v));
                }
            }
        }

        return dist;
    }

    public static void main(String[] args) {
        int V = 5;
        List<List<Edge>> graph = new ArrayList<>(V);
        for (int i = 0; i < V; i++) {
            graph.add(new ArrayList<>());
        }

        graph.get(0).add(new Edge(1, 2));
        graph.get(0).add(new Edge(2, -1));
        graph.get(1).add(new Edge(2, 3));
        graph.get(1).add(new Edge(3, 5));
        graph.get(2).add(new Edge(3, 2));
        graph.get(2).add(new Edge(4, 1));
        graph.get(3).add(new Edge(4, 3));

        int source = 0; // Source vertex

        List<Integer> shortestDistances = modifiedDijkstra(graph, source);

        for (int i = 0; i < V; ++i) {
            System.out.println("Shortest distance from " + source + " to " + i + " is: " + shortestDistances.get(i));
        }
    }
}

class Pair implements Comparable<Pair> {
    int distance;
    int vertex;

    public Pair(int d, int v) {
        distance = d;
        vertex = v;
    }

    public int compareTo(Pair other) {
        return Integer.compare(this.distance, other.distance);
    }
}
