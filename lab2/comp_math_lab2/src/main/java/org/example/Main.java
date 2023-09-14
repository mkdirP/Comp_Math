package org.example;

import methodData.*;
import graph.Graph;
import graph.PutInGraph;
//import org.example.PrintData.printResultToFile;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        System.out.println("[Численное решение нелинейных уравнений и систем]");
        boolean transcendent = false;
        double[] arr = new double[0];
        Scanner s = new Scanner(System.in);
        try {
            System.out.print("Please select the expression:\n" +
                    "1) 2.74x^3 - 1.93x^2 - 15.28x - 3.72\n" +
                    "2) x^2 -x -sin(x)\n" +
                    "3) x_1^2 - 2x_2 = 0\n" +
                    "   3x_1 - x_2^2 + 3 = 0\n> ");
            int exp = s.nextInt();
            if (exp == 1){
                arr = new double[4];
                arr[0] = -3.72;
                arr[1] = -15.28;
                arr[2] = -1.93;
                arr[3] = 2.74;
            } else if (exp == 2) {
                arr = new double[3];
                arr[0] = -1;
                arr[1] = -1;
                arr[2] = 1;
                transcendent = true;
            } else if (exp == 3){
                System.out.print("Please enter the accuracy, a, b:\n> ");
                double eps = s.nextDouble();
                double a = s.nextDouble();
                double b = s.nextDouble();
                NewTon.initSolve(eps, a, b);
                PrintData.printResultToFile();
                System.exit(0);
            } else {
                System.out.println("\033[31m" + "Please input from (1/2/3)! " + "\033[0m");
            }
        } catch (InputMismatchException ime){
            System.out.println("\033[31m" + "Please input a number!" + "\033[0m");
        } finally {
            Scanner sc2 = new Scanner(System.in);
            System.out.print("Enter the preliminary intervals for root isolation:\n> ");
            double a1 = sc2.nextDouble();
            double b1 = sc2.nextDouble();
            Graph graph1 = new Graph("graph");
            graph1.graph(PutInGraph.drawGraph(transcendent, a1, b1, arr));
        }
        double[] ds = chooseInput();
        double eps = ds[0];
        double a = ds[1];
        double b = ds[2];

        System.out.print("Please select method of solving:\n" +
                "1 - simple iteration\n" +
                "2 - part div\n" +
                "3 - secant\n> ");
        s = new Scanner(System.in);
        int methodMode = s.nextInt();
        while (true) {
            if (methodMode == 1) {
                SimpleIteration.initSolve(arr, a, b, eps, transcendent);
                break;
            }
            if (methodMode == 2) {
                new PartDiv(arr, eps, transcendent).initSolve(a, b);
                break;
            }
            if (methodMode == 3) {
                Secant.initSolve(arr, a, b, eps, transcendent);
                break;
            }
            System.out.println("\033[31m" + "Please input from (1/2/3)! " + "\033[0m");
            methodMode = s.nextInt();
        }

        Graph graph2 = new Graph("graph");
        graph2.graph(PutInGraph.points);

        PrintData.printResultToFile();
    }

    private static double[] chooseInput(){
        Scanner s = new Scanner(System.in);
        System.out.print("Please select the location of the file input:\n" +
                "1 - from console\n" +
                "2 - from file\n> ");
        int in = s.nextInt();
        boolean isFile = false;
        while (true) {
            if (in == 2) {
                isFile = true;
                break;
            }else if (in == 1) {
                break;
            }
            System.out.println("\033[31m" + "Please input from (file)! " + "\033[0m");
            in = s.nextInt();
        }
        double[] doubles = new double[4];
        if (isFile) {
            System.out.print("Please enter the path to file:\n> ");
            Scanner s1 = new Scanner(System.in);
            String path = s1.nextLine();
            FileReader reader;
            try {
                reader = new FileReader(path);
                BufferedReader br = new BufferedReader(reader);

                String line;
                int numColumns;
                while ((line = br.readLine()) != null) {
                    String[] columns = line.split("\\s+");
                    numColumns = columns.length;
                    doubles = new double[numColumns];
                    for (int i = 0; i < numColumns; i++){
                        doubles[i] = Double.parseDouble(columns[i]);
                    }
                }

                br.close();
                reader.close();
            } catch (FileNotFoundException e) {
                System.out.println("\033[31m" + "File not found, please re-enter the path!" + "\033[0m");
            } catch (IOException e){
                System.out.println(e.getMessage());
            }
        } else {
            System.out.print("Enter an accuracy, a, b:\n> ");
            Scanner s2 = new Scanner(System.in);
            doubles[0] = s2.nextDouble();
            doubles[1] = s2.nextDouble();
            doubles[2] = s2.nextDouble();
        }
        return doubles;
    }
}

