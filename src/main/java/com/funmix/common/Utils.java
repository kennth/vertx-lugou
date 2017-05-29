package com.funmix.common;

import java.util.UUID;

public class Utils {
	public static String genToken(){
		return UUID.randomUUID().toString().replace("-", "");
	}
	
	public static int calcPage(int page, int limit) {
		if (page <= 0)
			return 0;
		return limit * (page - 1);
	}
}
