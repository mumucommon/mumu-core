package com.lovecws.mumu.core.dao.mdatasource;


import org.apache.ibatis.executor.Executor;
import org.apache.ibatis.executor.keygen.SelectKeyGenerator;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.mapping.SqlCommandType;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.plugin.Invocation;
import org.apache.ibatis.plugin.Plugin;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.transaction.support.TransactionSynchronizationManager;

import java.util.Locale;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.ConcurrentHashMap;

public class MultipleMybatisInteInterceptor implements Interceptor {

    protected static final Logger logger = LoggerFactory.getLogger(MultipleMybatisInteInterceptor.class);

    private static final String REGEX = ".*insert\\u0020.*|.*delete\\u0020.*|.*update\\u0020.*";

    private static final Map<String, String> cacheMap = new ConcurrentHashMap<String, String>();

    @Override
    public Object intercept(Invocation invocation) throws Throwable {
        boolean synchronizationActive = TransactionSynchronizationManager.isSynchronizationActive();
        if (!synchronizationActive) {
            Object[] objects = invocation.getArgs();
            MappedStatement mappedStatement = (MappedStatement) objects[0];
            String mappedStatementId = mappedStatement.getId();

            String dynamicDataSourceGlobal = cacheMap.get(mappedStatementId);
            if (dynamicDataSourceGlobal == null) {
                //读方法
                if (mappedStatement.getSqlCommandType().equals(SqlCommandType.SELECT)) {
                    if (mappedStatementId.contains(SelectKeyGenerator.SELECT_KEY_SUFFIX)) {
                        dynamicDataSourceGlobal = MultipleDataSourceHolder.READ;
                    } else {
                        BoundSql boundSql = mappedStatement.getSqlSource().getBoundSql(objects[1]);
                        String sql = boundSql.getSql().toLowerCase(Locale.CHINA).replaceAll("[\\t\\n\\r]", " ");
                        if (sql.matches(REGEX)) {
                            dynamicDataSourceGlobal = MultipleDataSourceHolder.WRITE;
                        } else {
                            dynamicDataSourceGlobal = MultipleDataSourceHolder.READ;
                        }
                    }
                } else {
                    dynamicDataSourceGlobal = MultipleDataSourceHolder.WRITE;
                }
                logger.warn("设置方法[{}] use [{}] Strategy, SqlCommandType [{}]..", mappedStatementId, dynamicDataSourceGlobal, mappedStatement.getSqlCommandType().name());
                cacheMap.put(mappedStatementId, dynamicDataSourceGlobal);
            }
            MultipleDataSourceHolder.setDataSource(dynamicDataSourceGlobal);
        }

        return invocation.proceed();
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

    }
}