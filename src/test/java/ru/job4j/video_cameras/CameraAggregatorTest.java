package ru.job4j.video_cameras;

import org.junit.Test;

import java.util.List;
import java.util.concurrent.ExecutionException;

import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.is;

public class CameraAggregatorTest {

    @Test
    public void test() throws ExecutionException, InterruptedException {
        CameraAggregator aggregator = new CameraAggregator();
        Camera one = JsonFormatter.dataFromJson(
                "{id=1, urlType='LIVE', videoUrl='rtsp://127.0.0.1/1', value='fa4b588e-249b-11e9-ab14-d663bd873d93', ttl=120}", Camera.class);
        Camera two = JsonFormatter.dataFromJson(
                "{id=20, urlType='ARCHIVE', videoUrl='rtsp://127.0.0.1/2', value='fa4b5b22-249b-11e9-ab14-d663bd873d93', ttl=60}", Camera.class);
        Camera three = JsonFormatter.dataFromJson(
                "{id=3, urlType='ARCHIVE', videoUrl='rtsp://127.0.0.1/3', value='fa4b5d52-249b-11e9-ab14-d663bd873d93', ttl=120}", Camera.class);
        Camera four = JsonFormatter.dataFromJson(
                "{id=2, urlType='LIVE', videoUrl='rtsp://127.0.0.1/20', value='fa4b5f64-249b-11e9-ab14-d663bd873d93', ttl=180}", Camera.class);

        List<Camera> expectedList = List.of(one, two, three, four);

        assertThat(expectedList, is(aggregator.aggregateCameraInfo("http://www.mocky.io/v2/5c51b9dd3400003252129fb5")));
    }

}