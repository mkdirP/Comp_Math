package method;

import output.MethodType;
import output.Result;

import java.util.Arrays;
import java.util.function.Function;

import static method.CommonSolve.standardDevSolve;
import static method.Linear.linear;

public class Exponential {
    public static Result exponential(double[][] matrix){
        // 共享原始数组内容
        double[][] modifiedMatrix = Arrays.stream(matrix).map(double[]::clone).toArray(double[][]::new);
        for (double[] xy: modifiedMatrix) {
            if (xy[1] <= 0) continue;
            xy[1] = Math.log(xy[1]);
        }
        Result linear = linear(modifiedMatrix);
        double[] coefficients = linear.getCoefficients();
        coefficients[1] = Math.exp(coefficients[1]);
        Function<Double, Double> f = coefficientsExponential(coefficients);

        Result resultExponential = new Result(MethodType.EXPONENTIAL, coefficients, f, standardDevSolve(matrix, f));
        return resultExponential;
    }

    private static Function<Double, Double> coefficientsExponential(double[] coefficients) {
        return x -> coefficients[1] * Math.exp(coefficients[0] * x);
    }

}
