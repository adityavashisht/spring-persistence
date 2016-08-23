package com.spring.persistence.common.profiler;

/**
 * Created with IntelliJ IDEA.
 * User: AdityaVashisht
 * Date: 4/30/12
 * Time: 4:13 PM
 * To change this template use File | Settings | File Templates.
 */
public class ThreadLocalProfilerContext {

    private static final ThreadLocal<ProfilerContext> profilerContextThreadLocal = new ThreadLocal<ProfilerContext>();

    public static void bindProfiler(ProfilerContext profilerContext) {
        profilerContextThreadLocal.set(profilerContext);
    }

    public static void unBindProfiler() {
        getProfiler().getLogTraceMap().clear();
        profilerContextThreadLocal.set(null);
    }

    public static ProfilerContext getProfiler() {
        return profilerContextThreadLocal.get();
    }

}
