package com.example.twitter.utility;
import java.util.Random;
import java.lang.Thread;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.lang.Exception;
import java.lang.Runnable;
import java.util.concurrent.TimeUnit;

public class matrixUtility {
        public ExecutorService es;
        public int numOfThreads;

        public matrixUtility(int numOfThreads) {
                this.numOfThreads = numOfThreads;
                es = Executors.newFixedThreadPool(this.numOfThreads);
        }

        public int[][] matrixMultiplication(int[][] a, int[][] b) throws Exception{
                if(a[0].length!=b.length) {
                        throw new Exception("dimension of two matrix do not align!");
                }
                int[][] c = new int[a.length][b[0].length];
                for(int i=0;i<a.length;i++) {
                        Runnable r = new matrixRunnable(i,a,b,c);
                        es.execute(r);
                }
                es.shutdown();
                //es.awaitTermination(60,TimeUnit.SECONDS);
                try {
                    // Wait a while for existing tasks to terminate
                    if (!es.awaitTermination(60, TimeUnit.SECONDS)) {
                        es.shutdownNow(); // Cancel currently executing tasks
                    // Wait a while for tasks to respond to being cancelled
                    if (!es.awaitTermination(60, TimeUnit.SECONDS))
                        System.err.println("Pool did not terminate");
                    }
                } catch (InterruptedException ie) {
                    // (Re-)Cancel if current thread also interrupted
                    es.shutdownNow();
                    // Preserve interrupt status
                    Thread.currentThread().interrupt();
                }
                return c;
        }

        public int[][] normalMatrixMultiplication(int[][] a, int[][] b) throws Exception{
                if(a[0].length!=b.length) {
                        throw new Exception("dimension of two matrix do not align!");
                }
                int[][] c = new int[a.length][b[0].length];
                for(int i=0;i<a.length;i++) {
                        for(int j=0;j<b[0].length;j++) {
                                for(int k=0;k<b.length;k++) {
                                        c[i][j] += a[i][k] * b[k][j];
                                }
                        }
                }
                return c;
        }

        public static void main(String[] args) throws Exception{
            matrixUtility m = new matrixUtility(4);
            Random rand = new Random();
            int upperBound = 4;
            int[][] a = new int[700][1300];
            int[][] b = new int[1300][700];
            for(int i=0;i<a.length;i++){
                for(int j=0;j<a[0].length;j++){
                    a[i][j] = rand.nextInt(upperBound);
                }
            }
            for(int i=0;i<b.length;i++){
                for(int j=0;j<b[0].length;j++){
                    b[i][j] = rand.nextInt(upperBound);
                }
            }
            final long t3 = System.currentTimeMillis();
            int[][] d = m.matrixMultiplication(a,b);
            final long t4 = System.currentTimeMillis();
            System.out.println("Total time execution " + (t4-t3));
            final long t1 = System.currentTimeMillis();
            int[][] c = m.normalMatrixMultiplication(a,b);
            final long t2 = System.currentTimeMillis();
            System.out.println("Total time execution for normal " + (t2-t1));
            for(int i=0;i<c.length;i++){
                for(int j=0;j<c[0].length;j++){
                    if(c[i][j]!=d[i][j]){
                        System.out.println("" + i + " "+ j + " " + c[i][j] + " " + d[i][j]);
                    }
                    //System.out.print(d[i][j]+" ");
                }
                //System.out.println("");
            }
            System.out.println("completed!");
        }
}

class matrixRunnable implements Runnable{
        int row;
        int[][] A;
        int[][] B;
        int[][] C;

        public matrixRunnable(int row, int[][] a, int[][] b, int[][] c) {
                this.row = row;
                this.A = a;
                this.B = b;
                this.C = c;
        }

        @Override
        public void run() {
                for(int i=0;i<B[0].length;i++) {
                        for(int j=0;j<B.length;j++) {
                                C[row][i] += A[row][j]*B[j][i];
                        }
                }
        }
}
