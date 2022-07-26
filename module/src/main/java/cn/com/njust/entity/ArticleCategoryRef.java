package cn.com.njust.entity;

import java.io.Serializable;
import lombok.Data;

/**
 * @Classname cn.com.njust.entity
 * @Date 2022/2/11 8:50 下午
 * @Created by Zhang Shuheng
 * @Description
 */
@Data
public class ArticleCategoryRef implements Serializable {
    /**
     * 文章ID
     */
    private Integer articleId;

    /**
     * 分类ID
     */
    private Integer categoryId;

    private static final long serialVersionUID = -1989772189059440907L;
}