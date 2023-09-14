package output;

import java.util.function.Function;

public class Result {
    private MethodType methodType;
    private double value;

    // 构造函数
    public Result(MethodType methodType, double value) {
        this.methodType = methodType;
        this.value = value;
    }

    public MethodType getMethodType() {
        return methodType;
    }

    public double getValue() {
        return value;
    }

    @Override
    public String toString() {
        return "\033[34m"+"Result:" + "\033[0m" +
                "\nmethodType: " + methodType +
                "\ny(X): " + value + "\n";
    }
}
