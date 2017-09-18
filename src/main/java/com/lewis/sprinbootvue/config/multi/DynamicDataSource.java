package com.lewis.sprinbootvue.config.multi;

import com.lewis.sprinbootvue.config.MasterSlaveDataSource;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

import java.util.*;

public class DynamicDataSource extends AbstractRoutingDataSource {


    private List<MasterSlaveDataSource> masterSlaveDataSourceList;


    public DynamicDataSource(List<MasterSlaveDataSource> masterSlaveDataSourceList) {
        this.masterSlaveDataSourceList = masterSlaveDataSourceList;
    }

    @Override
    public void afterPropertiesSet() {
        if (CollectionUtils.isEmpty(masterSlaveDataSourceList)) {
            throw new IllegalArgumentException("Property 'masterSlaveDataSourceList' is required");
        }

        setDefaultTargetDataSource(masterSlaveDataSourceList.get(0).getMasterDs());
        Map<Object, Object> targetDataSources = new HashMap<>();
        for (MasterSlaveDataSource masterSlaveDataSource : masterSlaveDataSourceList) {
            if (masterSlaveDataSource != null) {
                //设置主库
                if (masterSlaveDataSource.getMasterDs() != null) {
                    targetDataSources.put(masterSlaveDataSource.getMasterKey(), masterSlaveDataSource.getMasterDs());
                }
                //设置从库
                Map<String, Object> slaveDsMap = masterSlaveDataSource.getSlaveDsMap();
                if (MapUtils.isNotEmpty(slaveDsMap)) {
                    Iterator<Map.Entry<String, Object>> iterator = slaveDsMap.entrySet().iterator();
                    while (iterator.hasNext()) {
                        Map.Entry<String, Object> slaveDs = iterator.next();
                        targetDataSources.put(slaveDs.getKey(), slaveDs.getValue());
                    }
                }

            }
        }
        setTargetDataSources(targetDataSources);
        super.afterPropertiesSet();
    }

    @Override
    protected Object determineCurrentLookupKey() {

        String key = DynamicDataSourceHolder.getDataSource();
        return key;

    }

    private Object findLookupKey(String key) {
        if (CollectionUtils.isNotEmpty(masterSlaveDataSourceList)) {
            for (MasterSlaveDataSource masterSlaveDataSource : masterSlaveDataSourceList) {
                if (StringUtils.equals(key, masterSlaveDataSource.getMasterKey())) {
                    return masterSlaveDataSource.getMasterKey();
                }
                Set<String> slaveKeys = masterSlaveDataSource.getSlaveDsMap().keySet();
                if (CollectionUtils.isNotEmpty(slaveKeys)) {
                    for (String slaveKey : slaveKeys) {
                        if (StringUtils.equals(key, slaveKey)) {
                            return slaveKey;
                        }
                    }
                }
            }
        }
        return key;
    }

    public List<MasterSlaveDataSource> getMasterSlaveDataSourceList() {
        return masterSlaveDataSourceList;
    }

    public void setMasterSlaveDataSourceList(List<MasterSlaveDataSource> masterSlaveDataSourceList) {
        this.masterSlaveDataSourceList = masterSlaveDataSourceList;
    }
}

