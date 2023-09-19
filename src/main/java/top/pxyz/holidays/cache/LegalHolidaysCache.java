package top.pxyz.holidays.cache;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 法定假日缓存
 *
 * @author Ijiran
 * @date 2023/09/18
 * @since 1.0
 */
public class LegalHolidaysCache {

    /**
     * 缓存内置年份Map
     */
    public static final Map<String, String> CACHE_YEAR_MAP = new ConcurrentHashMap<>();

    /**
     * 法定假日Map
     */
    public static final Map<String, String> LEGAL_HOLIDAY_MAP = new ConcurrentHashMap<>();

    /**
     * 法律工作日Map
     */
    public static final Map<String, String> LEGAL_WORK_MAP = new ConcurrentHashMap<>();

    static {
        try {
            initCache();
        } catch (IOException | URISyntaxException e) {
            throw new RuntimeException("节假日信息缓存出错！", e);
        }
    }

    private static void initCache() throws IOException, URISyntaxException {
        ClassLoader classLoader = LegalHolidaysCache.class.getClassLoader();
        Path resourceFolderPath = Paths.get(Objects.requireNonNull(classLoader.getResource("json")).toURI());
        processFilesInFolder(resourceFolderPath);
    }

    /**
     * 处理文件夹中文件
     *
     * @param folderPath 文件夹路径
     * @throws IOException IOException
     */
    @SuppressWarnings("unchecked")
    private static void processFilesInFolder(Path folderPath) throws IOException {
        Files.walk(folderPath)
                .filter(Files::isRegularFile)
                .filter(p -> p.toString().endsWith(".json"))
                .forEach(p -> {
                    try {
                        String fileName = p.getFileName().toString();
                        byte[] bytes = Files.readAllBytes(p);
                        String content = new String(bytes);
                        ObjectMapper objectMapper = new ObjectMapper();
                        objectMapper.enable(SerializationFeature.INDENT_OUTPUT);
                        if(!content.trim().isEmpty()){
                            Map<String, Object> jsonObject = objectMapper.readValue(content, Map.class);
                            if(jsonObject != null){
                                List<String> holidayList = (List<String>) jsonObject.getOrDefault("legal-holidays", new ArrayList<>());
                                for(String holiday : holidayList){
                                    LEGAL_HOLIDAY_MAP.put(holiday, fileName.replace(".json", ""));
                                }
                                List<String> workList = (List<String>) jsonObject.getOrDefault("legal-works", new ArrayList<>());
                                for(String work : workList){
                                    LEGAL_WORK_MAP.put(work, fileName.replace(".json", ""));
                                }
                                CACHE_YEAR_MAP.put(fileName.replace(".json", ""), fileName.replace(".json", ""));
                            }
                        }
                    } catch (IOException e) {
                        throw new RuntimeException("Error reading file: " + p, e);
                    }
                });
    }

}
