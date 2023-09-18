package top.pxyz.holidays;

import java.util.List;

/**
 * 法定假日util
 *
 * @author Ijiran
 * @date 2023/09/18
 * @since 1.0
 */
public class LegalHolidaysUtil {

    /**
     * 判断是否为法定假日
     *
     * @param date 日期
     * @return 是否为法定假日
     */
    public static boolean filter(String date) {
        return false;
    }

    /**
     * 判断区间内时间是否存在法定假日
     *
     * @param startDate 开始日期
     * @param endDate   结束日期
     * @return boolean
     */
    public static boolean filter(String startDate, String endDate) {
        return false;
    }

    /**
     * 判断区间内时间存在几个工作日
     *
     * @param startDate 开始日期
     * @param endDate   结束日期
     * @return int
     */
    public static int filterWorkDay(String startDate, String endDate) {
        return 0;
    }

    /**
     * 判断区间内时间存在几个节假日
     *
     * @param startDate 开始日期
     * @param endDate   结束日期
     * @return int
     */
    public static int filterHoliday(String startDate, String endDate) {
        return 0;
    }

    /**
     * 判断区间内时间存在几个法定节假日
     *
     * @param startDate 开始日期
     * @param endDate   结束日期
     * @return int
     */
    public static int filterLegalHoliday(String startDate, String endDate) {
        return 0;
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

}
