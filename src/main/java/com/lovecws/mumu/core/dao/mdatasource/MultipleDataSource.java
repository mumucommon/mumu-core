package com.lovecws.mumu.core.dao.mdatasource;

import org.apache.commons.lang3.RandomUtils;
import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

import javax.sql.DataSource;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

public class MultipleDataSource extends AbstractRoutingDataSource {

    private Map<Object, Object> targetDataSources;
    private Map<String, List<DataSource>> dataSourceMap = new ConcurrentHashMap<String, List<DataSource>>();
    private String dataSourceSplit;

    public void setTargetDataSources(Map<Object, Object> targetDataSources) {
        this.targetDataSources = targetDataSources;
    }

    public void setDataSourceSplit(String dataSourceSplit) {
        this.dataSourceSplit = dataSourceSplit;
    }

    @Override
    public void afterPropertiesSet() {
        if (this.targetDataSources == null) {
            throw new IllegalArgumentException("Property 'targetDataSources' is required");
        } else {
            Iterator var1 = this.targetDataSources.entrySet().iterator();

            while (var1.hasNext()) {
                Map.Entry<Object, Object> entry = (Map.Entry) var1.next();
                String lookupKey = this.resolveSpecifiedLookupKey(entry.getKey()).toString().toLowerCase();
                String[] dataSourceSplits = lookupKey.split(dataSourceSplit);
                List<DataSource> dataSources = dataSourceMap.get(dataSourceSplits[0]);
                if (dataSources == null) {
                    dataSources = new ArrayList<DataSource>();
                }
                DataSource dataSource = this.resolveSpecifiedDataSource(entry.getValue());
                dataSources.add(dataSource);
                dataSourceMap.put(dataSourceSplits[0], dataSources);
            }
        }
    }

    @Override
    protected DataSource determineTargetDataSource() {
        List<DataSource> dataSourceList = dataSourceMap.get(determineCurrentLookupKey());
        return dataSourceList.get(RandomUtils.nextInt(0, dataSourceList.size()));
    }

    @Override
    protected Object determineCurrentLookupKey() {
        // read write
        return MultipleDataSourceHolder.getDataSource().toLowerCase();
    }
}