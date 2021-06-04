package ru.job4j.singleton;

public class TrackerSingleSCL {
    private static TrackerSingleSCL tracker;

    private TrackerSingleSCL() {

    }

    public static synchronized TrackerSingleSCL getInstance() {
        if (tracker == null) {
            tracker = new TrackerSingleSCL();
        }
        return tracker;
    }

    public Item add(Item model) {
        return model;
    }

    private static class Item {}

    public static void main(String[] args) {
        TrackerSingleSCL tracker = TrackerSingleSCL.getInstance();
    }
}
