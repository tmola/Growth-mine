package com.code;

import com.code.common.fileUpload.UploadFileConfigure;
import com.code.common.util.excel.ExcelUtil;
import com.code.modules.demo.entity.model.TestExcelModel;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@SpringBootTest
class SomeCodeApplicationTests {

    @Test
    void contextLoads() {
    }

    @Test
    void testWriteExcel() {
        List<TestExcelModel> list = new ArrayList<>();
        TestExcelModel testExcelModel = new TestExcelModel();
        testExcelModel.setNum(1);
        testExcelModel.setEmail("1xxxxxxx");
        list.add(testExcelModel);

        ExcelUtil.write("ts.xlsx", "ts", TestExcelModel.class, list);
    }

    @Test
    void testWriteExcel2() {
        List<TestExcelModel> list = new ArrayList<>();
        for (int i = 1; i <= 24; i++) {
            TestExcelModel testExcelModel = new TestExcelModel();
            testExcelModel.setNum(i);
            testExcelModel.setEmail("1xxxxxxx" + i);
            list.add(testExcelModel);
        }

        int pageSize = 10;
        try {
            for (int page = 0; page * pageSize < list.size(); page++) {
                int begin = page * pageSize;
                int end =  list.size()-page*pageSize > pageSize ? page * pageSize + pageSize : list.size();
                List<TestExcelModel> subList = list.subList(begin, end);
                ExcelUtil.write("ts2.xlsx", "ts", page, TestExcelModel.class, subList);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @Test
    void testReadExcel() {
        List<TestExcelModel> list = ExcelUtil.read("ts.xlsx", 0, 2, TestExcelModel.class);

        for (TestExcelModel t : list) {
            System.out.println(t);
        }
    }
}
