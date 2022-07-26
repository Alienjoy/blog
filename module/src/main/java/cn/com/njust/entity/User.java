package cn.com.njust.entity;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * @Classname cn.com.njust.entity
 * @Date 2022/2/11 8:35 下午
 * @Created by Zhang Shuheng
 * @Description 
 */
@Data
public class User implements Serializable {
    /**
    * 用户ID
    */
    private Integer userId;

    /**
    * 用户名
    */
    private String userName;

    /**
    * 密码
    */
    private String userPass;

    /**
    * 昵称
    */
    private String userNickname;

    /**
    * 邮箱
    */
    private String userEmail;

    /**
    * 头像
    */
    private String userAvatar;

    /**
    * 上传登录IP
    */
    private String userLastLoginIp;

    /**
    * 注册时间
    */
    private Date userRegisterTime;

    /**
    * 上传登录时间
    */
    private Date userLastLoginTime;

    /**
    * 状态
    */
    private Integer userStatus;

    /**
    * 角色
    */
    private String userRole;

    private static final long serialVersionUID = -494952192902719294L;

    public User(String userName, String userPass, String userNickname, String userEmail) {
        this.userName = userName;
        this.userPass = userPass;
        this.userNickname = userNickname;
        this.userEmail = userEmail;
    }
}