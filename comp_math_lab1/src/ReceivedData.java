import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.InputMismatchException;
import java.util.Scanner;

public class ReceivedData {

    // 接收矩阵维度
    public static int recDimension(){
        while(true) {
            System.out.print("Please enter the matrix dimension(0 <= dim <= 20):\n> ");
            try{
                Scanner s = new Scanner(System.in);
                int dim = s.nextInt();
                if (dim >= 20 || dim <= 0) {
                    throw new InputMismatchException();
                }
                return dim;
            } catch (InputMismatchException ime){
                System.out.println("\033[31m" + "Please input a number from (0 <= dim <= 20)!" + "\033[0m");
            }

        }
    }
//    public static int dim = recDimension();
    // 接收矩阵系数
//    public static double recMatrixValue(){
//        while(true){
//            try {
//                Scanner s = new Scanner(System.in);
//                return s.nextDouble();
//            }catch (InputMismatchException ise){
//                System.out.println("\033[31m" + "Please input a number!" + "\033[0m");
//            }
//        }
//    }

    // 接收输入的矩阵
    public static double[][] recMatrixInput(){
        int dim = recDimension();
        int bn = 1;
        double[][] matrix = new double[dim][dim + 1];
        while (true) {
            Scanner s = new Scanner(System.in);
//        double d = s.nextDouble();
            try {
                for (int i = 1; i <= dim; i++) {
                    for (int j = 1; j <= dim; j++) {
                        System.out.print("Please enter the value of a(" + i + ")(" + j + ")\n> ");
                        double val = s.nextDouble();
                        matrix[i - 1][j - 1] = val;
                    }
                    System.out.print("Please enter the value of b(" + bn + ")\n> ");

                    double val = s.nextDouble();
                    matrix[i - 1][dim] = val;
                    bn++;
                }
            } catch (InputMismatchException ime) {
                System.out.println("\033[31m" + "Please enter a number!" + "\033[0m");
            }
//        System.out.println();
            return matrix;
        }
    }
    // 接收文件中的矩阵
    public static double[][] recMatrixFile(){
        Scanner s = new Scanner(System.in);
        System.out.print("Please make sure that the format of the matrix in the file is:\n" +
                "a    a    a  ...  a    b\n" +
                "a    a    a  ...  a    b\n" +
                "... ...  ... ... ...  ...\n" +
                "a    a    a  ...  a    b\n" +
                "Please enter the path to the file:\n> ");
        String path = s.nextLine();
        FileReader reader;
        double[][] matrix = null;
        try {
            reader = new FileReader(path);
            BufferedReader br = new BufferedReader(reader);

            long lines = Files.lines(Paths.get(new File(path).getPath())).count();
            int linesInt = (int) lines;
            matrix = new double[linesInt][linesInt+1];

            int count = 0;
            String str = null;
            while((str = br.readLine()) != null){
                String[] vals = str.trim().split("\\s+");
                for (int i = 0; i < linesInt ; ){
                    for (String val : vals){
                        matrix[count][i] = Double.parseDouble(val);
                        i++;
                    }
                }
                count++;
            }
            System.out.println();
            br.close();
        } catch (FileNotFoundException e) {
            System.out.println("\033[31m" + "File not found, please re-enter the path!" + "\033[0m");
        } catch (IOException e){
            System.out.println(e.getMessage());
        }

        return matrix;
    }
}
