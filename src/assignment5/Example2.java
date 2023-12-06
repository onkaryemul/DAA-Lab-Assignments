package assignment5;

// Divide and Conquer Strategy

// Strassen's Matrix Multiplication
/*
   2) Implement Strassen's matrix multiplication for 3*3 matrix.
        Do analysis of algorithm with respect to time complexity.
*/

import java.util.Scanner;

public class Example2 {

    public static int[][] strassenAlgorithm(int[][] A, int[][] B, int m, int n, int a, int b) {
        int k = Math.max(Math.max(m, n), Math.max(a, b));
        int s = nextPowerOf2(k);

        int[][] Aa = new int[s][s];
        int[][] Bb = new int[s][s];

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                Aa[i][j] = A[i][j];
            }
        }

        for (int i = 0; i < a; i++) {
            for (int j = 0; j < b; j++) {
                Bb[i][j] = B[i][j];
            }
        }

        int[][] Cc = strassenAlgorithmA(Aa, Bb, s);

        int[][] C = new int[m][b];

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < b; j++) {
                C[i][j] = Cc[i][j];
            }
        }

        return C;
    }

    public static int[][] strassenAlgorithmA(int[][] A, int[][] B, int size) {
        if (size == 1) {
            int[][] C = new int[1][1];
            C[0][0] = A[0][0] * B[0][0];
            return C;
        } else {
            int new_size = size / 2;
            int[][] a11 = new int[new_size][new_size];
            int[][] a12 = new int[new_size][new_size];
            int[][] a21 = new int[new_size][new_size];
            int[][] a22 = new int[new_size][new_size];
            int[][] b11 = new int[new_size][new_size];
            int[][] b12 = new int[new_size][new_size];
            int[][] b21 = new int[new_size][new_size];
            int[][] b22 = new int[new_size][new_size];
            int[][] c11 = new int[new_size][new_size];
            int[][] c12 = new int[new_size][new_size];
            int[][] c21 = new int[new_size][new_size];
            int[][] c22 = new int[new_size][new_size];
            int[][] p1 = new int[new_size][new_size];
            int[][] p2 = new int[new_size][new_size];
            int[][] p3 = new int[new_size][new_size];
            int[][] p4 = new int[new_size][new_size];
            int[][] p5 = new int[new_size][new_size];
            int[][] p6 = new int[new_size][new_size];
            int[][] p7 = new int[new_size][new_size];
            int[][] aResult = new int[new_size][new_size];
            int[][] bResult = new int[new_size][new_size];

            // Dividing the matrices into sub-matrices
            for (int i = 0; i < new_size; i++) {
                for (int j = 0; j < new_size; j++) {
                    a11[i][j] = A[i][j];
                    a12[i][j] = A[i][j + new_size];
                    a21[i][j] = A[i + new_size][j];
                    a22[i][j] = A[i + new_size][j + new_size];
                    b11[i][j] = B[i][j];
                    b12[i][j] = B[i][j + new_size];
                    b21[i][j] = B[i + new_size][j];
                    b22[i][j] = B[i + new_size][j + new_size];
                }
            }

            // Calculating p1 to p7
            add(a11, a22, aResult, new_size);
            add(b11, b22, bResult, new_size);
            int[][] p1Result = strassenAlgorithmA(aResult, bResult, new_size);
            System.out.println("p1:");
            display(p1Result, new_size, new_size);

            add(a21, a22, aResult, new_size);
            int[][] p2Result = strassenAlgorithmA(aResult, b11, new_size);
            System.out.println("p2:");
            display(p2Result, new_size, new_size);

            sub(b12, b22, bResult, new_size);
            int[][] p3Result = strassenAlgorithmA(a11, bResult, new_size);
            System.out.println("p3:");
            display(p3Result, new_size, new_size);

            sub(b21, b11, bResult, new_size);
            int[][] p4Result = strassenAlgorithmA(a22, bResult, new_size);
            System.out.println("p4:");
            display(p4Result, new_size, new_size);

            add(a11, a12, aResult, new_size);
            int[][] p5Result = strassenAlgorithmA(aResult, b22, new_size);
            System.out.println("p5:");
            display(p5Result, new_size, new_size);

            sub(a21, a11, aResult, new_size);
            add(b11, b12, bResult, new_size);
            int[][] p6Result = strassenAlgorithmA(aResult, bResult, new_size);
            System.out.println("p6:");
            display(p6Result, new_size, new_size);

            sub(a12, a22, aResult, new_size);
            add(b21, b22, bResult, new_size);
            int[][] p7Result = strassenAlgorithmA(aResult, bResult, new_size);
            System.out.println("p7:");
            display(p7Result, new_size, new_size);

            // Calculating c11, c12, c21, c22
            add(p3Result, p5Result, c12, new_size);
            add(p2Result, p4Result, c21, new_size);
            add(p1Result, p4Result, aResult, new_size);
            add(aResult, p7Result, bResult, new_size);
            sub(bResult, p5Result, c11, new_size);
            add(p1Result, p3Result, aResult, new_size);
            add(aResult, p6Result, bResult, new_size);
            sub(bResult, p2Result, c22, new_size);

            // Grouping the results into a single matrix
            int[][] C = new int[size][size];
            for (int i = 0; i < new_size; i++) {
                for (int j = 0; j < new_size; j++) {
                    C[i][j] = c11[i][j];
                    C[i][j + new_size] = c12[i][j];
                    C[i + new_size][j] = c21[i][j];
                    C[i + new_size][j + new_size] = c22[i][j];
                }
            }
            return C;
        }
    }

    public static void add(int[][] A, int[][] B, int[][] C, int size) {
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                C[i][j] = A[i][j] + B[i][j];
            }
        }
    }

    public static void sub(int[][] A, int[][] B, int[][] C, int size) {
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                C[i][j] = A[i][j] - B[i][j];
            }
        }
    }

    public static void display(int[][] matrix, int m, int n) {
        System.out.println();
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                System.out.print(matrix[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println();
    }

    public static int nextPowerOf2(int k) {
        return (int) Math.pow(2, Math.ceil(Math.log(k) / Math.log(2)));
    }

    // main method
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter the dimensions of the matrices (m n a b):");
        int m = scanner.nextInt();
        int n = scanner.nextInt();
        int a = scanner.nextInt();
        int b = scanner.nextInt();

        System.out.println("Enter elements for matrix A:");
        int[][] A = new int[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                A[i][j] = scanner.nextInt();
            }
        }

        System.out.println("Enter elements for matrix B:");
        int[][] B = new int[a][b];
        for (int i = 0; i < a; i++) {
            for (int j = 0; j < b; j++) {
                B[i][j] = scanner.nextInt();
            }
        }

        int[][] C = strassenAlgorithm(A, B, m, n, a, b);

        System.out.println("Final result (C):");
        display(C, m, b);
    }

}


