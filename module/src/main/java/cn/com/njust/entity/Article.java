package cn.com.njust.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import lombok.Data;

/**
 * @Classname cn.com.njust.entity
 * @Date 2022/2/11 3:29 下午
 * @Created by Zhang Shuheng
 * @Description
 */
@Data
public class Article implements Serializable {
    private static final long serialVersionUID = -8285400201384019552L;
    /**
     * 文章ID
     */
    private Integer articleId;

    /**
     * 用户ID
     */
    private Integer articleUserId;

    /**
     * 标题
     */
    private String articleTitle;

    /**
     * 摘要
     */
    private String articleSummary;

    /**
     * 内容
     */
    private String articleContent;

    /**
     * 访问量
     */
    private Integer articleViewCount;

    /**
     * 评论数
     */
    private Integer articleCommentCount;

    /**
     * 点赞数
     */
    private Integer articleLikeCount;

    /**
     * 是否允许评论
     */
    private Integer articleIsComment;

    /**
     * 状态
     */
    private Integer articleStatus;



    /**
     * 更新时间
     */
    private Date articleUpdateTime;

    /**
     * 创建时间
     */
    private Date articleCreateTime;

    /**
     * 缩略图
     */
    private String articleThumbnail;

    //非数据库字段
    //所属用户
    private User user;
    //所属分类
    private List<Category> categoryList;
    //所属标签
    private List<Tag> tagList;




}