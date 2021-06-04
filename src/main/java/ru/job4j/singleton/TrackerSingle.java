package ru.job4j.singleton;

public class TrackerSingle {
    private static final TrackerSingle INSTANCE = new TrackerSingle();

    private TrackerSingle() {

    }

    public static TrackerSingle getInstance() {
        return INSTANCE;
    }

    public Item add(Item model) {
        return model;
    }

    private static class Item {

    }

    public static void main(String[] args) {
        TrackerSingle tracker = TrackerSingle.getInstance();
    }
}
