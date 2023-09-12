import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.stream.Stream;
//matrix - 输入的二维矩阵

public class Receive {
    // 接收矩阵维度
    public static int recDimension(){
        while(true) {
            System.out.print("Please enter the number of pairs(8 <= pairs <= 12):\n> ");
            try{
                Scanner s = new Scanner(System.in);
                int dim = s.nextInt();
                if (dim > 12 || dim < 8) {
                    throw new InputMismatchException();
                }
                return dim;
            } catch (InputMismatchException ime){
                System.out.println("\033[31m" + "Please input a number from (8 <= pairs<= 12)!" + "\033[0m");
            }

        }
    }

    // 接收输入的矩阵
    public static double[][] recMatrixInput(){
        int dim = recDimension(); //行数
        double[][] matrix = new double[dim][2];

        while (true) {
            try {
                Scanner s = new Scanner(System.in);
                System.out.print("Please enter the value of xi yi \n");
                int count = 0;
                String str = null;
                while ((str = s.nextLine()) != null && count < dim) {
                    String[] vals = str.trim().split("\\s+");
                    for (int i = 0; i < 2 ; ){
                        for (String val : vals){
                            matrix[count][i] = Double.parseDouble(val);
                            i++;
                        }
                    }
                    count++;
                }
            } catch (InputMismatchException ime) {
                System.out.println("\033[31m" + "Please enter a number!" + "\033[0m");
            }
            return matrix;
        }
    }
    // 接收文件中的矩阵
    public static double[][] recMatrixFile(){
        Scanner s = new Scanner(System.in);
        System.out.print("Please make sure that the format of the matrix in the file is:\n" +
                "a    b\n" +
                "a    b\n" +
                "... ...\n" +
                "a    b\n" +
                "Please enter the path to the file:\n> ");
        String path = s.nextLine();
        FileReader reader;
        double[][] matrix = null;
        try {
            reader = new FileReader(path);
            BufferedReader br = new BufferedReader(reader);

//            行数
            long lines = Files.lines(Paths.get(new File(path).getPath())).count();
            int linesInt = (int) lines;
            matrix = new double[linesInt][2];

            int count = 0;
            String str = null;
            while((str = br.readLine()) != null){
                String[] vals = str.trim().split("\\s+");
                for (int i = 0; i < 2 ; ){
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
