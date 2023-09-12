import java.math.BigDecimal;
import java.text.DecimalFormat;

public class PrintData {
    public static void printMatrix(double[][] matrix){
        for (double[] ds : matrix){
            for (double d : ds){
                System.out.printf("%.4f", d);
                System.out.print("       ");
            }
            System.out.println();
        }
        System.out.println();
    }

    public static void printArray(double[] x, String mode){
        int i = 1;
        for (double d : x){
            if (mode.equals("x")) {
                System.out.print("x(" + i + "): ");
            }else{
                System.out.print("r(" + (i-1) + "1): ");
            }
            System.out.printf("%.4f", d);
            i++;
            System.out.println();
        }
        System.out.println();
    }
}
