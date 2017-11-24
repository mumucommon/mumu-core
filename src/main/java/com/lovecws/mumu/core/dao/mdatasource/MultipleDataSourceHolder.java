package com.lovecws.mumu.core.dao.mdatasource;

public class MultipleDataSourceHolder {

    public static final String READ = "read";
    public static final String WRITE = "write";

    private static final ThreadLocal<String> THREAD_DATA_SOURCE = new ThreadLocal<String>() {
        @Override
        protected String initialValue() {
            return WRITE;
        }
    };

    public static String getDataSource() {
        return THREAD_DATA_SOURCE.get();
    }

    public static void setDataSource(String dataSource) {
        THREAD_DATA_SOURCE.set(dataSource);
    }

    public static void clearDataSource() {
        THREAD_DATA_SOURCE.remove();
    }

}