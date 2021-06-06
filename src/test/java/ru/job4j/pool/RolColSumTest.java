package ru.job4j.pool;

import org.junit.Test;

import java.util.concurrent.ExecutionException;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

public class RolColSumTest {

    private int[][] matrix = new int[][] {
            { 0, 1, 2, 3} ,
            { 4, 5, 6, 7 },
            { 8, 9, 10, 11},
            {12, 13, 14, 15}
    };

    private Sums[] sumsResult = new Sums[] {
            new Sums(6, 24),
            new Sums(22, 28),
            new Sums(38, 32),
            new Sums(54, 36)
    };

    @Test
    public void testSum() throws ExecutionException, InterruptedException {
        assertThat(sumsResult, is(RolColSum.sum(matrix)));
        assertThat(sumsResult, is(RolColSum.asyncSum(matrix)));

    }
}