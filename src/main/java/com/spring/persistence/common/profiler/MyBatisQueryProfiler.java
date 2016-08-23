/**
 * All Rights Reserved
 */
package com.spring.persistence.common.profiler;

import org.apache.ibatis.executor.Executor;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.plugin.*;
import org.apache.ibatis.session.ResultHandler;
import org.apache.ibatis.session.RowBounds;


import java.util.Properties;


/**
 * <p>MyBatisQueryProfiler.java</p>
 * <p>This class is used to collect SQL profile data, per thread request, if enabled.
 * The collected information is passed to the web tier via the @see ProfilerContext
 * The @Intercepts annotation definition is simply a match on
 * 1.) The "type" to intercept, all invocations are via SimpleExectuor, therefore Executor.class
 * 2.) The "method" to intercept, in this case "query"
 * 3.) The "args", arguments passed to the method, for manipulation,extraction as needed.
 * </p>
 *
 * @author Aditya Vashisht
 * @since 05.02.2011
 */
@Intercepts({@Signature(type = Executor.class,
        method = "query",
        args = {MappedStatement.class, Object.class, RowBounds.class, ResultHandler.class}),

        @Signature(type = Executor.class,
                method = "update",
                args = {MappedStatement.class, Object.class})})
public class MyBatisQueryProfiler implements Interceptor {



    static int MAPPED_STATEMENT_INDEX = 0;
    static int PARAMETER_INDEX = 1;

    /**
     * Intercept the query invocation and check for trace enable
     * If trace enabled, collect profile data and associate with profileContext
     */

    public Object intercept(Invocation invocation) throws Throwable {
        ProfilerContext profilerContext = ThreadLocalProfilerContext.getProfiler();
        // Is trace enabled?
        if (profilerContext != null && profilerContext.isEnableTrace()) {
            profilerContext.extractAndStore(invocation.getArgs());
        }
        final Object[] queryArgs = invocation.getArgs();
        MappedStatement statement = (MappedStatement) queryArgs[MAPPED_STATEMENT_INDEX];
        Object parameter = queryArgs[PARAMETER_INDEX];
        // Extract the SQL
        BoundSql boundSql = statement.getBoundSql(parameter);
        //logger.info("MYBATIS - SQL ->\n " + statement.getId() + ":\n" + boundSql.getSql() + "\n");
        return invocation.proceed();
    }


    public Object plugin(Object o) {
        return Plugin.wrap(o, this);
    }


    public void setProperties(Properties properties) {
        // Do nothing
    }

}
