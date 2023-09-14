package method;

import output.MethodType;
import output.Result;

import java.util.Arrays;
import java.util.function.Function;

import static method.CommonSolve.standardDevSolve;
import static method.Linear.linear;

public class Power {
    public static Result power(double[][] matrix){
        double[][] modifiedMatrix = Arrays.stream(matrix).map(double[]::clone).toArray(double[][]::new);
        for (double[] xy: modifiedMatrix) {
            xy[0] = Math.log(xy[0]);
            xy[1] = Math.log(xy[1]);
        }
        Result linear = linear(modifiedMatrix);
        double[] coefficients = linear.getCoefficients();
        coefficients[1] = Math.exp(coefficients[1]);
        Function<Double, Double> f = coefficientsPower(coefficients);
        return new Result(MethodType.POWER, coefficients, f, standardDevSolve(matrix, f));
    }

    private static Function<Double, Double> coefficientsPower(double[] coefficients) {
        return x -> coefficients[1] * Math.pow(x, coefficients[0]);
    }
}
