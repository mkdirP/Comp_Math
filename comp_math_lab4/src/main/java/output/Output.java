package output;

public class Output {
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

}
