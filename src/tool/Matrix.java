package tool;

public class Matrix implements java.io.Serializable{
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
}
