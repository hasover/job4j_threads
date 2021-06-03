package ru.job4j.cache;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class Cache {
    private final Map<Integer, Base> memory = new ConcurrentHashMap<>();

    public boolean add(Base model) {
        return memory.putIfAbsent(model.getId(), model) == null;
    }

    public boolean update(Base model) {
         return memory.computeIfPresent(model.getId(), (key, value) -> {

             if (value.getVersion() != model.getVersion()) {
                 throw new OptimisticException("Versions not equal");
             }
             Base updated = new Base(model.getId(), model.getVersion() + 1);
             updated.setName(model.getName());
             return updated;
         } ) != null;
    }


    public void delete(Base model) {
        memory.remove(model.getId());
    }
}