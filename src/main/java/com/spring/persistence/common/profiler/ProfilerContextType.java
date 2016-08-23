package com.spring.persistence.common.profiler;

/**
 * Created with IntelliJ IDEA.
 * User: AdityaVashisht
 * Date: 5/7/12
 * Time: 3:22 PM
 * To change this template use File | Settings | File Templates.
 */
public enum ProfilerContextType {

    NORMAL("NORMAL"), AJAX("AJAX");

    private String value;

    ProfilerContextType(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public static ProfilerContextType getTypeByValue(String value) {
        return ProfilerContextType.valueOf(value);
    }
}
