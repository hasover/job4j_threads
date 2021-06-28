package ru.job4j.video_cameras;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.net.URL;

/**
 * Класс для получения строк в формате Json по ссылке Url
 */
public class DataReader implements AutoCloseable {
    private final BufferedInputStream inputStream;

    public DataReader(String url) throws IOException {
        inputStream = new BufferedInputStream(new URL(url).openStream());
    }

    /**
     * Метод позволяет получить следующий объект в формате Json
     * @return  возвращает String или null, если данных больше нет

     */

    public String nextData() throws IOException {
        StringBuilder builder = new StringBuilder();
        int symbol;
        boolean dataRead = false;
        while (!dataRead) {
            if ((symbol = inputStream.read()) == -1) {
                return null;
            } else if (symbol == '{') {
                builder.append("{");
                while ((symbol = inputStream.read()) != '}') {
                    builder.append((char) symbol);
                }
                builder.append("}");
                dataRead = true;
            }
        }
        return builder.toString().trim();
    }

    @Override
    public void close() throws Exception {
        if (inputStream != null) {
            inputStream.close();
        }
    }
}
