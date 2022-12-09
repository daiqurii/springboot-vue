package com.dzx.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.dzx.common.RespBean;
import com.dzx.common.RespBeanEnum;
import com.dzx.entity.User;
import com.dzx.service.IUserService;
import com.dzx.service.impl.UserServiceImpl;
import io.swagger.models.auth.In;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import org.springframework.stereotype.Controller;

import java.util.List;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author dzx
 * @since 2022-12-07
 */
@CrossOrigin
@Controller
@ResponseBody
@RequestMapping("/user")
public class UserController {
    @Autowired
    private IUserService userService;

    @PostMapping("/add")
    public RespBean addUser(@RequestBody User user) {
        if (user.getPassword() == null) {
            //设置默认密码
            user.setPassword("123456");
        }
        boolean b = userService.save(user);
        if (b) {
            return RespBean.success();
        }
        return RespBean.error(RespBeanEnum.ERROR);
    }

    /**
     * 分页查询
     *
     * @param pageNum  第几页
     * @param pageSize 每页多少条
     * @param search   搜索关键字
     * @return
     */
    @GetMapping("/getAll")
    public RespBean findPages(@RequestParam(defaultValue = "1") Integer pageNum, @RequestParam(defaultValue = "10") Integer pageSize, @RequestParam(defaultValue = "") String search) {
        Page<User> page = new Page<>(pageNum, pageSize);
//        模糊搜索
        Page<User> userPage = userService.page(page, new QueryWrapper<User>().like(StringUtils.isNotBlank(search), "username", search));
//        List<User> users = userService.list();
        return RespBean.success(userPage);
    }

    @GetMapping("/get/{id}")
    public RespBean getUserById(@PathVariable Integer id) {
        User user = userService.getById(id);
        if (user == null) {
            return RespBean.error(RespBeanEnum.USER_ERROR);
        }
        return RespBean.success(user);
    }

    @PutMapping("/put")
    public RespBean editUserById(@RequestBody User user){

        boolean b = userService.updateById(user);
        if (b) {
            return RespBean.success();
        }
        return RespBean.error(RespBeanEnum.ERROR);
    }

    @DeleteMapping("/delete/{id}")
    public RespBean deleteUserById(@PathVariable Integer id){
        boolean b = userService.removeById(id);
        if (b) {
            return RespBean.success();
        }
        return RespBean.error(RespBeanEnum.ERROR);
    }

}
