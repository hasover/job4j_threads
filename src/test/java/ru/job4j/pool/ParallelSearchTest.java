package ru.job4j.pool;

import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.is;
public class ParallelSearchTest {

    @Test
    public void whenFoundAndNot() {
        Integer[] array = new Integer[1000];
        for (int i = 0; i < 1000; i++) {
            array[i] = i;
        }
        assertThat(ParallelSearch.search(array, 950), is(950));
        assertThat(ParallelSearch.search(array, -30), is(-1));
    }
}