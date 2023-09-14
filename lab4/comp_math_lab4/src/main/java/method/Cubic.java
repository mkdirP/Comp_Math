package method;

import output.MethodType;
import output.Result;

import java.util.function.Function;

import static method.CommonSolve.*;

public class Cubic {
    public static Result cubic(double[][] matrix){
        double x_sum = 0, x2_sum = 0, x3_sum = 0, x4_sum = 0, x5_sum = 0, x6_sum = 0,
                y_sum = 0, xy_sum = 0, x2y_sum = 0, x3y_sum = 0;
        for (int i = 0; i < matrix.length; i++) {
            x_sum += matrix[i][0];
            x2_sum += Math.pow(matrix[i][0], 2);
            x3_sum += Math.pow(matrix[i][0], 3);
            x4_sum += Math.pow(matrix[i][0], 4);
            x5_sum += Math.pow(matrix[i][0], 5);
            x6_sum += Math.pow(matrix[i][0], 6);
            y_sum += matrix[i][1];
            xy_sum += matrix[i][0] * matrix[i][1];
            x2y_sum += Math.pow(matrix[i][0], 2) * matrix[i][1];
            x3y_sum += Math.pow(matrix[i][0], 3) * matrix[i][1];
        }

        double[][] leftSystem = new double[][] {
            {matrix.length, x_sum, x2_sum, x3_sum},
            {x_sum, x2_sum, x3_sum, x4_sum},
            {x2_sum, x3_sum, x4_sum, x5_sum},
            {x3_sum, x4_sum, x5_sum, x6_sum}
        };
        double[] rightSystem = new double[] {
                y_sum, xy_sum, x2y_sum, x3y_sum
        };
        // 计算出的线性方程组结果
        double[] solution = linearSystemSolve(leftSystem, rightSystem);
        // 将得到的结果赋值给近似函数
        Function<Double, Double> function = coefficientsCubic(solution);
        // 计算标准偏差
        double deviation = standardDevSolve(matrix, function);

        Result resultCubic = new Result(MethodType.CUBIC, solution, function, deviation);
        return resultCubic;

    }

    private static Function<Double, Double> coefficientsCubic(double[] coefficients) {
        return x -> x * x * x * coefficients[2] + x * x * coefficients[1] + x * coefficients[0] + coefficients[3];
    }
}
