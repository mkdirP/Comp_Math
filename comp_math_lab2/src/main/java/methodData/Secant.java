package methodData;

import org.example.PrintData;

import static graph.PutInGraph.*;
import static org.example.PrintData.printRes;

public class Secant {
    static double[] exp_local;
    static double subScr;
    static double superScr;
    static double eps_local;
    static double Xk0;
    static double Xk;
    static double Xk1;
    static boolean transcendentLocal = false;

//    检查启动间隔
    public static boolean check() {
        if (transcendentLocal) {
            if (solve(exp_local, subScr) * solve(exp_local, superScr) < 0) return true;
        } else {
            if (solvePoint(exp_local, subScr) * solvePoint(exp_local, superScr) < 0) return true;
        }
        return false;
    }
//    超越的 sin
    public static void initSolve(double[] expr, double a, double b, double eps, boolean transcendent) {
        exp_local = expr;
        subScr = a;
        superScr = b;
        eps_local = eps;
        transcendentLocal = transcendent;

        for (double temp = a - 2.0; temp < b + 2.0; temp += 0.1) {
            points.put(temp, transcendent ? solve(exp_local, temp) : solvePoint(exp_local, temp));
        }
        if (!check()) {
            System.out.println("Incorrect input");
            System.exit(10);
        }
        Xk0 = subScr;
        Xk = superScr;

        String header = "   k|\t   X_k-1| \t  X_k|    X_k+1|  f(X_k+1)| |X_k+1 - X_k| |\n" + "+---+----------+--------+---------+----------+---------------+\n";
        PrintData.str.append(header);
        System.out.println(header);

        int counter = 1;
        double[] temp = new double[5];
        while (Math.abs(Xk1 - Xk) > eps_local) {
            if (!transcendentLocal) {
                Xk1 = Xk - ((Xk - Xk0) / (solvePoint(exp_local, Xk) - solvePoint(exp_local, Xk0))) * solvePoint(exp_local, Xk);
                temp[3] = solvePoint(exp_local, Xk1);
            } else {
                Xk1 = Xk - ((Xk - Xk0) / (solve(exp_local, Xk) - solve(exp_local, Xk0))) * solve(exp_local, Xk);
                temp[3] = solve(exp_local, Xk1);
            }
            temp[0] = Xk0;
            temp[1] = Xk;
            temp[2] = Xk1;
            temp[4] = Math.abs(Xk1 - Xk);
            printRes(temp, counter);
            Xk0 = Xk;
            Xk = Xk1;
            counter++;
        }
        System.out.print("\n");

        String result = "Point = " + Xk1 + "\nValue = " + (transcendentLocal ? solve(exp_local, Xk1) : solvePoint(exp_local, Xk1));
        System.out.println(result);
        PrintData.str.append(result);
    }

}
