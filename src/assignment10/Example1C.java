package assignment10;

/*
   1. Implement the following using Backtracking:
       c) Hamiltonian cycle
*/

public class Example1C {
    static final int V = 5;

    void printSolution(int path[]) {
        System.out.println("Solution Exists: Following is one Hamiltonian Cycle");
        for (int i = 0; i < V; i++) {
            System.out.print(path[i] + " ");
        }
        System.out.println(path[0]);
    }

    boolean isSafe(int v, boolean graph[][], int path[], int pos) {
        if (!graph[path[pos - 1]][v]) {
            return false;
        }

        for (int i = 0; i < pos; i++) {
            if (path[i] == v) {
                return false;
            }
        }

        return true;
    }

    boolean hamCycleUtil(boolean graph[][], int path[], int pos) {
        if (pos == V) {
            if (graph[path[pos - 1]][path[0]]) {
                return true;
            } else {
                return false;
            }
        }

        for (int v = 1; v < V; v++) {
            if (isSafe(v, graph, path, pos)) {
                path[pos] = v;

                if (hamCycleUtil(graph, path, pos + 1)) {
                    return true;
                }

                path[pos] = -1;
            }
        }

        return false;
    }

    boolean hamCycle(boolean graph[][]) {
        int path[] = new int[V];
        for (int i = 0; i < V; i++) {
            path[i] = -1;
        }

        path[0] = 0;
        if (!hamCycleUtil(graph, path, 1)) {
            System.out.println("Solution does not exist");
            return false;
        }

        printSolution(path);
        return true;
    }

    public static void main(String args[]) {
        Example1C hamCycle = new Example1C();

        boolean graph1[][] = {
                {false, true, false, true, false},
                {true, false, true, true, true},
                {false, true, false, false, true},
                {true, true, false, false, true},
                {false, true, true, true, false}
        };

        hamCycle.hamCycle(graph1);

        boolean graph2[][] = {
                {false, true, false, true, false},
                {true, false, true, true, true},
                {false, true, false, false, true},
                {true, true, false, false, false},
                {false, true, true, false, false}
        };

        hamCycle.hamCycle(graph2);
    }
}
