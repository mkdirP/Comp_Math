package org.example;

import java.util.Scanner;

public class Main {

    private final static double[][] eqs = new double[5][5];

    public static void main(String[] args) {
        try {
            chooseEquation();
            System.out.println("[Численное интегрирование]");

            Scanner s = new Scanner(System.in);

            int option_equ, option_method, option_rectangle = -1;
            System.out.println("1) y = x^2\n" +
                    "2) y = -x^3 - x^2 - 2x + 1\n" +
                    "3) y = -3x^3 - 5x^2 + 4x - 2\n" +
                    "4) y = -x^3 - x^2 + x + 3\n" +
                    "5) y = -2x^3 - 4x^2 + 8x - 4");
            System.out.print("Please select the equation.\n> ");
            option_equ = s.nextInt();

            System.out.print("Please select the method:\n" +
                    "1 - Method Rectangle\n" +
                    "2 - Method Trapezoid\n> ");
            option_method = s.nextInt();
            if (option_method == 1) {
                System.out.print("Choose method from Rectangle:\n" +
                        "1 - Right Rectangle\n" +
                        "2 - Central Rectangle\n" +
                        "3 - Left Rectangle\n> ");
                option_rectangle = s.nextInt();
            }
            double a, b, accu;
            System.out.print("Please enter the interval a and b(\"a b\")\n> ");
            a = s.nextDouble();
            b = s.nextDouble();

            System.out.print("Please enter the accuracy\n> ");
            accu = s.nextDouble();
            double ans, oldAns = 0;
            double n = 4;
            while (true) {
                if (option_method == 1) {
                    ans = CalcData.rectangle(eqs[option_equ - 1], a, b, n, option_rectangle);
                }else{
                    ans = CalcData.trapezoidal(eqs[option_equ - 1], a, b, n );
                }
                if (oldAns != 0 && Math.abs(ans - oldAns) <= accu) {
                    break;
                }
                oldAns = ans;
                n *= 2;
                System.out.println("---------------------------------");
            }

        } catch (Exception e) {
            System.out.println("Please enter one of the specified numbers!");
            System.out.println(e.getMessage());
        }
    }

    private static void chooseEquation() {
        eqs[0] = new double[]{0, 0, 1, 0};
        eqs[1] = new double[]{1, -2, -1, -1};
        eqs[2] = new double[]{-2, 4, -5, -3};
        eqs[3] = new double[]{3, 1, -1, -1};
        eqs[4] = new double[]{-4, 8, -4, -2};
    }

}