package ru.job4j.video_cameras;

import java.util.Objects;

/**
 * Модель выходных данных - Camera
 */
public class Camera {
    int id;
    String urlType;
    String videoUrl;
    String value;
    int ttl;

    public Camera(int id, String urlType, String videoUrl, String value, int ttl) {
        this.id = id;
        this.urlType = urlType;
        this.videoUrl = videoUrl;
        this.value = value;
        this.ttl = ttl;
    }

    public int getId() {
        return id;
    }

    public String getUrlType() {
        return urlType;
    }

    public String getVideoUrl() {
        return videoUrl;
    }

    public String getValue() {
        return value;
    }

    public int getTtl() {
        return ttl;
    }

    @Override
    public String toString() {
        return "Camera{" +
                "id=" + id +
                ", urlType='" + urlType + '\'' +
                ", videoUrl='" + videoUrl + '\'' +
                ", value='" + value + '\'' +
                ", ttl=" + ttl +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Camera camera = (Camera) o;
        return id == camera.id &&
                ttl == camera.ttl &&
                Objects.equals(urlType, camera.urlType) &&
                Objects.equals(videoUrl, camera.videoUrl) &&
                Objects.equals(value, camera.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, urlType, videoUrl, value, ttl);
    }
}
