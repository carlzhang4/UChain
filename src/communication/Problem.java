package communication;

import java.io.Serializable;

public class Problem implements Serializable{

	private static final long serialVersionUID = 4726810114797047095L;
	public Matrix matrixA;
	public Matrix matrixB;

	public Problem(Matrix a,Matrix b) {
		matrixA = a;
		matrixB = b;
	}

	public String toString() {
		return matrixA.toString()+matrixB.toString();
	}
}
