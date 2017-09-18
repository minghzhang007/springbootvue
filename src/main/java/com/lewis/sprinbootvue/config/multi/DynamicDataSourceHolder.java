package com.lewis.sprinbootvue.config.multi;

public class DynamicDataSourceHolder {
    private static final ThreadLocal<String> holder = new ThreadLocal<String>();

    private DynamicDataSourceHolder() {
        //
    }

    public static void putDataSource(String dataSource) {
        holder.set(dataSource);
    }

    public static String getDataSource() {
        return holder.get();
    }

    public static void clearDataSource() {
        holder.remove();
    }
}
