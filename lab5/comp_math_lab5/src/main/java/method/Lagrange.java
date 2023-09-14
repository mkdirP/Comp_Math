package method;

import output.MethodType;
import output.Result;

public class Lagrange {
    public static Result lagrange(double[][] matrix, double x){
        double fx = 0;
        double[] l = new double[matrix.length];

        double[] x0 = new double[matrix.length];
        double[] y0 = new double[matrix.length];
        for (int i = 0; i < matrix.length; i++){
            y0[i] = matrix[i][1];
            x0[i] = matrix[i][0];
        }

        for (int i = 0; i < matrix.length; i++){
            l[i] = y0[i];
            for (int j = 0; j < matrix.length; j++){
                if (i != j){
                    l[i] *= (x - x0[j])/(x0[i] - x0[j]);
                }
            }
            fx += l[i];
        }
        return new Result(MethodType.LAGRANGE, fx);
    }
}
