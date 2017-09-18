package com.lewis.sprinbootvue.config;

import java.util.Map;

public class MasterSlaveDataSource {

    /**
     * 主库 写数据源
     */
    private Object masterDs;

    private String masterKey;

    /**
     * 从库 读数据源列表 key为从库的key  value为从库的数据源
     */
    private Map<String, Object> slaveDsMap;

    public Object getMasterDs() {
        return masterDs;
    }

    public void setMasterDs(Object masterDs) {
        this.masterDs = masterDs;
    }

    public String getMasterKey() {
        return masterKey;
    }

    public void setMasterKey(String masterKey) {
        this.masterKey = masterKey;
    }

    public Map<String, Object> getSlaveDsMap() {
        return slaveDsMap;
    }

    public void setSlaveDsMap(Map<String, Object> slaveDsMap) {
        this.slaveDsMap = slaveDsMap;
    }

    @Override
    public String toString() {
        return "MasterSlaveDataSource{" +
                "masterDs=" + masterDs +
                ", masterKey='" + masterKey + '\'' +
                ", slaveDsMap=" + slaveDsMap +
                '}';
    }
}
