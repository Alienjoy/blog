package cn.com.njust.entity;

import java.io.Serializable;
import lombok.Data;

/**
 * @Classname cn.com.njust.entity
 * @Date 2022/2/11 3:59 下午
 * @Created by Zhang Shuheng
 * @Description
 */
@Data
public class Information implements Serializable {
    private Integer siteId;

    private String siteTitle;

    private String siteDescription;

    private String siteMetaDescription;

    private String siteMetaKeywords;

    private Integer sitePageViews;

    private static final long serialVersionUID = 1L;
}