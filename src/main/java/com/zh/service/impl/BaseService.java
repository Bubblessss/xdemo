package com.zh.service.impl;

import com.zh.dao.mybatis.BaseMapper;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 基础公共service
 * @param <D> 子类service的mapper
 * @param <T> 子类service的实体类
 * @author zhanghang
 * @date 2017/12/19
 */
public  class BaseService<D extends BaseMapper<T>,T> {
    @Autowired
    protected D dao;

}
