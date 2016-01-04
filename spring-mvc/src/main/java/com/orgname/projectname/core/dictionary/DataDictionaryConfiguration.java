package com.orgname.projectname.core.dictionary;

import java.util.Collections;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public final class DataDictionaryConfiguration {
    private String root;
    private Map<String, Map<String, Map<String, Object>>> dicCache = new ConcurrentHashMap<String, Map<String, Map<String, Object>>>();
    
    private DictionaryResolver resolver;
    
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
        Map<String, Map<String, Object>> dic = resolver.resolve();
        dicCache.put(root, dic);
        
    }
    public String getRoot() {
        return root;
    }
    public void setRoot(String root) {
        this.root = root;
    }
    public DictionaryResolver getResolver() {
        return resolver;
    }
    public void setResolver(DictionaryResolver resolver) {
        this.resolver = resolver;
    }
    
}