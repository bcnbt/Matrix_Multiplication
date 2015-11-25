import java.util.Scanner;

public class MatrixMultiplication {

	public static final int NUM_OF_THREADS = 10;

	public static void main(String args[]) {
		int row;
		int col;
		int threadcounter = 0;
		Thread[] workers = new Thread[NUM_OF_THREADS];
		
		// user input
		Scanner in = new Scanner(System.in);
		int M;
		int K;
		int TK; // temporary K
		int N;
		
		//Matrix A
		System.out.println("How many ROWS are in matrix A?");
		M = in.nextInt();
		System.out.println("How many COLUMNS are in matrix A?");
		K = in.nextInt();
		int[][] A = new int[M][K];
		for (row = 0; row < M; row++) {
			for (col = 0; col < K; col++) {
				System.out.println("Matrix A: " + row + " , " + col + " = ");
				System.out.println();
				A[row][col] = in.nextInt();
			}
		}
		System.out.println("Matrix A: ");
		for (row = 0; row < M; row++) {
			for (col = 0; col < K; col++) {
				System.out.print("  " + A[row][col]);
			}
			System.out.println();
		}
		
		//Matrix B
		System.out.println("How many ROWS are in matrix B?");
		TK = in.nextInt();
		System.out.println("How many COLUMNS are in matrix B?");
		N = in.nextInt();
		int[][] B = new int[K][N];
		for (row = 0; row < TK; row++) {
			for (col = 0; col < N; col++) {
				System.out.println("Matrix B: " + row + " , " + col + " = ");
				System.out.println();
				B[row][col] = in.nextInt();
			}
		}
		System.out.println("Matrix B: ");
		for (row = 0; row < K; row++) {
			for (col = 0; col < N; col++) {
				System.out.print("  " + B[row][col]);
			}
			System.out.println();
		}
		System.out.println();
		
		int[][] C = new int[M][N];
		
		try {
			for (row = 0; row < M; row++) {
				for (col = 0; col < N; col++) {
					workers[threadcounter] = new Thread(new WorkerThread(row, col, A, B, C));
					workers[threadcounter].start();
					workers[threadcounter].join();
					threadcounter++;
				}
			}
		} catch (InterruptedException ie) {
		}
		print(M, K, TK, N, A, B, C);
	}

	public static void print(int M, int K, int TK, int N, int[][] A, int[][] B, int[][] C) {
		int row;
		int col;
		if (TK != K) {
			System.out.print("Cannot multiply Matrices");
		} else {
			System.out.println("The Matrices Multiplied Correctly");
			System.out.println("Matrix A: ");
			for (row = 0; row < M; row++) {
				for (col = 0; col < K; col++) {
					System.out.print("  " + A[row][col]);
				}
				System.out.println();
			}
			System.out.println("Matrix B: ");
			for (row = 0; row < K; row++) {
				for (col = 0; col < N; col++) {
					System.out.print("  " + B[row][col]);
				}
				System.out.println();
			}
			System.out.println("Matrix C: ");
			for (row = 0; row < M; row++) {
				for (col = 0; col < N; col++) {
					System.out.print("  " + C[row][col]);
				}
				System.out.println();
			}
		}
	}
}