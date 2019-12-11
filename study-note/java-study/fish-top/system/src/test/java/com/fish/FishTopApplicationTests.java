package com.fish;

import fish.unit.response.ResponseCode;
import fish.unit.response.ResponseVO;
import fish.util.*;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;


@SpringBootTest
class FishTopApplicationTests {

	@Test
	void contextLoads() throws Exception {
		System.out.println(IDUtil.baseUID());
		System.out.println(StringUtil.toDouble("121.23333", 3));
		System.out.println(StringUtil.lowerFirstUpperOther("HhHs"));
	}

}
