package com.lewis.sprinbootvue.config;

import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

import java.util.HashMap;
import java.util.Map;

public class DynamicDataSource extends AbstractRoutingDataSource {
    private Object writeDataSource; //写数据源

    private Object readDataSource; //读数据源

    private Object otherDataSource;//其他数据源


    public DynamicDataSource(Object writeDataSource, Object readDataSource, Object otherDataSource) {
        this.writeDataSource = writeDataSource;
        this.readDataSource = readDataSource;
        this.otherDataSource = otherDataSource;
    }


    @Override
    public void afterPropertiesSet() {
        if (writeDataSource == null) {
            throw new IllegalArgumentException("Property 'writeDataSource' is required");
        }

        setDefaultTargetDataSource(writeDataSource);
        Map<Object, Object> targetDataSources = new HashMap<>();


        targetDataSources.put(DynamicDataSourceGlobal.WRITE.name(), writeDataSource);
        if (readDataSource != null) {
            targetDataSources.put(DynamicDataSourceGlobal.READ.name(), readDataSource);
        }
        if (otherDataSource != null) {
            targetDataSources.put(DynamicDataSourceGlobal.OTHER.name(), otherDataSource);
        }
        setTargetDataSources(targetDataSources);

        super.afterPropertiesSet();
    }


    @Override
    protected Object determineCurrentLookupKey() {

        DynamicDataSourceGlobal dynamicDataSourceGlobal = DynamicDataSourceHolder.getDataSource();

        if (dynamicDataSourceGlobal == null
                || dynamicDataSourceGlobal == DynamicDataSourceGlobal.WRITE) {
            return DynamicDataSourceGlobal.WRITE.name();
        } else if (dynamicDataSourceGlobal == DynamicDataSourceGlobal.OTHER) {
            return DynamicDataSourceGlobal.OTHER.name();
        }

        return DynamicDataSourceGlobal.READ.name();
    }

    public Object getWriteDataSource() {
        return writeDataSource;
    }

    public void setWriteDataSource(Object writeDataSource) {
        this.writeDataSource = writeDataSource;
    }

    public Object getReadDataSource() {
        return readDataSource;
    }

    public void setReadDataSource(Object readDataSource) {
        this.readDataSource = readDataSource;
    }

    public Object getOtherDataSource() {
        return otherDataSource;
    }

    public void setOtherDataSource(Object otherDataSource) {
        this.otherDataSource = otherDataSource;
    }
}

