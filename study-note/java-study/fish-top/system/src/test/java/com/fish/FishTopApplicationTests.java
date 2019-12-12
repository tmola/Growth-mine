package com.fish;


import fish.util.*;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;


@SpringBootTest
class FishTopApplicationTests {

	@Test
	void contextLoads() throws Exception {
		System.out.println(IDUtil.baseUID());
		System.out.println(StringUtil.toDouble("121.23333", 3));
		System.out.println(StringUtil.lowerFirstUpperOther("HhHs"));
	}


}
