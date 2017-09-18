package com.lewis.sprinbootvue.config;

import java.util.List;

public class MasterSlaveDataSource {

    /**
     * 主库 写数据源
     */
    private Object masterDataSource;

    /**
     * 从库 读数据源列表
     */
    private List<Object> slaveDataSources;

    public MasterSlaveDataSource(Object masterDataSource, List<Object> slaveDataSources) {
        this.masterDataSource = masterDataSource;
        this.slaveDataSources = slaveDataSources;
    }

    public Object getMasterDataSource() {
        return masterDataSource;
    }

    public void setMasterDataSource(Object masterDataSource) {
        this.masterDataSource = masterDataSource;
    }

    public List<Object> getSlaveDataSources() {
        return slaveDataSources;
    }

    public void setSlaveDataSources(List<Object> slaveDataSources) {
        this.slaveDataSources = slaveDataSources;
    }

    @Override
    public String toString() {
        return "MasterSlaveDataSource{" +
                "masterDataSource=" + masterDataSource +
                ", slaveDataSources=" + slaveDataSources +
                '}';
    }
}
