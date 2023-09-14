package method;

public class Table {
    // 生成数据表格
    public static double[][] table(double[][] matrix){
        double[][] matrixTable = new double[matrix.length][matrix.length+1];
        for (int i = 0; i < matrix.length; i++){
            matrixTable[i][0] = matrix[i][0];
            matrixTable[i][1] = matrix[i][1];
            if (i == matrix.length - 1){
                int variable_value = matrix.length;
                for (int j = 3; j < matrix.length + 1; j++) {
                    for (int k = 0; k <= variable_value - 2; k++) {
                        if (k == variable_value - 2) {
                            variable_value -= 1;
                            break;
                        }
                        matrixTable[k][j] = matrixTable[k + 1][j - 1] - matrixTable[k][j - 1];
                    }
                }
                break;
            }
            matrixTable[i][2] = matrix[i+1][1] - matrix[i][1];
        }

        return matrixTable;
    }
}
