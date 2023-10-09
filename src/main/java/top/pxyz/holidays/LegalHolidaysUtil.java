package top.pxyz.holidays;

import top.pxyz.holidays.cache.LegalHolidaysCache;
import top.pxyz.holidays.parse.LegalHolidaysParse;
import top.pxyz.holidays.util.DateUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * 法定假日util
 *
 * @author Ijiran
 * @since 1.0
 */
public class LegalHolidaysUtil {

    /**
     * 日期正则
     */
    private static final String MATCHES = "\\d{4}-\\d{2}-\\d{2}";

    /**
     * 判断是否为法定假日
     *
     * @param date 日期
     * @return 是否为法定假日
     */
    public static boolean filterBoolean(String date) {
        //判断是否是正常日期格式
        if (!date.matches(MATCHES)) {
            return false;
        }
        //获取date中的年份
        String year = date.substring(0, 4);
        if(LegalHolidaysCache.CACHE_YEAR_MAP.containsKey(year)){
            return LegalHolidaysCache.LEGAL_HOLIDAY_MAP.containsKey(date);
        }
        return false;
    }

    /**
     * 判断区间内时间是否存在法定假日
     *
     * @param startDateString 开始日期（yyyy-mm-dd）
     * @param endDateString   结束日期（yyyy-mm-dd）
     * @return boolean
     */
    public static boolean filterBoolean(String startDateString, String endDateString) {
        //增加效验
        if (!startDateString.matches(MATCHES) || !endDateString.matches(MATCHES)) {
            throw new RuntimeException("日期格式错误");
        }
        List<String> dayList = DateUtil.rangeDate(startDateString, endDateString);
        for (String day : dayList) {
            if (LegalHolidaysCache.LEGAL_HOLIDAY_MAP.containsKey(day)) {
                return true;
            }
        }
        return false;
    }

    /**
     * 判断区间内时间的法定节假日
     *
     * @param startDateString 开始日期（yyyy-mm-dd）
     * @param endDateString   结束日期（yyyy-mm-dd）
     * @return boolean
     */
    public static List<String> filterList(String startDateString, String endDateString) {
        List<String> list = new ArrayList<>();
        //增加效验
        if (!startDateString.matches(MATCHES) || !endDateString.matches(MATCHES)) {
            throw new RuntimeException("日期格式错误");
        }
        DateUtil.rangeDate(startDateString, endDateString).forEach(day -> {
            if (LegalHolidaysCache.LEGAL_HOLIDAY_MAP.containsKey(day)) {
                list.add(day);
            }
        });
        return list;
    }

    /**
     * 判断区间内时间存在几个工作日
     *
     * @param startDateString 开始日期
     * @param endDateString   结束日期
     * @return int
     */
    public static int filterWorkDay(String startDateString, String endDateString) {
        //增加效验
        if (!startDateString.matches(MATCHES) || !endDateString.matches(MATCHES)) {
            throw new RuntimeException("日期格式错误");
        }
        List<String> dayList = DateUtil.rangeDate(startDateString, endDateString);
        int workDay = dayList.size();
        for (String day : dayList) {
            boolean isWorkDay = DateUtil.isWeekend(day);
            if (isWorkDay) {
                workDay--;
            }
            if (LegalHolidaysCache.LEGAL_HOLIDAY_MAP.containsKey(day) && !isWorkDay) {
                workDay--;
            }
            if(LegalHolidaysCache.LEGAL_WORK_MAP.containsKey(day) && isWorkDay){
                workDay++;
            }
        }
        return workDay;
    }

    /**
     * 判断区间内时间存在几个节假日
     *
     * @param startDateString 开始日期
     * @param endDateString   结束日期
     * @return int
     */
    public static int filterHoliday(String startDateString, String endDateString) {
        //增加效验
        if (!startDateString.matches(MATCHES) || !endDateString.matches(MATCHES)) {
            throw new RuntimeException("日期格式错误");
        }
        List<String> dayList = DateUtil.rangeDate(startDateString, endDateString);
        int holiday = dayList.size();
        for (String day : dayList) {
            boolean isWorkDay = DateUtil.isWeekend(day);
            if (!isWorkDay) {
                holiday--;
            }
            if (LegalHolidaysCache.LEGAL_HOLIDAY_MAP.containsKey(day) && !isWorkDay) {
                holiday++;
            }
            if(LegalHolidaysCache.LEGAL_WORK_MAP.containsKey(day) && isWorkDay){
                holiday--;
            }
        }
        return holiday;
    }

    /**
     * 判断区间内时间存在几个法定节假日
     *
     * @param startDateString 开始日期
     * @param endDateString   结束日期
     * @return int
     */
    public static int filterLegalHoliday(String startDateString, String endDateString) {
        //增加效验
        if (!startDateString.matches(MATCHES) || !endDateString.matches(MATCHES)) {
            throw new RuntimeException("日期格式错误");
        }
        List<String> dayList = DateUtil.rangeDate(startDateString, endDateString);
        int holiday = dayList.size();
        for (String day : dayList) {
            if (!LegalHolidaysCache.LEGAL_HOLIDAY_MAP.containsKey(day)) {
                holiday--;
            }
        }
        return holiday;
    }

    /**
     * 按年度获得法定节假日
     *
     * @param year 年
     */
    public static List<String> getLegalHolidaysByYear(String year) {

        return null;
    }

    /**
     * 按年度获得节假日
     *
     * @param year 年
     */
    public static List<String> getHolidaysByYear(String year) {
        return null;
    }

    /**
     * 解析文本，获取法定节假日信息JSON
     *
     * @param year 年
     * @param text 文本
     * @return jsonStr
     */
    public static String getLegalHolidaysJson(String year, String text) {
        return LegalHolidaysParse.parseLegalHolidaysJson(year,text);
    }

}
