package ru.job4j.video_cameras;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
/**
 * Класс для создания объекта из строки Json формата
 */
public class JsonFormatter {
    private static Gson gson = new GsonBuilder().create();

    public static <T> T dataFromJson(String data, Class<T> tClass) {
        return gson.fromJson(data, tClass);
    }

}
