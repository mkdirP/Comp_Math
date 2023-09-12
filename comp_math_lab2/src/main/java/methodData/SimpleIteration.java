package methodData;

import graph.PutInGraph;
import org.example.PrintData;

public class SimpleIteration {
    static double[] exp_local;
    static double[] exp_derived;
    static double[] phiX;
    static double subScr;
    static double superScr;
    static double Xk;
    static double Xk1;
    static double eps_local;
    static double lambda;

    static boolean transcendentLocal;
    public static void initSolve(double[] exp, double a, double b, double eps, boolean transcendent) {
        exp_local = exp;
        subScr = a;
        superScr = b;
        eps_local = eps;
        transcendentLocal = transcendent;
        exp_derived = PutInGraph.solveDerivative(exp_local);
        double derivativeA = PutInGraph.solvePoint(exp_derived, subScr);
        double derivativeB = PutInGraph.solvePoint(exp_derived, superScr);

        lambda = -1 / Math.max(Math.abs(derivativeA), Math.abs(derivativeB));
        Xk = Math.abs(derivativeA) > Math.abs(derivativeB) ? subScr : superScr;
        System.out.println("Lambda:" + String.format("%1$6.4f", lambda));

        phiX = new double[exp_local.length];
        for (int i = 0; i < exp_local.length; i++) {
            phiX[i] = exp_local[i] * lambda;
        }
        phiX[1] += 1;

        System.out.print("phi(X) =");
        for (int i = phiX.length - 1; i >= 0; i--) {
            System.out.print(String.format("%1$+6.4f", phiX[i]) + (i != 0 ? "*x" + (i == 1 ? "" : "^" + i) : ""));
        }
        System.out.println("\n");

        for (double temp = a - 2.0; temp < b + 2.0; temp += 0.1) {
            PutInGraph.points.put(temp, transcendent ? PutInGraph.solve(exp_local, temp) : PutInGraph.solvePoint(exp_local, temp));
        }
        solveExp();
        if (Xk1 > superScr || Xk1 < subScr) {
            System.out.println("Method doesn't find solve from inputting interval");
            System.exit(11);
        }

    }

    public static void solveExp() {
        String s = "   k|\t   X_k| \t  X_k+1|  phi(X_k+1)|    f(X_k+1)| |X_k+1 - X_k| |\n" +
                   "+---+--------+----------+------------+------------+---------------+\n";
        PrintData.str.append(s);
        System.out.println(s);

        Xk1 = PutInGraph.solvePoint(phiX, Xk);
        int counter = 0;
        double[] arr = new double[5];
        while (Math.abs(Xk1 - Xk) >= eps_local) {
            repeat(counter, arr);
            counter++;
            Xk = Xk1;
            Xk1 = arr[2];
        }
        repeat(counter, arr);

        String result = "\nResult X = " + String.format("%1$6.6f", Xk1);
        System.out.println(result);
        PrintData.str.append(result);
    }

    private static void repeat(int counter, double[] arr) {
        arr[0] = Xk;
        arr[1] = Xk1;
        arr[2] = PutInGraph.solvePoint(phiX, Xk1);
        arr[3] = PutInGraph.solvePoint(exp_local, Xk1);
        arr[4] = Math.abs(Xk1 - Xk);
        PrintData.printRes(arr, counter);
    }
}
