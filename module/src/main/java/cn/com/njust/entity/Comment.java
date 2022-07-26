package cn.com.njust.entity;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * @Classname cn.com.njust.entity
 * @Date 2022/2/24 9:55 上午
 * @Created by Zhang Shuheng
 * @Description 
 */
@Data
public class Comment implements Serializable {
    /**
    * 评论ID
    */
    private Integer commentId;

    /**
    * 回复的评论ID
    */
    private Integer commentPid;

    /**
     * 被评论的id，如果是文章的话就是文章的用户id，如果是评论的话就是评论的用户id
     */
    private Integer commentPuserId;

    /**
    * 被评论的用户名
    */
    private String commentPname;



    /**
    * 所属文章的ID
    */
    private Integer commentArticleId;

    /**
    * 内容
    */
    private String commentContent;

    /**
    * 评论者的id
    */
    private Integer commentUserId;

    /**
    * 评论时间
    */
    private Date commentCreateTime;



    private static final long serialVersionUID = 8161024942409591599L;

    //在server层的进行封装。非数据库字段

    //发表评论者的用户昵称
    private String commentAuthorName;

    //用户头像的链接
    private String commentAuthorAvatar;

    //用户的角色，判断是否为博主，是否为自己的文章自己发的评论
    private Integer commentAuthorRole;

    //用户的用户名
    private String commentUserName;

    //所属文章的标题，给后台显示我发表的评论的文章的标题
    private String commentArticleTitle;

    //封装发表此评论的用户信息
    private User user;

    /**
     * 非数据库字段
     */
    private Article article;

}