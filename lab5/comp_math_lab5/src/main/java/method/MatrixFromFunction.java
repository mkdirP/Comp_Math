package method;

import java.util.InputMismatchException;
import java.util.Scanner;

public class MatrixFromFunction {
    // 选择函数并生成数据
    public static double[][] matrix(){
        double a = 0;
        double b = 0;

        while (true) {
            try {
                // 选择函数
                System.out.print("1 - sin(x)\n" +
                        "2 - x^5 + 2x^4 + x^3 - 6x\n> ");
                Scanner s = new Scanner(System.in);
                int numOfFunction = 0;
                while (true){
                    numOfFunction = s.nextInt();
                    if (numOfFunction != 1 && numOfFunction != 2){
                        System.out.println("\033[31m" + "Please choose function from 1/2!" + "\033[0m");
                    } else {
                        break;
                    }
                }

                // 输入区间
                System.out.print("Please enter the a (space) b\n> ");
                Scanner s1 = new Scanner(System.in);
                String str = s1.nextLine();
                String[] vals = str.trim().split("\\s+");
                a = Double.parseDouble(vals[0]);
                b = Double.parseDouble(vals[1]);

                // 输入区间上的点的数量
                System.out.print("Please enter the number of points on the interval\n> ");
                int numOfPoints = s.nextInt();

                // 在区间上生成等距的n个点
                double[][] matrix = new double[numOfPoints][2];
                for (int i = 0; i < numOfPoints; i++){
                    matrix[i][0] = a + i*((b-a)/(numOfPoints-1));
                    if (numOfFunction == 1){
                        matrix[i][1] = Math.sin(matrix[i][0]);
                    } else matrix[i][1] = Math.pow(matrix[i][0], 5) + 2 * Math.pow(matrix[i][0],4) + Math.pow(matrix[i][0], 3) - 6 * matrix[i][0];
                }

                return matrix;

            } catch (InputMismatchException ime) {
                System.out.println("\033[31m" + "Please enter a number!" + "\033[0m");
            }

        }

    }
}
