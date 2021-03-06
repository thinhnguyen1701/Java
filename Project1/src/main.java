
public class main {
	private static int numOfMul = 0;
	public static void main(String[] args) {
		// create some variables
		int size = 13;
		final double expectedValue = 1.0;
		double[][] matrix = new double[size][size + 1];
		double[][] copyMatrix = new double[size][size + 1];
		double[] exactVector = new double [size];
		
		// setup matrix
		for (int row = 0; row < size; row++) {
			exactVector[row] = expectedValue;
			for (int col = 0; col < size; col++) {
				matrix[row][col] = (1d / (row + col + 1));
				copyMatrix[row][col] = (1d / (row + col + 1));
				if (col == matrix.length - 1){
					matrix[row][col + 1] = sumRow(matrix[row]);
					copyMatrix[row][col + 1] = sumRow(copyMatrix[row]);
				}
			}

		}
		
		// apply Gaussian Elimination method
		for (int i = 0; i < size; i++) {
			copyMatrix = gaussianElimination(i, size - i - 1, copyMatrix);
		}
		double[] x = computeX(copyMatrix);
		// display matrix on the console
		
		print(size);
		print(x);
		displayError(exactVector, x);
		System.out.printf("infinity norm of the error vector is = %.4f\n", infiniteNorm(x));
		System.out.printf("Euclidean norm of the error vector is = %.4f\n", magnitude(x, exactVector));
		System.out.println("Number of multiplication in my computer program = " + numOfMul);
	}

	/**
	 * print the matrix on the console
	 * @param matrix that want to print
	 */
	public static void print(double matrix[][]) {
		for (int i = 0; i < matrix.length; i++) {
			for (int j = 0; j < matrix.length + 1; j++) {
				System.out.printf("%.4f\t", matrix[i][j]);
			}
			System.out.println();
		}
	}
	
	public static void print(double[] matrix){
		System.out.print("computed solution = Transpose of [");
		for(int i = 0; i < matrix.length; i++){
			String a = Double.toString(matrix[i]);
			System.out.print(a.substring(0,6));
			if(i == matrix.length - 1)
				System.out.print("]");
			else
				System.out.print("  ");
		}
		System.out.println();
	}
	
	private static void print(int size){
		System.out.printf("for n = %d, the exact solution is = Transpose of [", size);
		for(int i = 0; i < size; i++){
			System.out.printf("%d", 1);
			if(i == size - 1)
				System.out.print("]");
			else
				System.out.print("  ");
		}
		System.out.println();
	}
	
	private static void displayError(double[] a, double[] b){
		System.out.print("error = exact solution - computed solution = Transpose of [");
		for(int i = 0; i < a.length; i++){
			System.out.printf("%.4f",a[i] - b[i]);
			if(i == a.length - 1)
				System.out.print("]");
			else
				System.out.print("  ");
		}
		System.out.println();
	}
	
	/**
	 * calculate the sum of a specific row
	 * @param row that want to compute
	 * @return the sum of the specific row
	 */
	public static double sumRow(double[] row){
		double sum = 0;
		for(int i = 0; i < row.length; i++)
			sum = sum + row[i];
		return sum;
	}
	
	/**
	 * apply Gaussian Elimination method without pivoting
	 * @param pointer	keep tracking row by row
	 * @param repeat	the number of re-do the GE method
	 * @param matrix	want to compute
	 * @param pivot		
	 * @return 			the value of matrix
	 */
	public static double[][]gaussianElimination(int pointer, int repeat, double[][] matrix){
		double pivot = matrix[pointer][pointer];
		for(int i = pointer; i < matrix.length; i++){
			if(i == matrix.length - 1)
				break;
			double var = (matrix[i + 1][pointer] / pivot);
			for(int j = pointer; j < matrix.length + 1; j++){
				numOfMul++;
				matrix[i + 1][j] = (-(matrix[pointer][j] * var) + matrix[i + 1][j]);
			}
		}
		return matrix;
	}
	
	public static double[] computeX(double[][] matrix){
		double[] x = new double [matrix.length];
		for(int i = x.length - 1 ; i > -1; i--){
			double a = matrix[i][x.length];
			x[i] = (a - leftSide(x, matrix[i], i)) / matrix[i][i];
		}
		return x;
	}
	
	
	private static double leftSide(double[] x,double[] matrix, int n){
		double a = 0;
		for(int i = 0; i < matrix.length - 1; i++){
			if(i != n){
				a += x[i]*matrix[i];
			}
		}
		return a;
	}
	
	private static double infiniteNorm(double[] a){
		double max = Double.MIN_VALUE;
		for(int i = 0; i < a.length; i++){
			if(max < a[i])
				max = a[i];
		}
		return max;
	}
	
	private static double magnitude(double[] a, double[] b){
		double result =0;
		for(int i = 0; i < a.length; i++)
			result += a[i] * b[i];
		return Math.sqrt(result);
	}
}
