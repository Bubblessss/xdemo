package com.zh.service.impl;

import com.zh.config.activemq.MqProduct;
import com.zh.dao.jpa.UserRepository;
import com.zh.dao.mybatis.UserMapper;
import com.zh.pojo.po.User;
import com.zh.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import java.util.List;

/**
 * 用户服务实现类
 * @author zhanghang
 * @date 2017/12/19
 */
@Service
@Slf4j
public class UserServiceImpl extends BaseService<UserMapper,User> implements UserService{

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private MqProduct mqProduct;
    @Autowired
    private JedisPool jedisPool;

    @Override
    public User getUserById(Integer id) {
        return this.userRepository.findOne(id);
    }

    @Override
    public Page<User> listUsersByDeptId(Integer deptId,Pageable pageable) {
        return this.userRepository.findByDeptId(deptId,pageable);
    }

    @Override
    public List<User> listUsers() {
        return this.dao.selectAllUsers();
    }


    @Override
    @Transactional(rollbackFor = Exception.class)
    public void saveUser(User user) {
        Integer uid = user.getId();
        this.userRepository.saveAndFlush(user);
        if (uid == null) {
            mqProduct.sendMessage("新增用户:" + user.getName());
        }
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void removeUserById(Integer id) {
        this.userRepository.delete(id);
    }

    @Override
    public String getUserSexByName(String name) {
        User user = this.userRepository.findByName(name);
        return user.getSex();
    }

    @Override
    public void removeFromRedisByKey(String key) {
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            jedis.del(key);
        } catch (Exception e) {
            log.error(e.getMessage());
        }finally {
            if (jedis != null) {
                jedis.close();
            }
        }
    }
}
