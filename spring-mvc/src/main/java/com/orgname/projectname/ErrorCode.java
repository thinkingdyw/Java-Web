package com.orgname.projectname;

import java.text.MessageFormat;

public enum ErrorCode {

    E0000(""),
    E0001("Could not create runtime directory, {0}");
    
    private String template;
    
    private ErrorCode(String template){
        this.template=template;
    }
    public String format(Object ... params){
       return MessageFormat.format(template, params);
    }
}
