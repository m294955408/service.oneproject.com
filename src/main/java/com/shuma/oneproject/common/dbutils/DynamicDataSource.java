package com.shuma.oneproject.common.dbutils;

import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

/**
 * @author 马文韬
 * @version V1.0
 * @date 2017/8/15
 * @desc
 */
public class DynamicDataSource extends AbstractRoutingDataSource {
    @Override
    protected Object determineCurrentLookupKey() {
        return DataSourceContextHolder.getDbType();
    }
}
