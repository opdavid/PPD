import java.util.Scanner;

public class MatrixMultiplication {

    public static void run() {

        int m, n, c, d;
        Scanner in = new Scanner(System.in);

        System.out.println("Enter the number of rows and columns of matrix");
        m = in.nextInt();
        n = in.nextInt();

        int myMatrixA[][] = new int[m][n];
        int myMatrixB[][] = new int[m][n];

        System.out.println("Enter the elements of first matrix");

        for (c = 0; c < m; c++)
            for (d = 0; d < n; d++)
                myMatrixA[c][d] = in.nextInt();

        System.out.println("Enter the elements of second matrix");

        for (c = 0; c < m; c++)
            for (d = 0; d < n; d++)
                myMatrixB[c][d] = in.nextInt();

        //counters
        int i, j, k;
        //rows and columns for each matrix
        int rowsA = myMatrixA.length;
        int colsA = myMatrixA[0].length;
        int rowsB = myMatrixB.length;
        int colsB = myMatrixB[0].length;
        //new matrix to hold result
        int[][] myMatrixC = new int[rowsA][colsB];
        //start across rows of A

        for (i = 0; i < rowsA; i++)
        {
            //work across cols of B
            for (j = 0; j < colsB; j++) {
                //now complete the addition and multiplication
                for (k = 0; k < colsA; k++) {
                    myMatrixC[i][j] += myMatrixA[i][k] * myMatrixB[k][j];
                }
            }
        }

        System.out.println("Multiplying A and B equals: ");
        for(m = 0; m < myMatrixC.length; m++) {
            for( n = 0; n < myMatrixC[0].length; n++) {
                System.out.print(myMatrixC[m][n] + " ");
            }
            System.out.println();
        }
    }
}
