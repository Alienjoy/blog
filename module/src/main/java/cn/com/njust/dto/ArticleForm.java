package cn.com.njust.dto;

import lombok.Data;

import java.util.List;

/**
 * @Classname cn.com.njust.dto
 * @Date 2022/2/18 5:52 下午
 * @Created by Zhang Shuheng
 * @Description
 * dto 数据传输对象，就是从新建的form表单提交的数据
 *
 */
@Data
public class ArticleForm {
    //文章的id，如果是新建的话为空，是hidden的
    private Integer articleId;
    //文章标题
    private String articleTitle;
    //文章的内容
    private String articleContent;
    //文章的分类id
    private Integer articleParentCategoryId;
    //子分类id
    private Integer articleChildCategoryId;
    //文章的状态，发布还是草稿
    private Integer articleStatus;
    //文章的缩略图
    private String articleThumbnail;
    //文章的标签
    private List<Integer> articleTagIds;
}
