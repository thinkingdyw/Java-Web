package com.orgname.projectname.core.utils;

public class HttpInvokeResult<DATA,ERROR> implements Result<DATA, ERROR>{

    protected enum Status{
        SUCCESS(1,"SUCCESS"),
        CLIENT_ERROR(2,"客户端错误"),
        SERVER_ERROR(3,"服务端错误");
        private final int code;
        private final String desc;
        private Status(int code,String desc) {
            this.code = code;
            this.desc = desc;
        }
        public int getCode() {
            return code;
        }
        public String getDesc() {
            return desc;
        }
        
    };
    
    private Status status;
    private DATA data;
    private ERROR error;
    
    @Override
    public boolean hasError() {
        return status!=Status.SUCCESS;
    }

    @Override
    public DATA getData() {
        return data;
    }

    @Override
    public ERROR getError() {
        return error;
    }
    public void clientFail(ERROR error){
        this.error = error;
        this.status = Status.CLIENT_ERROR;
    }
    public void serverFail(ERROR error){
        this.error = error;
        this.status = Status.SERVER_ERROR;
    }
}
