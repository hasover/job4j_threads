package ru.job4j;

import net.jcip.annotations.ThreadSafe;

import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;

class User implements Cloneable {
    private final int id;
    private int amount;

    public User(int id, int amount) {
        this.id = id;
        this.amount = amount;
    }

    @Override
    protected User clone() {
        return new User(this.id, this.amount);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return id == user.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    public int getId() {
        return id;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }
}

@ThreadSafe
public class UserStorage {

    private final Map<Integer, User> users = new ConcurrentHashMap<>();

    public boolean add(User user) {
        return users.putIfAbsent(user.getId(), user.clone()) == null;
    }
    
    public boolean update(User user) {
        return users.replace(user.getId(), user.clone()) != null;
    }

    public boolean delete(User user) {
        return users.remove(user.getId()) != null;
    }

    public synchronized boolean transfer(int fromId, int toId, int amount) {
        User fromUser = users.get(fromId);
        User toUser = users.get(toId);

        if (fromUser == null || toUser == null || fromUser.getAmount() < amount) {
            return false;
        }
        fromUser.setAmount(fromUser.getAmount() - amount);
        toUser.setAmount(toUser.getAmount() + amount);
        return true;
    }
}
