package cn.com.njust.service;

import cn.com.njust.entity.User;

/**
 * @Classname cn.com.njust.service
 * @Date 2022/2/13 8:25 下午
 * @Created by Zhang Shuheng
 * @Description
 */

public interface UserService {
    //获取用户信息通过userName或者是email
    public User getUserByUserNameOrEmail(String userName);
    //修改用户个人信息
    public int updateUser(User user);
    //根据用户id获取用户信息
    public  User getUserById(Integer userId);

    //用户注册
    public int insertUser(User user);


}
