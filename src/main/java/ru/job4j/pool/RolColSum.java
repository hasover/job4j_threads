package ru.job4j.pool;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;


public class RolColSum {

    public static Sums[] sum(int[][] matrix) {
        int size = matrix.length;
        Sums[] sumsResult = new Sums[size];
        for (int i = 0; i < size; i++) {
            sumsResult[i] = new Sums(getRowSum(matrix, i), getColSum(matrix, i));
        }
        return sumsResult;
    }

    public static Sums[] asyncSum(int[][] matrix) throws ExecutionException, InterruptedException {
        int size = matrix.length;

        Map<Integer, CompletableFuture<Integer>> rowSums = new HashMap<>();
        Map<Integer, CompletableFuture<Integer>> colSums = new HashMap<>();

        for (int i = 0; i < size; i++) {
            final int j = i;
            rowSums.put(j, CompletableFuture.supplyAsync(() -> getRowSum(matrix, j)));
            colSums.put(j, CompletableFuture.supplyAsync(() -> getColSum(matrix, j)));
        }
        Sums[] result = new Sums[size];
        for (int i = 0; i < size; i++) {
            result[i] = new Sums(rowSums.get(i).get(), colSums.get(i).get());
        }
        return result;
    }

    private static int getRowSum(int[][] matrix ,int row) {
        int sum = 0;
        for (int i = 0; i < matrix.length ; i++) {
            sum += matrix[row][i];
        }
        return sum;
    }

    private static int getColSum(int[][] matrix, int col) {
        int sum = 0;
        for (int i = 0; i < matrix.length; i++) {
            sum += matrix[i][col];
        }
        return sum;
    }
}
