package assignment7;

// Kruskal's Algorithm

import java.util.*;

class Example1A {
    static class Node {
        int parent;
        int rank;
    }

    static class Edge {
        int src;
        int dst;
        int wt;
    }

    static ArrayList<Node> dsuf = new ArrayList<>();
    static ArrayList<Edge> mst = new ArrayList<>();

    public static int find(int v) {
        if (dsuf.get(v).parent == -1)
            return v;
        dsuf.get(v).parent = find(dsuf.get(v).parent); // Path Compression
        return dsuf.get(v).parent;
    }

    public static void unionOp(int fromP, int toP) {
        // UNION by RANK
        if (dsuf.get(fromP).rank > dsuf.get(toP).rank) // fromP has a higher rank
            dsuf.get(toP).parent = fromP;
        else if (dsuf.get(fromP).rank < dsuf.get(toP).rank) // toP has a higher rank
            dsuf.get(fromP).parent = toP;
        else {
            // Both have the same rank, and so anyone can be made the parent
            dsuf.get(fromP).parent = toP;
            dsuf.get(toP).rank += 1; // Increase the rank of the parent
        }
    }

    public static boolean comparator(Edge a, Edge b) {
        return a.wt < b.wt;
    }

    public static void kruskals(ArrayList<Edge> edgeList, int V, int E) {
        Collections.sort(edgeList, (a, b) -> Integer.compare(a.wt, b.wt));
        int i = 0, j = 0;
        while (i < V - 1 && j < E) {
            int fromP = find(edgeList.get(j).src);
            int toP = find(edgeList.get(j).dst);
            if (fromP == toP) {
                j++;
                continue;
            }
            // Union operation
            unionOp(fromP, toP);
            mst.add(edgeList.get(j));
            i++;
        }
    }

    public static void printMST(ArrayList<Edge> mst) {
        System.out.println("MST formed is");
        for (Edge p : mst) {
            System.out.println("src: " + p.src + " dst: " + p.dst + " wt: " + p.wt);
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int V = sc.nextInt();
        int E = sc.nextInt();
        for (int i = 0; i < V; i++) {
            Node node = new Node();
            node.parent = -1;
            node.rank = 0;
            dsuf.add(node);
        }
        ArrayList<Edge> edgeList = new ArrayList<>();
        for (int i = 0; i < E; i++) {
            int from = sc.nextInt();
            int to = sc.nextInt();
            int wt = sc.nextInt();
            Edge temp = new Edge();
            temp.src = from;
            temp.dst = to;
            temp.wt = wt;
            edgeList.add(temp);
        }
        kruskals(edgeList, V, E);
        printMST(mst);
        sc.close();
    }
}

