package top.pxyz.holidays;

import org.junit.Test;

/**
 * 法定节假日测试
 *
 * @author Ijiran
 * @date 2023/09/18
 */
public class LegalHolidaysTest {

    @Test
    public void testFilterBoolean() {
        System.out.println(LegalHolidaysUtil.filterBoolean("2023-09-18"));
        System.out.println(LegalHolidaysUtil.filterBoolean("2023-09-28"));
        System.out.println(LegalHolidaysUtil.filterBoolean("2023-09-29"));

        System.out.println(LegalHolidaysUtil.filterBoolean("2023-09-18", "2023-09-28"));
        System.out.println(LegalHolidaysUtil.filterBoolean("2023-09-18", "2023-09-30"));
    }

    @Test
    public void testFilterWorkDay() {
        System.out.println(LegalHolidaysUtil.filterWorkDay("2023-09-18", "2023-09-30"));
    }

    @Test
    public void testFilterHoliday() {
        System.out.println(LegalHolidaysUtil.filterHoliday("2023-09-18", "2023-09-30"));
    }

    @Test
    public void testFilterLegalHoliday() {
        System.out.println(LegalHolidaysUtil.filterLegalHoliday("2023-09-18", "2023-09-30"));
    }

    @Test
    public void testFilterList() {
        System.out.println(LegalHolidaysUtil.filterList("2023-09-18", "2023-09-30"));
    }

}
