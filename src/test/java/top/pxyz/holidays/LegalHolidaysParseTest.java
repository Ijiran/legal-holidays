package top.pxyz.holidays;

import org.junit.Test;
import top.pxyz.holidays.parse.LegalHolidaysParse;

public class LegalHolidaysParseTest {

    @Test
    public void testParse() {
        String s = LegalHolidaysParse.parseLegalHolidaysJson("2023", "一、元旦：2022年12月31日至2023年1月2日放假调休，共3天。\n" +
                "\n" +
                "二、春节：1月21日至27日放假调休，共7天。1月28日（星期六）、1月29日（星期日）上班。\n" +
                "\n" +
                "三、清明节：4月5日放假，共1天。\n" +
                "\n" +
                "四、劳动节：4月29日至5月3日放假调休，共5天。4月23日（星期日）、5月6日（星期六）上班。\n" +
                "\n" +
                "五、端午节：6月22日至24日放假调休，共3天。6月25日（星期日）上班。\n" +
                "\n" +
                "六、中秋节、国庆节：9月29日至10月6日放假调休，共8天。10月7日（星期六）、10月8日（星期日）上班。");
        System.out.println(s);
    }

}
