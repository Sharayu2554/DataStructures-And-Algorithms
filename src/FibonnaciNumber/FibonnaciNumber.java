package FibonnaciNumber;

public class FibonnaciNumber {

    //f(n)  = f(n-1) + f(n-2)
    //this logic runtime would be O(n) not implementing this

    //[ f(n)   ]  = [ 1   1 ] [ f(n-1) ]
    //[ f(n-1) ]    [ 1   0 ] [ f(n-2) ]
    //Runnintg Time of this algorithm  : O(log n)

    public static int[][] matrixMulitplication(int[][] a, int[][] b) {
        int[][] c = new int[a.length][b[0].length];
        for (int i =0; i < a.length; i++) {
            for (int j = 0; j < b[i].length; j++) {
                c[i][j] = 0;
                for (int k = 0; k < a[i].length; k++ ) {
                    c[i][j] += a[i][k] * b[k][j];
                }
            }
        }
        return c;
    }

    //get A to the power n
    public static int[][] matrixPower(int[][] a, int n) {
        int[][] c = new int[a.length][a[0].length];
        if (n == 1)
            return a;
        else {
            int[][] p = matrixPower(matrixMulitplication(a, a), n/2);
            return n % 2 != 0 ? matrixMulitplication(p, a) : p;
        }
    }

    //fibonnaci Series
    //Vn[0][0] = A ^ n-1 * B
    public static int fibonnaciNumber(int[][] A, int n, int[][] B) {
        int[][] result = matrixMulitplication(matrixPower(A, n-1), B);
        return result[0][0];
    }

    public static void main(String args[]) {

        int[][] A = new int[2][2];

        A[0][0] = 1;
        A[0][1] = 1;
        A[1][0] = 1;
        A[1][1] = 0;

        int[][] B = new int[2][1];
        B[0][0] = 1;
        B[1][0] = 0;

        System.out.println(fibonnaciNumber(A, 7, B));
    }
}
