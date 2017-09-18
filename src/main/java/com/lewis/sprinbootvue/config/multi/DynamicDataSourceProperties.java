package com.lewis.sprinbootvue.config.multi;

import com.lewis.sprinbootvue.config.MasterSlaveDataSource;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DynamicDataSourceProperties {

    private static List<MasterSlaveDataSource> masterSlaveDataSourceList = new ArrayList<>();

    private static DynamicDataSourceProperties instance = new DynamicDataSourceProperties();

    public static DynamicDataSourceProperties getInstance() {
        return instance;
    }

    public static List<MasterSlaveDataSource> getMasterSlaveDataSourceList() {
        return masterSlaveDataSourceList;
    }

    public static void setMasterSlaveDataSourceList(List<MasterSlaveDataSource> masterSlaveDataSourceList) {
        DynamicDataSourceProperties.masterSlaveDataSourceList = masterSlaveDataSourceList;
    }

    private static Map<String,String> key2PathMapping = new HashMap<>();

    static {
        key2PathMapping.put("sharding_0","com.lewis.sprinbootvue.biz.mybatis.dao.sharding_0");
        key2PathMapping.put("sharding_1","com.lewis.sprinbootvue.biz.mybatis.dao.sharding_0");
        key2PathMapping.put("snailReader","com.lewis.sprinbootvue.biz.mybatis.dao.snailReader");
    }

    public static Map<String, String> getKey2PathMapping() {
        return key2PathMapping;
    }
}
