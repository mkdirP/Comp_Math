package method;

import output.MethodType;
import output.Result;

import java.util.Arrays;
import java.util.function.Function;

import static method.CommonSolve.standardDevSolve;
import static method.Linear.linear;

public class Logarithmic {
    public static Result logarithmic(double[][] matrix){
        double[][] modifiedFunctionTable = Arrays.stream(matrix).map(double[]::clone).toArray(double[][]::new);
        for (double[] xy: modifiedFunctionTable) {
            xy[0] = Math.log(xy[0]);
        }
        Result linear = linear(modifiedFunctionTable);
        double[] coefficients = linear.getCoefficients();
        Function<Double, Double> f = coefficientsLog(coefficients);

        Result resultLogarithmic = new Result(MethodType.LOGARITHMIC, coefficients, f, standardDevSolve(matrix, f));
        return resultLogarithmic;
    }

    private static Function<Double, Double> coefficientsLog(double[] coefficients) {
        return x -> coefficients[1] * Math.log(x) + coefficients[0];
    }
}
