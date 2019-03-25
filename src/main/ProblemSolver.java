package main;

import communication.Matrix;
import communication.Problem;
import tool.Tool;

public class ProblemSolver {
	Matrix matrixA,matrixB;
	Matrix matrixC;
	public static void main(String[] args) {
		Matrix a = new Matrix(3, 3);
		Matrix b = new Matrix(3, 3);
		int[][] data1 = {{4,2,3},{2,1,4},{5,2,2}};
		int[][] data2 = {{2,3,4},{5,3,2},{1,2,4}};
		
		a.change_data(data1);
		b.change_data(data2);
		Problem problem = new Problem(a, b);
		String answer = new ProblemSolver(problem).run().toString();
		Tool.print(answer);
		Tool.print(answer.hashCode());
	}
	
	public ProblemSolver(Problem problem) {
		matrixA = problem.matrixA;
		matrixB = problem.matrixB;
		
	}
	public Matrix run() {
		int col = matrixA.getCol();
		int row = matrixA.getRow();
		matrixC = new Matrix(row, col);
		int [][] a = matrixA.getData();
		int [][] b = matrixB.getData();
		int [][] c = new int[row][col];
		for(int i=0;i<row;i++)
			for(int j=0;j<col;j++)
				for(int k=0;k<row;k++) {
					c[i][j]+=a[i][k]*b[k][j];
				}
		matrixC.change_data(c);
		return matrixC;
	}
}
