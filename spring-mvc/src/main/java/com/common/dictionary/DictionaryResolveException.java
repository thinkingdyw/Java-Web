package com.common.dictionary;
/**
 * 数据字典解析异常
 * @author diaoyouwei
 *
 */
public class DictionaryResolveException extends Exception{

	private static final long serialVersionUID = 1L;
	
	public DictionaryResolveException(String msg,Throwable e){
		super(msg,e);
	}
}
