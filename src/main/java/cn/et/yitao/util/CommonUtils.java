package cn.et.yitao.util;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.ConvertUtils;

import java.util.Date;
import java.util.Map;
import java.util.UUID;

public class CommonUtils {
	/**
	 * 返回一个不重复的字符串
	 * 
	 * @return
	 */
	public static String uuid() {
		return UUID.randomUUID().toString().replace("-", "").toUpperCase();
	}

	@SuppressWarnings("rawtypes")
	public static <T> T toBean(Map map, Class<T> clzz) {
		try {
			T bean = clzz.newInstance();
			ConvertUtils.register(new DateConverter(), Date.class);
			BeanUtils.populate(bean, map);
			return bean;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	public static <T> Map toMap(T t) {
		try {
			Map describe = BeanUtils.describe(t);
			return describe;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}
