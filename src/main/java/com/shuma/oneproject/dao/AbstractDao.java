package com.shuma.oneproject.dao;

import java.util.List;
import java.util.Map;

/**
 *
 * @author 马文韬
 * @param <E>
 * @date   2017-7-23
 * @version V1.0
 * @desc
 *  数据层通用接口
 */
public abstract interface AbstractDao<E> {
    /**
     * 插入实体
     *
     * @param entity 实体
     * @return
     */
    public int insert(E entity);

    /**
     * 更新实体
     *
     * @param entity 实体
     * @return 更新是否成功
     */
    public boolean update(E entity);

    /**
     * 删除实体
     *
     * @param entity 实体
     * @return 删除是否成功
     */
    public boolean delete(E entity);

    /**
     * 根据条件查询实体列表
     *
     * @param map 查询条件
     * @return 实体列表
     */
    public List<E> select(Map<String, Object> map);

    /**
     * 根据主键或唯一标识查询单个实体
     *
     * @param map 查询条件
     * @return 实体
     */
    public E selectOne(Map<String, Object> map);

    /**
     * 根据条件获取个数
     *
     * @param map 查询条件
     * @return 个数
     */
    public int count(Map<String, Object> map);
}
