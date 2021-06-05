package ru.job4j.pool;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;

public class ParallelMergeSort extends RecursiveTask<int[]> {
    private final int[] array;
    private final int from;
    private final int to;

    public ParallelMergeSort(int[] array, int from, int to) {
        this.array = array;
        this.from = from;
        this.to = to;
    }
    @Override
    protected int[] compute() {
        if (from == to) {
            return new int[] { array[from]};
        }
        int mid = (to + from) / 2;

        ParallelMergeSort left = new ParallelMergeSort(array, from, mid);
        ParallelMergeSort right = new ParallelMergeSort(array, mid + 1, to);

       left.fork();
        right.fork();

        return MergeSort.merge(left.join(), right.join());
    }

    public static int[] sort(int[] array) {
        ForkJoinPool pool = new ForkJoinPool();
        return pool.invoke(new ParallelMergeSort(array, 0, array.length - 1));
    }

    public static void main(String[] args) {
        int[] arr = {13, 0 ,-4, 56, 100, 34, 55, 6, 8, 0};
        for (int i : sort(arr)) {
            System.out.print(i + " ");
        }
    }
}
