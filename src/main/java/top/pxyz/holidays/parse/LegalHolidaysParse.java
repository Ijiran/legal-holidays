package top.pxyz.holidays.parse;

import top.pxyz.holidays.util.DateUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

/**
 * 法定节假日解析
 *
 * @author Ijiran
 * @date 2023/09/18
 * @since 1.0
 */
public class LegalHolidaysParse {

    private static final Pattern HOLIDAY_PATTERN = Pattern.compile("(\\d{4}年\\d{1,2}月\\d{1,2}日至\\d{4}年\\d{1,2}月\\d{1,2}日放假调休)");

    private static final Pattern HOLIDAY_PATTERN1 = Pattern.compile("(\\d{1,2}月\\d{1,2}日放假，)");

    private static final Pattern HOLIDAY_PATTERN2 = Pattern.compile("(\\d{1,2}月\\d{1,2}日至\\d{1,2}月\\d{1,2}日放假调休)");

    private static final Pattern WORKDAY_PATTERN = Pattern.compile("(\\d{1,2}月\\d{1,2}日（星期[一二三四五六日]）上班)");

    /**
     * 解析法定假日json
     *
     * @param year 年
     * @param text 文本
     * @return 一串
     */
    public static String parseLegalHolidaysJson(String year, String text) {
        List<String> holidayList = new ArrayList<>();
        List<String> workdayList = new ArrayList<>();
        // 提取放假日期
        Matcher holidayMatcher = HOLIDAY_PATTERN.matcher(text);
        while (holidayMatcher.find()) {
            String holidayStr = holidayMatcher.group(1);
            String[] holidayArr = holidayStr.split("至");
            String startStr = holidayArr[0].replace("年", "-").replace("月", "-").replace("日", "");
            String endStr = holidayArr[1].replace("年", "-").replace("月", "-").replace("日放假", "").replace("调休", "");
            parseBetweenYear(holidayList, startStr, endStr);
        }
        // 提取放假日期
        Matcher holidayMatcher1 = HOLIDAY_PATTERN1.matcher(text);
        while (holidayMatcher1.find()) {
            String holidayStr = holidayMatcher1.group(1);
            String dateStr = year + "-" + holidayStr.replace("月", "-").replace("日", "").replace("放假，", "");
            holidayList.add(DateUtil.parseDate(dateStr));
        }
        // 提取放假日期
        Matcher holidayMatcher2 = HOLIDAY_PATTERN2.matcher(text);
        while (holidayMatcher2.find()) {
            String holidayStr = holidayMatcher2.group(1);
            System.out.println(holidayStr);
            String[] holidayArr = holidayStr.split("至");
            String startStr = year + "-" + holidayArr[0].replace("月", "-").replace("日", "");
            String endStr = year + "-" + holidayArr[1].replace("月", "-").replace("日放假", "").replace("调休", "");
            parseBetweenYear(holidayList, startStr, endStr);
        }
        // 提取上班日期
        Matcher workdayMatcher = WORKDAY_PATTERN.matcher(text);
        while (workdayMatcher.find()) {
            String workdayStr = workdayMatcher.group(1);
            String dateStr = year + "-" + workdayStr.replace("月", "-").replace("日", "").substring(0, workdayStr.indexOf("（") - 1);
            workdayList.add(DateUtil.parseDate(dateStr));
        }
        holidayList = holidayList.stream().sorted().collect(Collectors.toList());
        // 构造JSON串
        StringBuilder jsonBuilder = new StringBuilder();
        jsonBuilder.append("{\"legal-holidays\": [");
        appendJson(holidayList, jsonBuilder);
        jsonBuilder.append("],\"legal-works\": [");
        appendJson(workdayList, jsonBuilder);
        jsonBuilder.append("]}");
        return jsonBuilder.toString();
    }

    private static void parseBetweenYear(List<String> dateList, String startStr, String endStr) {
        List<String> dayList = DateUtil.rangeDate(startStr, endStr);
        dateList.addAll(dayList);
    }

    /**
     * 附加json
     *
     * @param dayList     日期列表
     * @param jsonBuilder json生成器
     */
    private static void appendJson(List<String> dayList, StringBuilder jsonBuilder) {
        for (int i = 0; i < dayList.size(); i++) {
            String dateStr = dayList.get(i);
            jsonBuilder.append("\"").append(dateStr).append("\"");
            if (i != dayList.size() - 1) {
                jsonBuilder.append(",");
            }
        }
    }

}
