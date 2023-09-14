package methodData;

import org.example.PrintData;

import static graph.PutInGraph.*;
import static org.example.PrintData.*;

public class PartDiv {

    private final double[] equation;
    private final int n;
    private final double accuracy;
    boolean transcendent;
    public PartDiv(double[] equation, double accuracy, boolean transcendent) {
        this.equation = equation;
        this.accuracy = accuracy;
        n = equation.length;
        this.transcendent = transcendent;
    }

    public void initSolve(double a, double b) {
        for (double temp = a - 2.0; temp < b + 2.0; temp += 0.1) {
            points.put(temp, transcendent ? solve(equation, temp) : solvePoint(equation, temp));
        }

        String header = "   k|\t\t a| \t    b|\t\t   x| \t   f(a)| \t  f(b)|\t\t f(x)| \t   |a-b||\n" +
                        "+---+------+--------+--------+----------+---------+---------+-----------+\n";
        PrintData.str.append(header);
        System.out.println(header);

        double[] arr = new double[7];
        arr[0] = a;
        arr[1] = b;
        repeat(arr);
        int cnt = 0;
        while (arr[6] > accuracy && Math.abs(arr[5]) > accuracy) {
            printRes(arr, cnt);
            cnt++;
            if (arr[3] * arr[5] > 0) {
                arr[0] = arr[2];
            } else {
                arr[1] = arr[2];
            }
            repeat(arr);
        }
        printRes(arr, cnt);
        String results = "Count iterations: " + cnt + "\n" +
                        "Final x = " + arr[2];
        System.out.println(results);
        str.append(results);
    }

//    执行任务
    private void repeat(double[] arr) {
        arr[2] = (arr[0] + arr[1]) / 2;
        arr[3] = execute(arr[0]);
        arr[4] = execute(arr[1]);
        arr[5] = execute(arr[2]);
        arr[6] = Math.abs(arr[0] - arr[1]);
    }
    public double execute(double x) {
        double ans = 0;
        for (int i = 0; i < n; i++) {
            ans += transcendent ? solve(equation, x): solvePoint(equation, x);
        }
        return ans;
    }
}
