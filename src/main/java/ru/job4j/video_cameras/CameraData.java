package ru.job4j.video_cameras;

/**
 * Модель исходных данных CameraData
 */
public class CameraData {
    private int id;
    private String sourceDataUrl;
    private String tokenDataUrl;

    public int getId() {
        return id;
    }

    public String getSourceDataUrl() {
        return sourceDataUrl;
    }

    public String getTokenDataUrl() {
        return tokenDataUrl;
    }

}
