package ru.job4j.singleton;

public class TrackerSingleHolder {

    private TrackerSingleHolder() {}

    public static TrackerSingleHolder getInstance() {
        return HOLDER.INSTANCE;
    }

    public Item add(Item model) {
        return model;
    }

    private static class Item {}

    private static final class HOLDER {
        private static final TrackerSingleHolder INSTANCE = new TrackerSingleHolder();
    }

    public static void main(String[] args) {
        TrackerSingleHolder tracker = TrackerSingleHolder.getInstance();
    }
}
