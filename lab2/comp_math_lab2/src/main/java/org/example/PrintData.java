package org.example;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class PrintData {

    public static void printRes(double[] a, int count) {
        StringBuilder s = new StringBuilder(String.format(" %1$03d|", count));
        for (int i = 0; i < a.length; i++) {
            s.append(String.format("%1$6.4f | ", a[i]));
        }
        s.append("\n");
        System.out.print(s);
        str.append(s );
    }

    public static void printResultToFile(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Output the result to a separate file?(y/n)");
        String answer = scanner.nextLine();
        if (answer.equalsIgnoreCase("y")) {
            System.out.println("Please enter the name of File:");
            String filename = scanner.nextLine();
            printToFile(filename);
            System.out.println("\nSuccess, program exit");
            System.exit(0);
        } else {
            System.out.println("Program exit");
            System.exit(0);
        }
    }

    public static StringBuilder str = new StringBuilder();
    public static void printToFile(String filename) {
        try(FileWriter writer = new FileWriter("src/main/java/outbox/" + filename + ".txt", false)) {
            writer.write(str.toString());
            writer.flush();
        } catch(IOException e){
            System.out.println(e.getMessage());
        }
    }
}
