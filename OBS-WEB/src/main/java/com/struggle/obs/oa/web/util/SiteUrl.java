package com.struggle.obs.oa.web.util;

import java.io.IOException;
import java.util.Properties;


/**
 * 读取配置文件
 * @author tangyh
 *
 */
public class SiteUrl {
	private static Properties properties = new Properties();
	static{
		try {
			properties.load(SiteUrl.class.getClassLoader().getResourceAsStream("siteurl.properties"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 读取配置文件中的地址
	 * @param key 地址key
	 * @return
	 */
	public static String readUrl(String key){		
		return (String)properties.get(key);
	}
}
