package com.orgname.projectname.core.utils;

public interface Result<DATA,ERROR> {
    /**
     * 是否失败
     * @return
     */
    public boolean hasError();
    
    /**
     * @return 数据域
     */
    public DATA getData();
    /**
     * @param 
     * @return 错误消息
     */
    public ERROR getError();
    
}