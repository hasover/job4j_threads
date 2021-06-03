package ru.job4j.cache;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

public class CacheTest {
    @Test
    public void whenCanAdd() {
        Cache cache = new Cache();
        Base base = new Base(1, 0);
        base.setName("a");
        assertThat(cache.add(base), is(true));
    }

    @Test
    public void whenCannotAdd() {
        Cache cache = new Cache();
        Base base = new Base(1, 0);
        base.setName("a");
        cache.add(base);
        base.setName("b");
        assertThat(cache.add(base), is(false));
    }

    @Test
    public void whenUpdateSuccessful() {
        Cache cache = new Cache();
        Base base = new Base(1, 0);
        base.setName("a");
        cache.add(base);
        base.setName("b");
        assertThat(cache.update(base), is(true));

    }

    @Test(expected = OptimisticException.class)
    public void whenUpdateFailed() {
        Cache cache = new Cache();
        Base base = new Base(1, 0);
        base.setName("a");
        cache.add(base);
        base.setName("b");
        cache.update(base);
        base.setName("c");
        cache.update(base);

    }
}