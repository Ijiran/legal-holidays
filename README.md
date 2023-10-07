# legal-holidays

法定节假日

## 📚简介

本项目是一个法定节假日的查询工具，可以查询指定年份的法定节假日，也可以查询指定日期是否为法定节假日。

### 目的

本项目的目的是为了方便开发者在开发中使用，比如在开发中需要根据节假日来调整业务逻辑，或者在开发中需要根据节假日来调整工作日的安排等等。

## 📝文档

#### 📅判断是否为法定假日

```java
boolean isLegalHoliday = LegalHolidaysUtil.filterBoolean("2021-10-01");
```

#### 📅判断区间内时间是否存在法定假日

```java
boolean isLegalHoliday = LegalHolidaysUtil.filterBoolean("2021-10-01", "2021-10-09");
```

#### 📅判断区间内时间的法定节假日

```java
List<String> legalHolidayList = LegalHolidaysUtil.filterList("2021-10-01", "2021-10-09");
```

#### 📅判断区间内时间存在几个工作日

```java
int workDayNum = LegalHolidaysUtil.filterWorkDay("2021-10-01", "2021-10-09");
```

#### 📅判断区间内时间存在几个节假日

```java
int holidayNum = LegalHolidaysUtil.filterHoliday("2021-10-01", "2021-10-09");
```

#### 📅判断区间内时间存在几个法定节假日

```java
int legalHolidayNum = LegalHolidaysUtil.filterLegalHoliday("2021-10-01", "2021-10-09");
```

#### 📅解析文本，获取法定节假日信息JSON

```java
String legalHolidaysJson = LegalHolidaysUtil.getLegalHolidaysJson("2023", "一、元旦：2022年12月31日至2023年1月2日放假调休，共3天。\n" +
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
```

### 数据来源

[国务院办公厅关于2010年部分节假日安排的通知](https://www.gov.cn/gongbao/content/2009/content_1487011.htm)

[国务院办公厅关于2011年部分节假日安排的通知](https://www.gov.cn/gongbao/content/2010/content_1765282.htm)

[国务院办公厅关于2012年部分节假日安排的通知](https://www.gov.cn/gongbao/content/2011/content_2020918.htm)

[国务院办公厅关于2013年部分节假日安排的通知](https://www.gov.cn/gongbao/content/2012/content_2292057.htm)

[国务院办公厅关于2014年部分节假日安排的通知](https://www.gov.cn/gongbao/content/2014/content_2561299.htm)

[国务院办公厅关于2015年部分节假日安排的通知](https://www.gov.cn/gongbao/content/2015/content_2799019.htm)

[国务院办公厅关于2016年部分节假日安排的通知](https://www.gov.cn/gongbao/content/2016/content_2979719.htm)

[国务院办公厅关于2017年部分节假日安排的通知](https://www.gov.cn/gongbao/content/2016/content_5148793.htm)

[国务院办公厅关于2018年部分节假日安排的通知](https://www.gov.cn/gongbao/content/2017/content_5248221.htm)

[国务院办公厅关于2019年部分节假日安排的通知](https://www.gov.cn/gongbao/content/2018/content_5350046.htm)

[国务院办公厅关于2020年部分节假日安排的通知](https://www.gov.cn/gongbao/content/2019/content_5459138.htm)

[国务院办公厅关于2021年部分节假日安排的通知](https://www.gov.cn/gongbao/content/2020/content_5567750.htm)

[国务院办公厅关于2022年部分节假日安排的通知](https://www.gov.cn/gongbao/content/2021/content_5651728.htm)

[国务院办公厅关于2023年部分节假日安排的通知](https://www.gov.cn/gongbao/content/2023/content_5736714.htm)

后期持续更新中...

## 📦安装

### 🍊Maven

在项目的pom.xml的dependencies中加入以下内容:

```xml
<dependency>
    <groupId>top.pxyz</groupId>
    <artifactId>legal-holidays</artifactId>
    <version>1.0</version>
</dependency>
```

### 🍐Gradle
```
implementation 'top.pxyz:legal-holidays:1.0'
```

### 🍎自定义节假日配置文件

因为节假日的安排是不固定的，所以本项目提供了自定义节假日配置文件的方式，方便开发者自行配置节假日。

#### 例子
    
[LegalHolidaysConfigExample.java](src%2Fmain%2Fjava%2Ftop%2Fpxyz%2Fholidays%2Fconfig%2FLegalHolidaysConfigExample.java)

#### 如何获取到节假日配置信息

1. 国务院发布的最新节假日信息
2. 通过爬虫获取到的节假日信息
3. 通过其他方式获取到的节假日信息
4. 通过自己编写的程序生成的节假日信息
5. 等等

#### 可能会使用到的节假日内置工具方法

[LegalHolidaysParse.java](src%2Fmain%2Fjava%2Ftop%2Fpxyz%2Fholidays%2Fparse%2FLegalHolidaysParse.java)

[LegalHolidaysParseTest.java](src%2Ftest%2Fjava%2Ftop%2Fpxyz%2Fholidays%2FLegalHolidaysParseTest.java)

### 📥下载jar

点击以下链接，下载`legal-holidays-X.X.X.jar`即可：

- [Maven中央库](https://repo1.maven.org/maven2/top/pxyz/legal-holidays/1.0/)

## 🏗️添砖加瓦

### 🐞提供bug反馈或建议

随意提吧，没啥要求。

- [Gitee issue](https://gitee.com/Ijiran/legal-holidays/issues)
- [Github issue](https://github.com/Ijiran/legal-holidays/issues)

### 下一步计划

- [ ] 优化代码
- [ ] 提供在线、离线更新数据源的方式
- [ ] 增加更多的文档
- [ ] 增加更多的数据来源
- [ ] 增加更多的功能


## ⭐Star legal-holidays

如果你觉得这个项目还不错的话，就给它一个Star吧，你的支持是我最大的动力！