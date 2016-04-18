package com.orgname.projectname.core.utils;

public class ReadableException extends Exception{

    
    public ReadableException(String msg){
        super(msg);
    }
    public ReadableException(String msg,Throwable throwable){
        super(msg,throwable);
    }
    
    private static final long serialVersionUID = 1L;
    
}
