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
public class ArticleTagRef implements Serializable {
    /**
     * 文章ID
     */
    private Integer articleId;

    /**
     * 标签ID
     */
    private Integer tagId;

    private static final long serialVersionUID = 2724759287791457687L;
}