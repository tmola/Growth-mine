
## 1. 引入pom依赖
```xml
<!--        easyexcel-->
		<dependency>
			<groupId>com.alibaba</groupId>
			<artifactId>easyexcel</artifactId>
			<version>2.0.0-beta1</version>
		</dependency>
```

## 2. 简单使用

>读
```Java
public static List read(String pathName, int sheet, int row, Class head) {
        return row == 0 ?
                EasyExcel.read(pathName, head, new ExcelListener()).sheet(sheet).doReadSync() :
                EasyExcel.read(pathName, head, new ExcelListener()).sheet(sheet).headRowNumber(row).doReadSync();
    }
```

>写
```Java
public static void write(String fileName, String sheetName, Class head, List list) {
       EasyExcel.write(fileName, head).sheet(sheetName).doWrite(list);
   }
```

>写多sheet
```Java
public static void write(String fileName, String sheetName, Class head, List list, int lineMax) throws IOException {
        int sheetNum = list.size() / lineMax;
        int lastSheetLine = list.size() % lineMax;
        sheetNum += lastSheetLine > 0 ? 1 : 0;
        ExcelWriter excelWriter = EasyExcel.write(fileName, head)/*.registerWriteHandler()*/.build();
        for (int no = 0; no < sheetNum; no++) {
            String sheetNameNo = sheetName + (no + 1);
            WriteSheet writeSheet = EasyExcel.writerSheet(no, sheetNameNo).build();

            excelWriter.write(no < sheetNum - 1 ? list.subList(no * lineMax, no * lineMax + lineMax) : list.subList(no * lineMax, list.size()), writeSheet);
        }
        excelWriter.finish();
        // 分页获取数据？
    }
```

> 更多用法see: https://github.com/alibaba/easyexcel/tree/master/src/test/java/com/alibaba/easyexcel/test/demo

## SheetWriteHandler
