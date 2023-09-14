package method;

import org.apache.commons.math3.linear.Array2DRowRealMatrix;
import org.apache.commons.math3.linear.ArrayRealVector;
import org.apache.commons.math3.linear.DecompositionSolver;
import org.apache.commons.math3.linear.LUDecomposition;

import java.util.function.Function;

public class CommonSolve {
    public static double standardDevSolve(double[][] matrix, Function<Double, Double> function) {
        // 偏差度量
        double s = 0;
        for (double[] xy: matrix) {
            s += Math.pow(xy[1] - function.apply(xy[0]), 2);
        }
        // 标准偏差
        double standardDeviation = Math.sqrt(s/matrix.length);
        return standardDeviation;
    }

    public static double[] linearSystemSolve(double[][] leftSystem, double[] rightSystem) {
        DecompositionSolver solver = new LUDecomposition(new Array2DRowRealMatrix(leftSystem)).getSolver();
        return solver.solve(new ArrayRealVector(rightSystem)).toArray();
    }
}
