package com.zh.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zh.annotation.OperateLogger;
import com.zh.pojo.dto.PageDto;
import com.zh.pojo.po.User;
import com.zh.pojo.vo.Result;
import com.zh.service.UserService;
import com.zh.utils.MyApp;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;
import java.util.List;

/**
 * UserController类
 * @author zhanghang
 * @date 2017/12/19
 */
@RestController
@RequestMapping("/user")
@Api(value = "UserController",description = "用户controlle")
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping("/getUserById")
    @ApiOperation(value="获取用户", notes="根据用户主键获取用户")
    @ApiImplicitParam(name = "id", value = "用户主键", paramType="query", dataType = "Long", required = true)
    @OperateLogger(description = "根据用户主键获取用户")
    public Result getUserById(@PathParam("id") Integer id){
        User user = this.userService.getUserById(id);
        return new Result(user);
    }


    @GetMapping("/getUserSexByName")
    @ApiOperation(value="获取用户性别", notes="根据用户名获取用户性别")
    @ApiImplicitParam(name = "name", value = "用户名", paramType="query", dataType = "String", required = true)
    @OperateLogger(description = "根据用户名获取用户性别")
    @Cacheable(value = "zh",key = "'user_sex_' + #name")
    public Result getUserSexByName(@PathParam("name") String name){
        String sex = this.userService.getUserSexByName(name);
        return new Result(sex);
    }

    @GetMapping("/removeFromRedisByKey")
    @ApiOperation(value="删除redis中的数据", notes="根据key删除redis中的数据")
    @ApiImplicitParam(name = "key", value = "redis中的key", paramType="query", dataType = "String",required = true)
    @OperateLogger(description = "根据key删除redis中的数据",operateType = MyApp.DELETE)
    public void removeFromRedisByKey(@PathParam("key") String key){
        this.userService.removeFromRedisByKey(key);
    }

    @PostMapping("/listUsersByDeptId")
    @ApiOperation(value="查询用户", notes="根据部门编号查询用户")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "page", value = "起始页", paramType="form", dataType = "Long", required = true),
            @ApiImplicitParam(name = "size", value = "条数", paramType="form", dataType = "Long", required = true),
            @ApiImplicitParam(name = "deptId", value = "部门主键", paramType="form", dataType = "Long", required = true)
    })
    @OperateLogger(description = "根据部门编号查询用户")
    public Result listUsersByDeptId(PageDto pageDto, Integer deptId){
        Sort sort = new Sort(Sort.Direction.DESC, MyApp.SORT_ID);
        Pageable pageable = new PageRequest(pageDto.getPage(), pageDto.getSize(), sort);
        Page<User> pageUsers = this.userService.listUsersByDeptId(deptId,pageable);
        return new Result(pageUsers.getContent(),pageUsers.getTotalElements());
    }

    @PostMapping("/listUsers")
    @ApiOperation(value="查询所有用户", notes="查询所有用户")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "page", value = "起始页", paramType="form", dataType = "Long", required = true),
            @ApiImplicitParam(name = "size", value = "条数", paramType="form", dataType = "Long", required = true)
    })
//    @OperateLogger(description = "查询所有用户")
    public Result listUsers(PageDto pageDto){
        PageHelper.startPage(pageDto.getPage(), pageDto.getSize());
        List<User> users = this.userService.listUsers();
        PageInfo pageInfo = new PageInfo(users);
        return new Result(users,pageInfo.getTotal());
    }

    @PostMapping("/saveUser")
    @ApiOperation(value="保存用户", notes="新增或修改一条用户数据")
//    @ApiImplicitParam(name = "user", value = "用户实体", required = true, dataType = "User")
    @OperateLogger(description = "新增或修改一条用户数据",operateType = MyApp.INSERT_UPDATE)
    public void saveUser(User user){
        this.userService.saveUser(user);
    }

    @GetMapping("/removeUserById")
    @ApiOperation(value="删除用户", notes="根据用主键删除用户")
    @ApiImplicitParam(name = "id", value = "用户主键", paramType="query", dataType = "Long",required = true)
    @OperateLogger(description = "根据用主键删除用户",operateType = MyApp.DELETE)
    @CacheEvict(value = "zh",key = "'user_' + #id")
    public void removeUserById(@PathParam("id") Integer id){
        this.userService.removeUserById(id);
    }

}
