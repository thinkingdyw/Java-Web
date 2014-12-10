package com.common;

import java.util.Collections;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.apache.commons.lang.StringUtils;

import com.common.DictionaryResolver.DictionaryResolveException;

public class DataDictionaryConfiguration {
	private String DEFAULT_DIC_FILE = "dictionary.xml";
	private String dicFile;
	private String root;
	
	private Map<String, Map<String, Map<String, Object>>> dicCache = new ConcurrentHashMap<String, Map<String, Map<String, Object>>>();
	
	private DictionaryResolver resolver = new DictionaryResolver();
	
	public Map<String, Object> get(String indexName){
		if(dicCache == null){
			return Collections.emptyMap();
		}
		return dicCache.get(root).get(indexName);
	}
	public Map<String, Map<String, Object>> get(){
		if(dicCache == null){
			return Collections.emptyMap();
		}
		return dicCache.get(root);
	}
	public void init() throws DictionaryResolveException{
		Map<String, Map<String, Object>> dic = resolver.resovle(getDicFile());
		root = resolver.getRoot();
		dicCache.put(root, dic);
		
	}
	
	public String getDicFile() {
		return StringUtils.isNotBlank(dicFile)?dicFile:DEFAULT_DIC_FILE;
	}

	public void setDicFile(String dicFile) {
		this.dicFile = dicFile;
	}
	public String getRoot() {
		return root;
	}
	
	public static void main(String[] args) {
		System.out.println("000000727954".length());
	}
}
