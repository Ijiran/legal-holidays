package top.pxyz.holidays.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import top.pxyz.holidays.cache.LegalHolidaysCache;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * 法定节假日配置
 *
 * @author qulinchao
 * @date 2023/10/07
 */
public class LegalHolidaysConfigExample {

    static {
        try {
//            initFileCache();
//            initRedisCache();
//            initDatabaseCache();
//            initOtherCache();
        } catch (Exception e) {
            throw new RuntimeException("LegalHolidaysConfig 节假日信息缓存出错！", e);
        }
    }

    /**
     * init文件缓存
     */
    public static void initFileCache() throws IOException, URISyntaxException {
        ClassLoader classLoader = LegalHolidaysCache.class.getClassLoader();
        Path resourceFolderPath = Paths.get(Objects.requireNonNull(classLoader.getResource("json")).toURI());
        Files.walk(resourceFolderPath)
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
                                    LegalHolidaysCache.LEGAL_HOLIDAY_MAP.put(holiday, fileName.replace(".json", ""));
                                }
                                List<String> workList = (List<String>) jsonObject.getOrDefault("legal-works", new ArrayList<>());
                                for(String work : workList){
                                    LegalHolidaysCache.LEGAL_WORK_MAP.put(work, fileName.replace(".json", ""));
                                }
                                LegalHolidaysCache.CACHE_YEAR_MAP.put(fileName.replace(".json", ""), fileName.replace(".json", ""));
                            }
                        }
                    } catch (IOException e) {
                        throw new RuntimeException("Error reading file: " + p, e);
                    }
                });
    }

    /**
     * init redis cache
     */
    public static void initRedisCache() {
        // 连接redis

//        LegalHolidaysCache.LEGAL_HOLIDAY_MAP; // 法定假日Map key: yyyy-MM-dd value: yyyy
//        LegalHolidaysCache.LEGAL_WORK_MAP; // 法律工作日Map key: yyyy-MM-dd value: yyyy
//        LegalHolidaysCache.CACHE_YEAR_MAP; // 年份Map key: yyyy value: yyyy
    }

    /**
     * init数据库缓存
     */
    public static void initDatabaseCache() {
        // 连接数据库

//        LegalHolidaysCache.LEGAL_HOLIDAY_MAP; // 法定假日Map key: yyyy-MM-dd value: yyyy
//        LegalHolidaysCache.LEGAL_WORK_MAP; // 法律工作日Map key: yyyy-MM-dd value: yyyy
//        LegalHolidaysCache.CACHE_YEAR_MAP; // 年份Map key: yyyy value: yyyy
    }

    /**
     * 初始化其他缓存
     */
    public static void initOtherCache() {
//        LegalHolidaysCache.LEGAL_HOLIDAY_MAP; // 法定假日Map key: yyyy-MM-dd value: yyyy
//        LegalHolidaysCache.LEGAL_WORK_MAP; // 法律工作日Map key: yyyy-MM-dd value: yyyy
//        LegalHolidaysCache.CACHE_YEAR_MAP; // 年份Map key: yyyy value: yyyy
    }

}