/*
    public static int nextPowerOf2(int k) {
        return (int) Math.pow(2, Math.ceil(Math.log(k) / Math.log(2)));
    }

    public static void display(int[][] matrix, int m, int n) {
        System.out.println("Output:");
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                System.out.print(matrix[i][j] + " ");
            }
            System.out.println();
        }
    }

    public static void add(int[][] A, int[][] B, int[][] C, int size) {
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                C[i][j] = A[i][j] + B[i][j];
            }
        }
    }

    public static void sub(int[][] A, int[][] B, int[][] C, int size) {
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                C[i][j] = A[i][j] - B[i][j];
            }
        }
    }

    public static void StrassenAlgorithmA(int[][] A, int[][] B, int[][] C, int size) {
        if (size == 1) {
            C[0][0] = A[0][0] * B[0][0];
        } else {
            int newSize = size / 2;
            int[][] a11 = new int[newSize][newSize];
            int[][] a12 = new int[newSize][newSize];
            int[][] a21 = new int[newSize][newSize];
            int[][] a22 = new int[newSize][newSize];

            int[][] b11 = new int[newSize][newSize];
            int[][] b12 = new int[newSize][newSize];
            int[][] b21 = new int[newSize][newSize];
            int[][] b22 = new int[newSize][newSize];

            int[][] c11 = new int[newSize][newSize];
            int[][] c12 = new int[newSize][newSize];
            int[][] c21 = new int[newSize][newSize];
            int[][] c22 = new int[newSize][newSize];

            int[][] p1 = new int[newSize][newSize];
            int[][] p2 = new int[newSize][newSize];
            int[][] p3 = new int[newSize][newSize];
            int[][] p4 = new int[newSize][newSize];
            int[][] p5 = new int[newSize][newSize];
            int[][] p6 = new int[newSize][newSize];
            int[][] p7 = new int[newSize][newSize];

            int[][] aResult = new int[newSize][newSize];
            int[][] bResult = new int[newSize][newSize];

            for (int i = 0; i < newSize; i++) {
                for (int j = 0; j < newSize; j++) {
                    a11[i][j] = A[i][j];
                    a12[i][j] = A[i][j + newSize];
                    a21[i][j] = A[i + newSize][j];
                    a22[i][j] = A[i + newSize][j + newSize];

                    b11[i][j] = B[i][j];
                    b12[i][j] = B[i][j + newSize];
                    b21[i][j] = B[i + newSize][j];
                    b22[i][j] = B[i + newSize][j + newSize];
                }
            }

            add(a11, a22, aResult, newSize);
            add(b11, b22, bResult, newSize);
            StrassenAlgorithmA(aResult, bResult, p1, newSize);

            add(a21, a22, aResult, newSize);
            StrassenAlgorithmA(aResult, b11, p2, newSize);

            sub(b12, b22, bResult, newSize);
            StrassenAlgorithmA(a11, bResult, p3, newSize);

            sub(b21, b11, bResult, newSize);
            StrassenAlgorithmA(a22, bResult, p4, newSize);

            add(a11, a12, aResult, newSize);
            StrassenAlgorithmA(aResult, b22, p5, newSize);

            sub(a21, a11, aResult, newSize);
            add(b11, b12, bResult, newSize);
            StrassenAlgorithmA(aResult, bResult, p6, newSize);

            sub(a12, a22, aResult, newSize);
            add(b21, b22, bResult, newSize);
            StrassenAlgorithmA(aResult, bResult, p7, newSize);

            add(p3, p5, c12, newSize);
            add(p2, p4, c21, newSize);

            add(p1, p4, aResult, newSize);
            add(aResult, p7, bResult, newSize);
            sub(bResult, p5, c11, newSize);

            add(p1, p3, aResult, newSize);
            add(aResult, p6, bResult, newSize);
            sub(bResult, p2, c22, newSize);

            for (int i = 0; i < newSize; i++) {
                for (int j = 0; j < newSize; j++) {
                    C[i][j] = c11[i][j];
                    C[i][j + newSize] = c12[i][j];
                    C[i + newSize][j] = c21[i][j];
                    C[i + newSize][j + newSize] = c22[i][j];
                }
            }
        }
    }

    public static void StrassenAlgorithm(int[][] A, int[][] B, int m, int n, int a, int b) {
        int k = Math.max(Math.max(m, n), Math.max(a, b));
        int s = nextPowerOf2(k);

        int[][] Aa = new int[s][s];
        int[][] Bb = new int[s][s];
        int[][] Cc = new int[s][s];

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                Aa[i][j] = A[i][j];
            }
        }

        for (int i = 0; i < a; i++) {
            for (int j = 0; j < b; j++) {
                Bb[i][j] = B[i][j];
            }
        }

        StrassenAlgorithmA(Aa, Bb, Cc, s);

        int[][] C = new int[m][b];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < b; j++) {
                C[i][j] = Cc[i][j];
            }
        }

        display(C, m, b);
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int[][] A = new int[3][3];
        int[][] B = new int[3][3];

        System.out.println("Enter elements for matrix A:");
        for (int i = 0; i < 3; ++i) {
            for (int j = 0; j < 3; ++j) {
                A[i][j] = sc.nextInt();
            }
        }

        System.out.println("Enter elements for matrix B:");
        for (int i = 0; i < 3; ++i) {
            for (int j = 0; j < 3; ++j) {
                B[i][j] = sc.nextInt();
            }
        }

        StrassenAlgorithm(A, B, 3, 3, 3, 3);
    }

*/