package method;

import output.MethodType;
import output.Result;

public class Newton {

    // 第一
    public static Result newton_1(double[][] matrix, double x ){
//        double t = (x - matrix[0][0]) / (matrix[1][0] - matrix[0][0]);
        double[] dividedDifference = new double[matrix.length];
        double fx = 0;

        double[] x0 = new double[matrix.length];
        double[] y0 = new double[matrix.length];
        for (int i = 0; i < matrix.length; i++){
            y0[i] = matrix[i][1];
            x0[i] = matrix[i][0];
        }
        // 初始化差商数组
        for (int i = 0; i < matrix.length; i++) {
            dividedDifference[i] = y0[i];
        }

        // 计算差商
        for (int j = 1; j < matrix.length; j++) {
            for (int i = matrix.length - 1; i >= j; i--) {
                dividedDifference[i] = (dividedDifference[i] - dividedDifference[i - 1]) / (x0[i] - x0[i - j]);
            }
        }

        // 使用牛顿插值公式计算估计值
        for (int i = 0; i < matrix.length; i++) {
            double term = dividedDifference[i];
            for (int j = 0; j < i; j++) {
                term *= (x - x0[j]);
            }
            fx += term;
        }
        return new Result(MethodType.NEWTON_1, fx);
    }

    // 第二
    public static Result newton_2(double[][] matrix, double x ){
        double[][] dividedDifference = new double[matrix.length][matrix.length];
        double fx = 0;

        double[] x0 = new double[matrix.length];
        double[] y0 = new double[matrix.length];
        for (int i = 0; i < matrix.length; i++){
            y0[i] = matrix[i][1];
            x0[i] = matrix[i][0];
        }
        // 初始化差商数组
        for (int i = 0; i < matrix.length; i++) {
            dividedDifference[i][0] = y0[i];
        }

        // 计算差商
        for (int j = 1; j < matrix.length; j++) {
            for (int i = j; i < matrix.length; i++) {
                dividedDifference[i][j] = (dividedDifference[i][j - 1] - dividedDifference[i - 1][j - 1]) /
                        (x0[i] - x0[i - j]);
            }
        }

        // 使用牛顿插值公式计算估计值
        for (int i = 0; i < matrix.length; i++) {
            double term = dividedDifference[i][i];
            for (int j = 0; j < i; j++) {
                term *= (x - x0[j]);
            }
            fx += term;
        }
        return new Result(MethodType.NEWTON_2, fx);

    }

}
