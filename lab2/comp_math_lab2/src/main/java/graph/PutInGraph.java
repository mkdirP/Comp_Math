package graph;

import java.util.HashMap;
import java.util.Map;

public class PutInGraph {
    public static Map<Double, Double> points = new HashMap<>();
    public static double solve(double[] exp, double point) {
        double solve = 0;
        solve += point*point-point-Math.sin(point);

        return solve;
    }

    public static double solvePoint(double[] exp, double point) {
        double solve = 0;

        for (int size = exp.length - 1; size >= 0; size--) {
            solve += exp[size] * Math.pow(point, size);
        }

        return solve;
    }

    public static double[] solveDerivative(double[] exp) {
        int size = exp.length;
        double[] der = new double[size - 1];

        for (int i = 1; i < size; i++) {
            der[i-1] = exp[i] * (i);
        }

        return der;
    }

    public static Map<Double, Double> drawGraph( boolean transcendent, double a, double b, double[] arr){
        Map<Double, Double> firstGraph = new HashMap<>();
        for (double i = a - 2.0; i < b + 2.0; i += 0.1) {
            firstGraph.put(i, transcendent ? solve(arr, i) : solvePoint(arr, i));
        }
        return firstGraph;
    }
}
