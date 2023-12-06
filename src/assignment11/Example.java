package assignment11;

/*
       One day ACSES president decides to organize Research Symposium in WCE. He proposed his idea to all ACSES members, and all agreed on his idea.
Secretary of ACSES has ready bird view plan which contains all Symposium committees in the form of dependencies, with which proper execution of plan can be done.
Table 1.1 shows committees /entities and their dependencies.

   Sr. No         Committee/Entity                               Dependent on

     1.             Review Board                           Registration, Paper, Poster
     2.             Hospitality                                 Finance, Food
     3.            Registration                                   Finance
     4.              Finance                               Approval from TEQIP coordinator
     5.              Session                                    Review Board
     6.         Approval from TEQIP coordinator            Approval from director
     7.               Food                                       Finance

                                   Table 1.1


     Now president has divided members of ACSES into these committees/entities and he wants to tell them in which order to execute each committee/entity's task for smooth execution
Implement an algorithm to Help ACSES president to design all possible linear order execution of committee/entity. Convert this information in the form of Dependence Graph and apply Topological sort with Discovering and Finishing time.
*/

import java.util.*;

class Graph {
    private int V;
    private List<Integer>[] adj;

    public Graph(int V) {
        this.V = V;
        adj = new ArrayList[V];
        for (int i = 0; i < V; i++) {
            adj[i] = new ArrayList<>();
        }
    }

    public void addEdge(int u, int v) {
        adj[u].add(v);
    }

    public void topologicalSort() {
        int[] inDegree = new int[V];

        for (int u = 0; u < V; u++) {
            for (int v : adj[u]) {
                inDegree[v]++;
            }
        }

        Queue<Integer> q = new LinkedList<>();
        for (int i = 0; i < V; i++) {
            if (inDegree[i] == 0) {
                q.add(i);
            }
        }

        int cnt = 0;
        List<Integer> topOrder = new ArrayList<>();

        while (!q.isEmpty()) {
            int u = q.poll();
            topOrder.add(u);

            for (int v : adj[u]) {
                if (--inDegree[v] == 0) {
                    q.add(v);
                }
            }

            cnt++;
        }

        if (cnt != V) {
            System.out.println("There exists a cycle in the graph");
            return;
        }

        for (int i : topOrder) {
            System.out.print(i + " ");
        }
        System.out.println();
    }
}

public class Example {
    public static void main(String[] args) {
        Graph g = new Graph(10);

        // 0
        g.addEdge(0, 2);
        g.addEdge(0, 7);
        g.addEdge(0, 8);

        // 1
        g.addEdge(1, 3);
        g.addEdge(1, 6);

        // 2
        g.addEdge(2, 3);

        // 3
        g.addEdge(3, 5);

        // 4
        g.addEdge(4, 0);

        // 5
        g.addEdge(5, 9);

        // 6
        g.addEdge(6, 3);

        // 7
        // No edge

        // 8
        // No edge

        // 9
        // No edge

        System.out.println("Following is a Topological Sort of");
        g.topologicalSort();
    }

}
