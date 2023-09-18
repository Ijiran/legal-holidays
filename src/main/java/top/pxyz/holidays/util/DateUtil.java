package top.pxyz.holidays.util;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

/**
 * 日期工具类
 *
 * @author Ijiran
 * @date 2023/09/18
 */
public class DateUtil {

    /**
     * 日期格式y
     */
    public static final DateTimeFormatter DATE_FORMAT_Y = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    /**
     * 解析日期
     *
     * @param date   日期
     * @param format 总体安排
     * @return 一串
     */
    public static String parseDate(String date, DateTimeFormatter format){
        if(format == null){
            format = DATE_FORMAT_Y;
        }
        return parse(date, format);
    }

    /**
     * 解析日期
     *
     * @param date   日期
     * @return 一串
     */
    public static String parseDate(String date){
        return parse(date, DATE_FORMAT_Y);
    }

    /**
     * 解析日期
     *
     * @param date 日期
     * @return 一串
     */
    private static String parse(String date, DateTimeFormatter format){
        return LocalDate.parse(formatDateString(date), format).format(format);
    }

    /**
     * 范围
     *
     * @param startDate 开始日期
     * @param endDate   结束日期
     * @return 列表＜string＞
     */
    public static List<String> rangeDate(String startDate, String endDate) {
        return range(startDate, endDate, DATE_FORMAT_Y);
    }

    /**
     * 范围
     *
     * @param startDate 开始日期
     * @param endDate   结束日期
     * @param format    格式
     * @return 列表＜string＞
     */
    public static List<String> rangeDate(String startDate, String endDate, DateTimeFormatter format) {
        if (format == null){
            format = DATE_FORMAT_Y;
        }
        return range(startDate, endDate, format);
    }

    /**
     * 范围
     *
     * @param startDateString 开始日期
     * @param endDateString   结束日期
     * @param formatter    格式
     * @return 列表＜string＞
     */
    private static List<String> range(String startDateString, String endDateString, DateTimeFormatter formatter) {
        LocalDate startDate = LocalDate.parse(formatDateString(startDateString), formatter);
        LocalDate endDate = LocalDate.parse(formatDateString(endDateString), formatter);
        List<String> dateRange = new ArrayList<>();
        // 获取日期区间内的所有日期
        LocalDate currentDate = startDate;
        while (!currentDate.isAfter(endDate)) {
            dateRange.add(currentDate.format(formatter));
            currentDate = currentDate.plusDays(1);
        }
        return dateRange;
    }

    /**
     * 格式化日期字符串
     *
     * @param dateString 日期字符串
     * @return 一串
     */
    private static String formatDateString(String dateString){
        DateTimeFormatter inputFormatter = DateTimeFormatter.ofPattern("yyyy-M-d");
        LocalDate date = LocalDate.parse(dateString, inputFormatter);
        DateTimeFormatter outputFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        return date.format(outputFormatter);
    }

}
