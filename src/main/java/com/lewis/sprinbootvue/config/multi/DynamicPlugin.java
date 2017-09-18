package com.lewis.sprinbootvue.config.multi;

import com.lewis.sprinbootvue.config.MasterSlaveDataSource;
import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.executor.Executor;
import org.apache.ibatis.executor.keygen.SelectKeyGenerator;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.mapping.SqlCommandType;
import org.apache.ibatis.plugin.*;
import org.apache.ibatis.session.ResultHandler;
import org.apache.ibatis.session.RowBounds;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.transaction.support.TransactionSynchronizationManager;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

@Intercepts({
        @Signature(type = Executor.class, method = "update", args = {
                MappedStatement.class, Object.class}),
        @Signature(type = Executor.class, method = "query", args = {
                MappedStatement.class, Object.class, RowBounds.class,
                ResultHandler.class})})
public class DynamicPlugin implements Interceptor {

    protected static final Logger logger = LoggerFactory.getLogger(DynamicPlugin.class);

    private static final String REGEX = ".*insert\\u0020.*|.*delete\\u0020.*|.*update\\u0020.*";

    //key: MappedStatement.getId  value: datasource id
    private static final Map<String, String> cacheMap = new ConcurrentHashMap<>();

    @Override
    public Object intercept(Invocation invocation) throws Throwable {

        boolean synchronizationActive = TransactionSynchronizationManager.isSynchronizationActive();
        if (!synchronizationActive) {
            Object[] objects = invocation.getArgs();
            MappedStatement ms = (MappedStatement) objects[0];
            //DynamicDataSourceGlobal dynamicDataSourceGlobal = null;
            String finalKey = null;
            String id = ms.getId();

            //没有MappedStatement的id的缓存
            if ((finalKey = cacheMap.get(id)) == null) {
                List<String> satisfiedKeys = getSatisfiedKeys(id);
                List<MasterSlaveDataSource> masterSlaveDataSourceList = DynamicDataSourceProperties.getMasterSlaveDataSourceList();

                if (ms.getSqlCommandType().equals(SqlCommandType.SELECT)) {
                    //!selectKey 为自增id查询主键(SELECT LAST_INSERT_ID() )方法，使用主库
                    if (ms.getId().contains(SelectKeyGenerator.SELECT_KEY_SUFFIX)) {
                        finalKey = processMasterKey(finalKey, satisfiedKeys, masterSlaveDataSourceList);
                    } else {
                        BoundSql boundSql = ms.getSqlSource().getBoundSql(objects[1]);
                        String sql = boundSql.getSql().toLowerCase(Locale.CHINA).replaceAll("[\\t\\n\\r]", " ");
                        if (sql.matches(REGEX)) {
                            finalKey = processMasterKey(finalKey, satisfiedKeys, masterSlaveDataSourceList);
                        } else {
                            for (MasterSlaveDataSource masterSlaveDataSource : masterSlaveDataSourceList) {
                                if (StringUtils.isEmpty(finalKey)) {
                                    Map<String, Object> slaveDsMap = masterSlaveDataSource.getSlaveDsMap();
                                    if (MapUtils.isNotEmpty(slaveDsMap)) {
                                        Iterator<Map.Entry<String, Object>> it = slaveDsMap.entrySet().iterator();
                                        while (it.hasNext()) {
                                            Map.Entry<String, Object> entry = it.next();
                                            for (String satisfiedKey : satisfiedKeys) {
                                                if (StringUtils.equals(satisfiedKey, entry.getKey())) {
                                                    finalKey = satisfiedKey;
                                                    break;
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                } else {
                    finalKey = processMasterKey(finalKey, satisfiedKeys, masterSlaveDataSourceList);
                }
            }
            logger.warn("设置方法[{}] use [{}] Strategy, SqlCommandType [{}]..", ms.getId(), finalKey, ms.getSqlCommandType().name());
            cacheMap.put(ms.getId(), finalKey);
            DynamicDataSourceHolder.putDataSource(finalKey);
        }

        return invocation.proceed();
    }

    private String processMasterKey(String finalKey, List<String> satisfiedKeys, List<MasterSlaveDataSource> masterSlaveDataSourceList) {
        for (MasterSlaveDataSource masterSlaveDataSource : masterSlaveDataSourceList) {
            if (StringUtils.isEmpty(finalKey)) {
                for (String satisfiedKey : satisfiedKeys) {
                    if (StringUtils.equals(satisfiedKey, masterSlaveDataSource.getMasterKey())) {
                        finalKey = satisfiedKey;
                        break;
                    }
                }
            }
        }
        return finalKey;
    }

    private List<String> getSatisfiedKeys(String id) {
        List<String> satisfiedKeys = new ArrayList<>();
        Map<String, String> key2PathMapping = DynamicDataSourceProperties.getKey2PathMapping();
        Iterator<Map.Entry<String, String>> iterator = key2PathMapping.entrySet().iterator();
        while (iterator.hasNext()) {
            Map.Entry<String, String> entry = iterator.next();
            if (id.startsWith(entry.getValue())) {
                String dataSourceKey = entry.getKey();
                satisfiedKeys.add(dataSourceKey);
            }
        }
        return satisfiedKeys;
    }

    @Override
    public Object plugin(Object target) {
        if (target instanceof Executor) {
            return Plugin.wrap(target, this);
        } else {
            return target;
        }
    }

    @Override
    public void setProperties(Properties properties) {
        //
    }
}
