package com.spring.persistence.common.profiler;

import java.io.Serializable;

/**
 * SQL Profile Data to capture all the data specific to a query
 * <p/>
 * User: AdityaVashisht
 * Date: 4/30/12
 * Time: 4:16 PM
 */
public final class SQLProfileData implements Serializable {

    private final String id;
    private final long duration;
    private final String statement;
    private final Object parameter;

    /**
     * Constructor
     *
     * @param statement
     * @param duration
     * @param id
     */
    public SQLProfileData(String statement, long duration, String id, Object parameter) {
        this.statement = statement;
        this.duration = duration;
        this.id = id;
        this.parameter = parameter;
    }

    /**
     * Getter and setter, immutable types
     */
    public String getStatement() {
        return statement;
    }

    public long getDuration() {
        return duration;
    }

    public String getId() {
        return id;
    }

    public Object getParameter() {
        return parameter;
    }
}
