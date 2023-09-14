package methodData;

import org.example.MatrixData;
import org.example.PrintData;

public class NewTon {
    static double deltaX;
    static double deltaY;
    static double Xk;
    static double Xk1;
    static double Yk;
    static double Yk1;
    static double[][] matrix = new double[2][3];
    static double precision;

    public static void initSolve(double precision, double a, double b) {
        double[] arr;
        Xk = a;
        Yk = b;
        NewTon.precision = precision;

        String header = "Starting data:\nX_0 = " + Xk + "\nY_0 = " + Yk;
        PrintData.str.append(header);
        System.out.println(header);

        MatrixData.SIZE = 2;
        int counter = 0;
        while (true) {
            counter++;
            matrix[0][0] = 2*Xk;
            matrix[0][1] = -2;
            matrix[0][2] = 2*Yk - Xk*Xk;
            matrix[1][0] = 3;
            matrix[1][1] = -2*Yk;
            matrix[1][2] = -3 + Yk*Yk - 3*Xk;
            MatrixData.setMatrix(matrix);
            MatrixData.initMatrixX12();
            arr = MatrixData.startComputed();
            deltaX = arr[0];
            deltaY = arr[1];
            Xk1 = Xk + deltaX;
            Yk1 = Yk + deltaY;

            boolean flag = Math.abs(Xk1-Xk) <= precision || Math.abs(Yk1-Yk) <= precision;
            if (flag || counter > 20) {
                break;
            }
            Xk = Xk1;
            Yk = Yk1;
        }
        String result = "\n\nIteration k = " + counter + "\n" +
                "X = " + String.format("%1$6.4f", Xk1) +
                "; \nY = " + String.format("%1$6.4f;", Yk1) + "\n" +
                "Error vector:" + "\n" +
                "for X: " + String.format("%1$6.4f", (Xk1 - Xk)) + "\n" +
                "for Y: " + String.format("%1$6.4f", (Yk1 - Yk));
        PrintData.str.append(result);
        System.out.println(result);
    }

}
