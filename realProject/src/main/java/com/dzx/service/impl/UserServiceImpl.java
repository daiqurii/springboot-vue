package com.dzx.service.impl;

import com.dzx.entity.User;
import com.dzx.mapper.UserMapper;
import com.dzx.service.IUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author dzx
 * @since 2022-12-07
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {

}
