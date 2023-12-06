package assignment10;

/*
   1. Implement the following using Backtracking:
       a) 4-Queen’s problem
*/

public class Example1A {
    final int N = 4;
    int[] ld = new int[2 * N - 1];  // Size N for left diagonals
    int[] rd = new int[2 * N - 1];  // Size N for right diagonals
    int[] cl = new int[N];         // Size N for columns


    void printSolution(int board[][])
    {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++)
                System.out.print(" " + (board[i][j] == 1 ? "Q" : ".") + " ");
            System.out.println();
        }
    }

    boolean solveNQUtil(int board[][], int col)
    {
        if (col >= N)
            return true;

        for (int i = 0; i < N; i++) {
            if (ld[i - col + N - 1] != 1 && rd[i + col] != 1 && cl[i] != 1) {
                board[i][col] = 1;
                ld[i - col + N - 1] = rd[i + col] = cl[i] = 1;

                if (solveNQUtil(board, col + 1))
                    return true;

                board[i][col] = 0;
                ld[i - col + N - 1] = rd[i + col] = cl[i] = 0;
            }
        }

        return false;
    }

    boolean solveNQ()
    {
        int board[][] = new int[N][N];

        if (!solveNQUtil(board, 0)) {
            System.out.println("Solution does not exist");
            return false;
        }

        printSolution(board);
        return true;
    }

    public static void main(String[] args)
    {
        Example1A nQueens = new Example1A();
        nQueens.solveNQ();
    }
}
