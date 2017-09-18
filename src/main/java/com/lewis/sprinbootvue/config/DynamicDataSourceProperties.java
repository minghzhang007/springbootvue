package com.lewis.sprinbootvue.config;

import com.google.common.collect.ArrayListMultimap;

public class DynamicDataSourceProperties {
    public static ArrayListMultimap<String, String> dataSourceKeyMap;

    static {
        dataSourceKeyMap = ArrayListMultimap.create();
    }

    public static ArrayListMultimap<String, String> getDataSourceKeyMap() {
        return dataSourceKeyMap;
    }

   /* public static void setDataSourceKeyMap(ArrayListMultimap<String, String> dataSourceKeyMap) {
        this.dataSourceKeyMap = dataSourceKeyMap;
    }*/
}
