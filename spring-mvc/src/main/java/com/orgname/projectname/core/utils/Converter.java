package com.orgname.projectname.core.utils;

public interface Converter<S,T> {

    public T convert(S s) throws Exception;
}
