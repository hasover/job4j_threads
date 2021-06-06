package ru.job4j.pool;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;

public class ParallelSearch<T extends Comparable<T>> extends RecursiveTask<Integer> {
    private final T[] array;
    private final T object;
    private final int start;
    private final int end;

    public ParallelSearch(T[] array, T object, int start, int end) {
        this.array = array;
        this.object = object;
        this.start = start;
        this.end = end;
    }


    @Override
    protected Integer compute() {

        int mid = (start + end) / 2;

        int compareResult = object.compareTo(array[mid]);

        if(compareResult == 0) {
            return mid;
        }

        if (end - start + 1 <= 10) {
            for (int i = start; i <= end ; i++) {
                if (array[i].compareTo(object) == 0) {
                    return i;
                }
            }
            return -1;
        }

        ParallelSearch<T> searchRange;
        if (compareResult > 0) {
            searchRange = new ParallelSearch<>(array, object, mid + 1, end);
        } else {
            searchRange = new ParallelSearch<>(array, object, start, mid);
        }
        searchRange.fork();
        return searchRange.join();
    }

    public static <T extends Comparable<T>> int search(T[] array, T object) {
        ForkJoinPool pool = new ForkJoinPool();
        return pool.invoke(new ParallelSearch<>(array, object, 0, array.length - 1));
    }

}