package output;

import java.util.function.Function;

public class Result {
    private MethodType methodType;
    private double[] coefficients; // 近似函数的系数
    private Function<Double, Double> function; // 近似函数
    private double standardDeviation; // 标准偏差
    private double correlation; //线性函数的皮尔逊相关系数
    private String functionResult;

    // 构造函数
    public Result(MethodType methodType, double[] coefficients, Function<Double, Double> function, double standardDeviation) {
        this.methodType = methodType;
        this.coefficients = coefficients;
        this.function = function;
        this.standardDeviation = standardDeviation;
        functionResult = coefficientsResult();
    }
    public Result(MethodType methodType, double[] coefficients, Function<Double, Double> function, double standardDeviation, double correlation) {
        this(methodType, coefficients, function, standardDeviation);
        this.correlation = correlation;
    }

    // 近似函数的输出
    private String coefficientsResult() {
        String re;
        switch (methodType){
            case LINEAR:
                re = String.format("%fx +%f", coefficients[0], coefficients[1]);
                break;
            case QUADRATIC:
                re = String.format("%fx^2 + %fx + %f", coefficients[2], coefficients[1], coefficients[0]);
                break;
            case CUBIC:
                re = String.format("%fx^3 + %fx^2 + %fx + %f", coefficients[3], coefficients[2], coefficients[1], coefficients[0]);
                break;
            case EXPONENTIAL:
                re = String.format("%fe^(%fx)", coefficients[1], coefficients[0]);
                break;
            case LOGARITHMIC:
                re = String.format("%flnx + %f", coefficients[0], coefficients[1]);
                break;
            case POWER:
                re = String.format("%fx^(%f)", coefficients[1], coefficients[0]);
                break;
            default:
                re = null;
                break;
        }
        return re;
    }

    public MethodType getMethodType() {
        return methodType;
    }

    public double[] getCoefficients() {
        return coefficients;
    }

    public Function<Double, Double> getFunction() {
        return function;
    }

    public double getStandardDeviation() {
        return standardDeviation;
    }

    @Override
    public String toString() {
        return "\n"+ "\033[34m"+"Result:" + "\033[0m" +
                "\nmethodType: " + methodType +
                "\nfunctionResult: " + functionResult  +
                "\nstandardDeviation: " + standardDeviation +
                (methodType == methodType.LINEAR ? "\ncorrelation: " + correlation + "\n" : "");
    }
}
