import java.math.BigDecimal;

public class CalculateData {
    // 计算矩阵的行列式 Вычисление определителя матрицы
    public static double calcDeterminant(double[][] matrix){
        int dim = matrix.length;
        double det = 0;
        if (dim == 1) return matrix[0][0];

        else if (dim == 2) {
            det = matrix[0][0] * matrix[1][1] - matrix[0][1] * matrix[1][0];
        } else {
            for (int a = 0; a < dim; a++) {
                double[][] m = new double[dim-1][];
                for (int k = 0; k < (dim - 1); k++) {
                    m[k] = new double[dim - 1];
                }
                for (int i = 1; i < dim; i++) {
                    int j2 = 0;
                    for (int j = 0; j < dim; j++) {
                        if (j == a) {
                            continue;
                        }
                        m[i-1][j2] = matrix[i][j];
                        j2++;
                    }
                }
                det += Math.pow(-1.0, 1.0+a+1.0) * matrix[0][a] * calcDeterminant(m);
            }
        }

        return det;
    }

//    高斯方法将线性方程组转换为三角矩阵 Гауссовский метод преобразования системы линейных уравнений в треугольную матрицу
    public static double[][] calcTriangularMatrix(double[][] matrix) {
        int rows = matrix.length;
        int cols = matrix[0].length;
        double[][] triangularMatrix = new double[rows][cols];
        // 将输入矩阵复制到三角矩阵中
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                triangularMatrix[i][j] = matrix[i][j];
            }
        }
        // 执行高斯消除法，将矩阵转化为三角矩阵
        for (int i = 0; i < rows - 1; i++) {
            for (int j = i + 1; j < rows; j++) {
                double factor = triangularMatrix[j][i] / triangularMatrix[i][i];
                triangularMatrix[j][i] = 0.0;  // Set the diagonal element to zero
                for (int k = i + 1; k < cols; k++) {
                    triangularMatrix[j][k] = triangularMatrix[j][k] - factor * triangularMatrix[i][k];
                }
            }
        }
        // 将对角线元素简化为1
        for (int i = 0; i < rows; i++) {
            double divisor = triangularMatrix[i][i];
            for (int j = i; j < cols; j++) {
                triangularMatrix[i][j] = triangularMatrix[i][j] / divisor;
            }
        }
        return triangularMatrix;
    }

//    计算线性方程组未知数 Вычисление количества неизвестных в системе линейных уравнений
    public static double[] calcUnknows(double[][] matrix) {
        int rows = matrix.length;
        int cols = matrix[0].length - 1;
        double[][] triangularMatrix = calcTriangularMatrix(matrix);

        // Back-substitution to solve the system of linear equations
        double[] x = new double[rows];
        for (int i = rows - 1; i >= 0; i--) {
            double sum = 0.0;
            for (int j = i + 1; j < cols; j++) {
                sum += triangularMatrix[i][j] * x[j];
            }
            x[i] = (triangularMatrix[i][cols] - sum) / triangularMatrix[i][i];
        }

        return x;
    }

//    计算线性方程组差异 Вычисление разности между системами линейных уравнений
    public static double[] calcResidual(double[][] matrix) {
        int n = matrix.length;
        double[] x = calcUnknows(matrix);
        double[][] A = new double[n][n];
        double[] b = new double[n];
        for (int i=0; i<n; i++){
            for (int j=0; j<n; j++){
                A[i][j] = matrix[i][j];
            }
            b[i] = matrix[i][matrix.length];
        }
        double[] r = new double[n];
        // 对增量矩阵[A | b]进行高斯消解
        for (int k = 0; k < n - 1; k++) {
            for (int i = k + 1; i < n; i++) {
                double factor = A[i][k] / A[k][k];
                for (int j = k + 1; j < n; j++) {
                    A[i][j] -= factor * A[k][j];
                }
                b[i] -= factor * b[k];
            }
        }
        // 反向替代以找到解决方案x
        for (int i = n - 1; i >= 0; i--) {
            double sum = 0.0;
            for (int j = i + 1; j < n; j++) {
                sum += A[i][j] * x[j];
            }
            x[i] = (b[i] - sum) / A[i][i];
        }
        // r = Ax - b
        for (int i = 0; i < n; i++) {
            double sum = 0.0;
            for (int j = 0; j < n; j++) {
                sum += A[i][j] * x[j];
            }
            r[i] = sum - b[i];
        }
        return r;
    }

}
