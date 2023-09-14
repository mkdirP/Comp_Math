package method;

import output.MethodType;
import output.Result;

import java.util.function.Function;

import static method.CommonSolve.*;

public class Quadratic {
    public static Result quadratic(double[][] matrix){
        double x_sum = 0, x2_sum = 0, x3_sum = 0, x4_sum = 0,
                y_sum = 0, xy_sum = 0, x2y_sum = 0;

        for (int i = 0; i < matrix.length; i++) {
            x_sum += matrix[i][0];
            x2_sum += Math.pow(matrix[i][0], 2);
            x3_sum += Math.pow(matrix[i][0], 3);
            x4_sum += Math.pow(matrix[i][0], 4);
            y_sum += matrix[i][1];
            xy_sum += matrix[i][0] * matrix[i][1];
            x2y_sum += Math.pow(matrix[i][0], 2) * matrix[i][1];
        }

        double[][] leftSystem = new double[][] {
                {matrix.length, x_sum, x2_sum},
                {x_sum, x2_sum, x3_sum},
                {x2_sum, x3_sum, x4_sum}
        };
        double[] rightSystem = new double[] {
                y_sum, xy_sum, x2y_sum
        };
        // 计算出的线性方程组结果
        double[] solution = linearSystemSolve(leftSystem, rightSystem);
        // 将得到的结果赋值给近似函数
        Function<Double, Double> function = coefficientsQuadratic(solution);
        // 计算标准偏差
        double deviation = standardDevSolve(matrix, function);

        Result resultQuadratic = new Result(MethodType.QUADRATIC, solution, function, deviation);
        return resultQuadratic;

    }

    private static Function<Double, Double> coefficientsQuadratic(double[] coefs) {
        return (x -> x * x * coefs[2] + x * coefs[1] + coefs[0]);
    }
}
