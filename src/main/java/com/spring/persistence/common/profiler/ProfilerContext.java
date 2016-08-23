package com.spring.persistence.common.profiler;

import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.MappedStatement;

import java.util.concurrent.ConcurrentHashMap;

/**
 * Created with IntelliJ IDEA.
 * User: AdityaVashisht
 * Date: 4/30/12
 * Time: 4:13 PM
 * To change this template use File | Settings | File Templates.
 */
public final class ProfilerContext {

    static int MAPPED_STATEMENT_INDEX = 0;
    static int PARAMETER_INDEX = 1;
    /**
     * Thread id, Map<SQLProfileData>
     */
    private static final ConcurrentHashMap<String, SQLProfileData> logTraceMap = new ConcurrentHashMap<String, SQLProfileData>();
    private final boolean enableTrace;
    private ProfilerContextType profilerContextType;
    private String content;

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    /**
     * @param enableTrace
     * @param profilerContextType
     */
    public ProfilerContext(boolean enableTrace, ProfilerContextType profilerContextType) {
        this.enableTrace = enableTrace;
        this.profilerContextType = profilerContextType;
    }

    /**
     * @return
     */
    public ConcurrentHashMap<String, SQLProfileData> getLogTraceMap() {
        return logTraceMap;
    }

    /**
     * @return
     */
    public boolean isEnableTrace() {
        return enableTrace;
    }

    /**
     * @return
     */
    public String getProfilerContextType() {
        return profilerContextType.getValue();
    }

    /**
     * @param queryArgs
     */
    public void extractAndStore(final Object[] queryArgs) {
        synchronized (logTraceMap) {
            // Extract statement and parameters
            MappedStatement statement = (MappedStatement) queryArgs[MAPPED_STATEMENT_INDEX];
            Object parameter = queryArgs[PARAMETER_INDEX];
            // Extract the SQL
            BoundSql boundSql = statement.getBoundSql(parameter);
            // Check if statement already keyed to a map-entry (most likely not, but check anyway)
            // TODO : What if the keyed query has already been performed and is being executed a second time?
            //        Write an equals()/hashcode() on SQLProfileData, that will compare SQL id
            if (statement != null && statement.getId() != null && this.getLogTraceMap() != null && this.getLogTraceMap().get(statement.getId()) == null) {
                //TODO: Get the time evaluation after query execution, not sure if feasible here?
                SQLProfileData sqlProfile = new SQLProfileData(boundSql.getSql(), 1000, statement.getId(), parameter);
                this.getLogTraceMap().put(statement.getId(), sqlProfile);
            }
        }
    }


    @Override
    public String toString() {
        return "ProfilerContext{" +
                "profilerContextType=" + profilerContextType + "-->DATA : " + getPrintableForm() +
                '}';
    }

    /**
     * Print a human readable stack of profiled data
     *
     * @return
     */
    public String getPrintableForm() {
        StringBuilder builder = new StringBuilder();
        for (SQLProfileData profileData : logTraceMap.values()) {
            builder.append("<br/>/** Begin :" + profileData.getId() + " - QUERY BEGIN")
                    .append("\n\n<br/>")
                    .append(profileData.getStatement()).append("\n").append(";PARAMETERS :").append(profileData.getParameter()).append("\n\n<br/><br/>")
                    .append("END :" + profileData.getId() + "- QUERY END \n*/").append("\n\n<br/><br/>");
        }
        return builder.toString();
    }
}
