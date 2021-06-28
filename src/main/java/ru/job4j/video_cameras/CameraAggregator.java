package ru.job4j.video_cameras;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

/**
 * Класс производит аггрегацию данных
 */
public class CameraAggregator {

    private final ExecutorService service = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());

    /**
     * Метод принимает ссылку на список камер и возвращает список камер в агрегированном виде
     * Для каждой камеры в формате CameraData метод запускает два потока выполнения для получения
     * данных SourceData и TokenData, ссылки на результат потоков Future<> хранятся во времменом списке типа CameraTemp.
     * В конце временный список преобразуется к требуемому формату Camera
     * @param url ссылка на список доспупных камер
     * @return список на камеры в агрегированном виде
     *
     *
     */
    public List<Camera> aggregateCameraInfo(String url) throws ExecutionException, InterruptedException {
        List<CameraTemp> cameraTempList = new ArrayList<>();

        String cameraDataJson;

        try (DataReader reader = new DataReader(url)) {
            while ((cameraDataJson = reader.nextData()) != null) {
                CameraData cameraData = JsonFormatter.dataFromJson(cameraDataJson, CameraData.class);

                Callable<SourceData> sourceDataTask = () -> {
                    SourceData sourceData;
                    try (DataReader dataReader = new DataReader(cameraData.getSourceDataUrl())) {
                        sourceData = JsonFormatter.dataFromJson(dataReader.nextData(), SourceData.class);
                    }
                    return sourceData;
                };

                Callable<TokenData> tokenDataTask = () -> {
                    TokenData tokenData;
                    try (DataReader dataReader = new DataReader(cameraData.getTokenDataUrl())) {
                        tokenData = JsonFormatter.dataFromJson(dataReader.nextData(), TokenData.class);
                    }
                    return tokenData;
                };

                cameraTempList.add(new CameraTemp(cameraData.getId(), service.submit(sourceDataTask), service.submit(tokenDataTask)));
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        List<Camera> cameraList = new ArrayList<>(cameraTempList.size());
        for (CameraTemp temp : cameraTempList) {
            cameraList.add(new Camera(temp.id, temp.sourceData.get().urlType, temp.sourceData.get().videoUrl,
                                               temp.tokenData.get().value, temp.tokenData.get().ttl));
        }
        service.shutdown();
        return cameraList;
    }

    private class CameraTemp {
        int id;
        Future<SourceData> sourceData;
        Future<TokenData> tokenData;

        public CameraTemp(int id, Future<SourceData> sourceData, Future<TokenData> tokenData) {
            this.id = id;
            this.sourceData = sourceData;
            this.tokenData = tokenData;
        }
    }
}
