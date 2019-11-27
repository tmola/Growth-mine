package com.sbot;

import com.sbot.common.utils.ToolUtil;
import com.sbot.modules.system.entity.SysUser;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.tools.Tool;
import java.lang.reflect.InvocationTargetException;
import java.util.Date;

@SpringBootTest
class SbotApplicationTests {

	@Test
	void contextLoads() {

		SysUser user = new SysUser();

		try {
			ToolUtil.setFieldValueByFieldName(user, "id", "qwe");
			System.out.println(user);
		} catch (NoSuchFieldException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}
		try {
			ToolUtil.setFieldValueBySetMethod(user, "borndate", new Date());
			System.out.println(user);
		} catch (NoSuchFieldException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}
	}

}
