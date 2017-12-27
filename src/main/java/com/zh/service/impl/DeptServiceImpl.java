package com.zh.service.impl;

import com.zh.dao.mybatis.DeptMapper;
import com.zh.pojo.po.Dept;
import com.zh.service.DeptService;
import org.springframework.stereotype.Service;

/**
 * 部门服务实现类
 * @author zhanghang
 * @date 2017/12/19
 */
@Service
public class DeptServiceImpl extends BaseService<DeptMapper,Dept> implements DeptService{

}
