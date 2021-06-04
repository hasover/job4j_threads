package ru.job4j.singleton;

public class TrackerSingleDCL {
    private volatile static TrackerSingleDCL tracker;

    private TrackerSingleDCL() {

    }

    public static TrackerSingleDCL getInstance() {

        if (tracker == null) {
            synchronized (TrackerSingleDCL.class) {
                if (tracker == null)
                tracker = new TrackerSingleDCL();
            }
        }
        return tracker;
    }

    public Item add(Item model) {
        return model;
    }

    private static class Item {}

    public static void main(String[] args) {
        TrackerSingleDCL tracker = TrackerSingleDCL.getInstance();
    }
}
