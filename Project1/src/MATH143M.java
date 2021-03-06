
public class MATH143M {
	public static void main(String[] args){
		int size = 5;
		
		String[][] matrix = new String[size][size + 1];
		
		for(int i = 0; i < size; i++){
			for(int j = 0; j < size + 1; j++){
				matrix[i][j] = Double.toString(1.0 / (i + j + 1));
				if(j == size - 1){
					matrix[i][j+1] = Double.toString(sumRow(matrix[i]));
				}
			}
		}
		
		//apply method
		for (int i = 0; i < size; i++) {
			matrix = gaussianElimination(i, size - i - 1, matrix);
		}
		
		print(matrix);
	}
	
	public static double sumRow(String[] row){
		double a = 0;
		for(int i = 0; i < row.length - 1; i++)
			a += Double.parseDouble(row[i]);
		return a;
	}
	
	public static void print(String[][] matrix){
		for(int i = 0; i < matrix.length; i++){
			for(int j = 0; j < matrix.length + 1; j++){
				System.out.print(matrix[i][j] + "\t");;
			}
			System.out.println();
		}
	}
	
	public static String[][]gaussianElimination(int pointer, int repeat, String[][] matrix){
		double pivot = Double.parseDouble(matrix[pointer][pointer]);
		for(int i = pointer; i < matrix.length; i++){
			if(i == matrix.length - 1)
				break;
			double var = (Double.parseDouble(matrix[i + 1][pointer]) / pivot);
			for(int j = pointer; j < matrix.length + 1; j++){
				matrix[i + 1][j] = Double.toString((-(Double.parseDouble(matrix[pointer][j]) * var) + Double.parseDouble(matrix[i + 1][j])));
			}
		}
		System.out.println("----------------------");
		return matrix;
	}
	
	public static double[] computeX(String[][] matrix){
		double[] xTrans =new double [matrix.length];
		for(int i = 0; i < xTrans.length; i++){
			xTrans[i] = 0;
		}
		for(int i = xTrans.length; i > 0; i--){
			
		}
		return xTrans;
	}
}
