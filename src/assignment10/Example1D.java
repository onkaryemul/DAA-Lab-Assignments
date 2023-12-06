package assignment10;

/*
    1. Implement the following using Backtracking:
        d) Graph coloring Problem
*/

public class Example1D {
    static final int V = 4;

    void printSolution(int color[]) {
        System.out.println("Solution Exists: Following are the assigned colors");
        for (int i = 0; i < V; i++) {
            System.out.print(" " + color[i] + " ");
        }
        System.out.println();
    }

    boolean isSafe(int v, boolean graph[][], int color[], int c) {
        for (int i = 0; i < V; i++) {
            if (graph[v][i] && c == color[i]) {
                return false;
            }
        }

        return true;
    }

    boolean graphColoringUtil(boolean graph[][], int m, int color[], int v) {
        if (v == V) {
            return true;
        }

        for (int c = 1; c <= m; c++) {
            if (isSafe(v, graph, color, c)) {
                color[v] = c;
                if (graphColoringUtil(graph, m, color, v + 1)) {
                    return true;
                }
                color[v] = 0;
            }
        }

        return false;
    }

    boolean graphColoring(boolean graph[][], int m) {
        int color[] = new int[V];
        for (int i = 0; i < V; i++) {
            color[i] = 0;
        }

        if (!graphColoringUtil(graph, m, color, 0)) {
            System.out.println("Solution does not exist");
            return false;
        }

        printSolution(color);
        return true;
    }

    public static void main(String args[]) {
        Example1D mColoring = new Example1D();

        /* Create following graph and test whether it is 3 colorable
        (3)---(2)
        |    / |
        |   /  |
        | /    |
        (0)---(1)
        */
        boolean graph[][] = {
                { false, true, true, true },
                { true, false, true, false },
                { true, true, false, true },
                { true, false, true, false },
        };

        int m = 3; // Number of colors
        mColoring.graphColoring(graph, m);
    }
}
