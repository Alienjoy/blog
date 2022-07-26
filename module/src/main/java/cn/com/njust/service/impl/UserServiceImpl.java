package cn.com.njust.service.impl;

import cn.com.njust.entity.User;
import cn.com.njust.mapper.UserMapper;
import cn.com.njust.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * @Classname cn.com.njust.service.impl
 * @Date 2022/2/13 8:35 下午
 * @Created by Zhang Shuheng
 * @Description
 */
@Service
@Slf4j
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;
    @Override
    public User getUserByUserNameOrEmail(String userName) {
        try {
            User user = userMapper.getUserByUserNameOrEmail(userName);
            return user;
        }catch (Exception e){
            e.printStackTrace();
            log.error("获取用户失败，失败原因为：{}",e);
        }
        return null;
    }

    @Override
    public int updateUser(User user) {
        int res=0;
        try {
            res = userMapper.updateUser(user);
        }catch (Exception e){
            e.printStackTrace();
            log.error("修改用户信息失败，失败原因为：{}",e);
        }

        return res;
    }

    @Override
    public User getUserById(Integer userId) {
        try {
            User user = userMapper.getUserById(userId);
            return user;
        }catch (Exception e){
            e.printStackTrace();
            log.error("根据id获取用户信息失败，失败原因为：{}",e);
        }
        return null;
    }

    @Override
    public int insertUser(User user) {
        //在此处封装数据
        if(user.getUserAvatar()==null){
            user.setUserAvatar("/img/avatar/defaultAvatar.jpeg");
        }
        if(user.getUserRegisterTime()==null){
            user.setUserRegisterTime(new Date());
        }
        if(user.getUserStatus()==null){
            user.setUserStatus(1);
        }
        if(user.getUserRole()==null){
            user.setUserRole("user");
        }
        try {
            int i = userMapper.insertUser(user);
            return i;
        }catch (Exception e){
            e.printStackTrace();
            log.error("注册用户失败，失败原因为：{}",e);
        }
        return 0;
    }
}
