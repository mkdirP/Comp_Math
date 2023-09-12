import javax.swing.plaf.synth.SynthEditorPaneUI;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        System.out.println("[Решение системы линейных алгебраических уравнений СЛАУ]" );

        double[][] matrix = mode(chooseMode());
        System.out.println("\n"+"\033[34m"+"Value of matrix:" + "\033[0m");
        PrintData.printMatrix(matrix);

//        矩阵的行列式
        System.out.println("\033[34m"+"Value of the determinant of the matrix is:" + "\033[0m");
        System.out.println(CalculateData.calcDeterminant(matrix));

//        三角矩阵
        System.out.println("\n"+"\033[34m"+"Value of the triangular matrix is:" + "\033[0m");
        PrintData.printMatrix(CalculateData.calcTriangularMatrix(matrix));

//        线性方程组的未知数
        System.out.println("\033[34m" + "Vector of unknowns(x)" + "\033[0m");
        PrintData.printArray(CalculateData.calcUnknows(matrix), "x");

//        线性方程组差异
        System.out.println("\033[34m" + "Vector of residual(r)" + "\033[0m");
        PrintData.printArray(CalculateData.calcResidual(matrix), "r");
    }

    public static int chooseMode(){
//        double[][] matrix = null;
        while (true){
            try {
                Scanner s = new Scanner(System.in);
                System.out.print("Please select the mode:\n" +
                        "1 - Input matrix\n" +
                        "2 - Matrix from file\n" +
                        "0 - Exit the program\n> ");
                int mode = s.nextInt();
                if (mode != 1 && mode != 2 && mode != 0){
                    System.out.println("\033[31m" + "Please input from (1/2/0)! " + "\033[0m");
                }
                return mode;
            }catch (InputMismatchException ime){
                System.out.println("\033[31m" + "Please input a number!" + "\033[0m");
            }
        }
    }

    public static double[][] mode(int mode) {
        double[][] matrix = null;
        if (mode == 0) {
            System.out.println("\033[31m" + "Exit the program" + "\033[0m");
            System.exit(0);
        } else if (mode == 1) {
//          System.out.println("mode 1");
            matrix = ReceivedData.recMatrixInput();
        } else if (mode == 2) {
//          System.out.println("mode 2");
            matrix = ReceivedData.recMatrixFile();
        }
        return matrix;
    }
}