package org.example;

public class CalcData {

    public static double trapezoidal(double[] equation, double a, double b, double n) {
        double x = a;
        double h = (b - a) / n;
        double ans = 0;
        for (int i = 0; i < n; i++) {
            double y = calcEqs(x, equation);
            ans += y;
            System.out.println("X_" + i + " = " + x + "\tY_" + i + " = " + y);
            x += h;
        }
//        根据公式得出：
        ans = h * (ans + (calcEqs(a, equation) + calcEqs(b, equation)) / 2);
        System.out.println("n = " + n + "\nanswer: " + ans);
        return ans;
    }

    public static double rectangle(double[] equation, double a, double b, double n, int method) {
        double x = a;
        double h = (b - a) / n;
        double ans = 0;
        for (int i = 0; i < n; i++) {
            double flag = method == 1 ? (x + h) : (method == 2 ? x + (h / 2) : x);
            double y = calcEqs(flag, equation);

            ans += y;
            System.out.println("X_" + i + " = " + x + "\tY_" + i + " = " + y);
            x += h;
        }
        ans *= h;
        System.out.println("n = " + n + "\nanswer: " + ans);
        return ans;
    }

//    根据x的值和所选函数计算y的值
    public static double calcEqs(double x, double[] eqs) {
        double ans = 0;
        for (int i = 0; i < eqs.length; i++) {
            ans += eqs[i] * Math.pow(x, i);
        }
        return ans;
    }

}