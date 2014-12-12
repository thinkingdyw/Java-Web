package com.common.dictionary;

import java.util.Map;

public interface DictionaryResolver {

	/**
	 * 解析数据字典
	 * 数据字典可能是数据库配置，可能是配置文件指定
	 * @return
	 * @throws DictionaryResolveException
	 */
	public Map<String, Map<String, Object>>  resolve() throws DictionaryResolveException;
}
