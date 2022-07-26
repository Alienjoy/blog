package cn.com.njust.mapper;

import cn.com.njust.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * @Classname cn.com.njust.mapper
 * @Date 2022/2/11 8:35 下午
 * @Created by Zhang Shuheng
 * @Description
 */
@Mapper
public interface UserMapper {
    //根据用户id获取用户信息
    public User getUserById(Integer userId);

    //获取用户信息通过userName或者是email
    public User getUserByUserNameOrEmail(@Param("userName") String userName);

    //修改用户信息
    public int updateUser(@Param("user") User user);

    //修改用户信息
    public int insertUser(@Param("user") User user);

}