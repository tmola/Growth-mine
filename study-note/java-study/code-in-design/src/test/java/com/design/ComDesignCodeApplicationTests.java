package com.design;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.HashMap;
import java.util.Map;

@SpringBootTest
class ComDesignCodeApplicationTests {

	@Test
	void contextLoads() {
	}

	@Test
	void testMap() {
		Map<String, Object> map = new HashMap<>();

		map.put("121", "asas");

		System.out.println(map.get("xx"));
	}

}
