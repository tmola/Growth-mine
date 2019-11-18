package com.code;

import com.code.common.fileUpload.UploadFileConfigure;
import com.code.common.properties.AppProperties;
import com.code.common.util.excel.ExcelUtil;
import com.code.modules.demo.entity.model.TestExcelModel;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@SpringBootTest
class SomeCodeApplicationTests {

    @Value("${app.const.version}")
    private String version;

    @Autowired
    private AppProperties properties;

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
    void testReadExcel() {
        List<TestExcelModel> list = ExcelUtil.read("ts.xlsx", 0, 2, TestExcelModel.class);

        for (TestExcelModel t : list) {
            System.out.println(t);
        }
    }

    @Test
    void testFile() {
        String file = ExcelUtil.createExcelFile();
        System.out.println(file);
        System.out.println(ExcelUtil.deleteFile(file));
    }

    @Test
    void testProperties() {
        System.out.println(properties.getVersion());
        System.out.println(version);
    }
}
