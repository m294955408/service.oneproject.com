package com.shuma.oneproject.dao;

import com.shuma.oneproject.common.dbutils.DataSource;
import com.shuma.oneproject.common.dbutils.DataSourceContextHolder;
import com.shuma.oneproject.entity.Category;

/**
 * @author 马文韬
 * @version V1.0
 * @date 2017/8/26
 * @desc
 */
@DataSource(DataSourceContextHolder.BlogDataSource)
public interface CategoryDao extends AbstractDao<Category> {

}
