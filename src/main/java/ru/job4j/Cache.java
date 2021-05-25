package ru.job4j;

public final class Cache {
    private static Cache cache;

    public synchronized static Cache insOf() {
        if (cache == null) {
            cache = new Cache();
        }
        return cache;
    }
}
