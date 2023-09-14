package org.example;

public class MatrixData {
    public static int SIZE = -1;
    static boolean isPerestanovka = false;

    static double[][] matrixA;
    static double[][] matrixB;
    static double[][] matrixX1;
    static double[][] matrixX2;

    static double epsilon;
    static int M;

    public static void setMatrix(double[][] allMatrix) {
        matrixA = new double[SIZE][SIZE];
        matrixB = new double[SIZE][1];

        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                matrixA[i][j] = allMatrix[i][j];
            }
            matrixB[i][0] = allMatrix[i][SIZE];
        }
        preReversingLines();
    }

    public static void reverseLines(int i, int j) {
        double[] arr = matrixA[i];
        matrixA[i] = matrixA[j];
        matrixA[j] = arr;

        double arrB = matrixB[i][0];
        matrixB[i][0] = matrixB[j][0];
        matrixB[j][0] = arrB;
    }

    public static int searchLineWithNorma(int numberX) {
        int currentX = numberX;
        double currentCoeff;
        double sumOfOther;

        for (int i = numberX; i < SIZE; i++) {
            currentCoeff = Math.abs(matrixA[i][currentX]);
            sumOfOther = 0;
            for (int j = 0; j < SIZE; j++) {
                sumOfOther += Math.abs(matrixA[i][j]);
            }
            sumOfOther -= currentCoeff;
            if (currentCoeff >= sumOfOther) {
                if (currentCoeff > sumOfOther) {
                    isPerestanovka = true;
                }
                reverseLines(numberX, i);
                return i;
            }
        }
        System.out.println("It isn't possible to rearrange the lines so that the diagonal predominance is fulfilledе");
        System.exit(4);
        return -1;
    }

    public static void preReversingLines() {
        for (int i = 0; i < SIZE; i++) {
            searchLineWithNorma(i);
        }
        if (isPerestanovka) {
        } else {
            System.out.println("The condition that the iterations in the replacement process must converge is not met");
            System.exit(5);
        }
    }

    public static void initMatrixX12() {
        matrixX2 = new double[SIZE][1];
        matrixX1 = new double[SIZE][1];
        for (int i = 0; i < SIZE; i++) {
            matrixX2[i][0] = matrixB[i][0] / matrixA[i][i];
        }
    }

    public static void iteration() {
        for (int i = 0; i < SIZE; i++) {
            matrixX1[i][0] = matrixX2[i][0];
        }

        double sumOther;
        for (int i = 0; i < SIZE; i++) {
            sumOther = 0;

            for (int j = 0; j < SIZE; j++) {
                if (j < i) {
                    sumOther += matrixA[i][j] * matrixX2[j][0] / matrixA[i][i];
                } else if (j == i) {
                } else {
                    sumOther += matrixA[i][j] * matrixX1[j][0] / matrixA[i][i];
                }
            }

            matrixX2[i][0] = matrixB[i][0] / matrixA[i][i] - sumOther;
        }
    }

    public static double[] startComputed() {
        int count = 0;
        boolean flag = true;
        for (int i = 0; i < SIZE; i++) {
            if (Math.abs(matrixX2[i][0] - matrixX1[i][0]) > epsilon) {
                flag = false;
            }
        }
        while (true) {
            iteration();
            count++;
            if (flag || count >= M) {
                break;
            }
        }

        System.out.println("\nAfter the next iteration of：");
        System.out.println("deltaX =" + String.format("%1$6.4f", matrixX2[0][0]));
        System.out.println("deltaY =" + String.format("%1$6.4f", matrixX2[1][0]));

        return new double[] {matrixX2[0][0], matrixX2[1][0]};
    }
}
