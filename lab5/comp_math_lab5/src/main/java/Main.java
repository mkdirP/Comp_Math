import graph.FunctionPlotter;
import method.*;
import org.jfree.ui.RefineryUtilities;
import output.Output;
import output.Result;

import java.util.*;

public class Main {
    public static void main(String[] args) {
        System.out.println("[Интерполяция функции]");
        // 先接收数据再选择方法
        double[][] matrix = chooseReceive();
        // 输出数据
        System.out.println("\033[34m"+"Values of pairs:" + "\033[0m");
        Output.printMatrix(matrix);

        System.out.println("\033[34m"+"Raw data tables:"  + "\033[0m");
        Output.printMatrix(Table.table(matrix));

        double x = Receive.recX(matrix[0][0], matrix[matrix.length-1][0]);
        System.out.println(chooseMethod(matrix, x));

        // 反转数组 竖向变为横向
        double[][] reMatrix = new double[2][matrix.length];
        for (int i = 0; i < matrix.length; i++){
            reMatrix[0][i] = matrix[i][0];
            reMatrix[1][i] = matrix[i][1];
        }
        FunctionPlotter plotter = new FunctionPlotter("Function Plotter", reMatrix);
        plotter.pack();
        RefineryUtilities.centerFrameOnScreen(plotter);
        plotter.setVisible(true);

    }

    // 选择接收数据的模式
    public static double[][] chooseReceive() {
        double[][] matrix = null;
        while (true){
            try {
                Scanner s = new Scanner(System.in);
                System.out.print("Please select the mode:\n" +
                        "1 - Input matrix\n" +
                        "2 - Matrix from file\n" +
                        "3 - Choose function\n" +
                        "0 - Exit the program\n> ");
                int mode = s.nextInt();
                if (mode != 1 && mode != 2 && mode != 3 && mode != 0){
                    System.out.println("\033[31m" + "Please input from (1/2/3/0)!\n " + "\033[0m");
                    continue;
                }else if (mode == 0) {
                    System.out.println("\033[31m" + "Exit the program" + "\033[0m");
                    System.exit(0);
                } else if (mode == 1) {
                    matrix = Receive.recMatrixInput();
                } else if (mode == 2) {
                    matrix = Receive.recMatrixFile();
                } else if (mode == 3) {
                    matrix = MatrixFromFunction.matrix();
                }
            }catch (InputMismatchException ime){
                System.out.println("\033[31m" + "Please input a number!\n" + "\033[0m");
                continue;
            }
            return matrix;
        }
    }

    // 选择方法
    public static Result chooseMethod(double[][] matrix, double x){
        while (true){
            try {
                Scanner s = new Scanner(System.in);
                System.out.print("Please select the method:\n" +
                        "1 - Lagrange\n" +
                        "2 - Newton_Frond\n" +
                        "3 - Newton_Backend\n" +
                        "0 - Exit the program\n> ");
                int mode = s.nextInt();
                if (mode != 1 && mode != 2 && mode != 3 && mode != 0){
                    System.out.println("\033[31m" + "Please input from (1/2/0)!\n " + "\033[0m");
                } else if (mode == 0) {
                    System.out.println("\033[31m" + "Exit the program" + "\033[0m");
                    System.exit(0);
                } else if (mode == 1) {
                    Result lagrange = Lagrange.lagrange(matrix, x);
                    return lagrange;
                } else if (mode == 2) {
                    Result newton1 = Newton.newton_1(matrix, x);
                    return newton1;
                } else if (mode == 3) {
                    Result newton2 = Newton.newton_2(matrix, x);
                    return newton2;
                }
            }catch (InputMismatchException ime){
                System.out.println("\033[31m" + "Please input a number!\n" + "\033[0m");
            }
        }
    }


}