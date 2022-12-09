package com.dzx.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.dzx.common.RespBean;
import com.dzx.common.RespBeanEnum;
import com.dzx.entity.User;
import com.dzx.service.IUserService;
import com.dzx.utils.JWTUtils;
import com.dzx.vo.LoginVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

/**
 * TODO
 *
 * @author Daiquiri
 * @version 1.0
 * @date 2022/12/8 16:10
 */

@RestController
@RequestMapping("/login")

public class loginController {
    @Autowired
    private IUserService userService;

    @PostMapping("/doLogin")
    public RespBean doLogin(@RequestBody LoginVo loginVo) {
        if (StringUtils.hasText(loginVo.getUsername())) {
            User user = userService.getOne(new QueryWrapper<User>().eq("username", loginVo.getUsername()));
            if (user == null) {
                return RespBean.error(RespBeanEnum.USER_ERROR);
            }
            if (StringUtils.hasText(loginVo.getPassword()) && loginVo.getPassword().equals(user.getPassword())) {
                String token = JWTUtils.getToken(user);
                return RespBean.success(token);  //登录成功就给前端返回带有token响应结果 让前端携带token进行其他操作
            }
            return RespBean.error(RespBeanEnum.LOGIN_ERROR);
        } else {
            return RespBean.error(RespBeanEnum.USER_ERROR);
        }
    }

    @PostMapping("/register")
    public RespBean register(@RequestBody LoginVo loginVo) {
        if (StringUtils.hasText(loginVo.getUsername())) {
            User user = userService.getOne(new QueryWrapper<User>().eq("username", loginVo.getUsername()));
            if (user != null) {
                return RespBean.error(RespBeanEnum.USER_EXISTS_ERROR);
            }
            if (StringUtils.hasText(loginVo.getPassword())) {
                User user1 = new User();
                user1.setUsername(loginVo.getUsername());
                user1.setPassword(loginVo.getPassword());
                boolean b = userService.save(user1);
                if (b) {
                    return RespBean.success();
                } else {
                    return RespBean.error(RespBeanEnum.ERROR);
                }

            }
            return RespBean.error(RespBeanEnum.ERROR);
        } else {
            return RespBean.error(RespBeanEnum.USER_ERROR);
        }
    }
}
