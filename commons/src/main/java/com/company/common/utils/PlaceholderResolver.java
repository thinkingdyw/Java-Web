package com.company.common.utils;

import java.util.Map;
import java.util.Properties;

public interface PlaceholderResolver {
	/**
	 * 解析配置信息
	 * @param config
	 * @return
	 */
	public Map<String, String> resolve(Properties config);
}
