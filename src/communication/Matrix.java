package communication;

import tool.Config.objectType;

public class Matrix implements java.io.Serializable{

	private static final long serialVersionUID = 5164470290457547216L;
	private static objectType type = objectType.Matrix;
	private int row;
	private int col;
	private int[][] data;
	public Matrix(int row,int col) {
		this.row = row;
		this.col = col;
		data = new int[row][col];
		for(int i = 0; i<row; i++)
			for(int j = 0; j<col; j++) {
				data[i][j] = 0;
			}
	}
	public Boolean change_value_at(int row,int col,int value) {
		if(row<this.row&&col<this.col) {
			data[row][col] = value;
			return true;
		}
		else
			return false;
	}
	
	public void change_data(int [][]data) {
		this.data = data;
	}
	
	public void clear() {
		for(int i = 0; i<row; i++)
			for(int j = 0; j<col; j++) {
				data[i][j] = 0;
			}
	}
	
	public String toString() {
		StringBuilder m = new StringBuilder();
		m.append("row:"+row+"  col:"+col+"\n");
		for(int i = 0; i<row; i++) {
			for(int j = 0; j<col; j++) {
				m.append(data[i][j]+" ");
			}
			m.append("\n");
		}
		return m.toString();
	}
	
	public int getRow() {
		return row;
	}
	public int getCol() {
		return col;
	}
	public int[][] getData() {
		return data;
	}

}
