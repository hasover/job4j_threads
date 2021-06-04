package ru.job4j.singleton;

public enum  TrackerSingleEnum {
    INSTANCE;

    public Item add(Item model) {
        return model;
    }

    private static class Item {

    }

    public static void main(String[] args) {
        TrackerSingleEnum tracker = TrackerSingleEnum.INSTANCE;
    }
}
