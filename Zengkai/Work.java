class Work {

    private int[][] op1;
    private int[][] op2;
    private int[][] result;

    Work(int[][] op1, int[][] op2) {
        this.op1 = op1;
        this.op2 = op2;
    }

    Work(int[][] op1, int[][] op2, int[][] result) {
        this.op1 = op1;
        this.op2 = op2;
        this.result = result;
    }

    public void compute() {
    }

    public String toString() {
        return null;
    }

    public boolean verify() {
        return false;
    }

}