package cn.com.njust.entity;

import java.io.Serializable;
import lombok.Data;

/**
 * @Classname cn.com.njust.entity
 * @Date 2022/2/11 8:13 下午
 * @Created by Zhang Shuheng
 * @Description 
 */
@Data
public class Category implements Serializable {
    /**
    * 分类ID
    */
    private Integer categoryId;

    /**
    * 分类父ID
    */
    private Integer categoryPid;

    /**
    * 分类名称
    */
    private String categoryName;

    /**
    * 图标
    */
    private String categoryIcon;

    private static final long serialVersionUID = -2863546633324683322L;

    //用于在新建文章的时候，添加分类信息的时候的构造函数
    public Category(Integer categoryId) {
        this.categoryId = categoryId;
    }
}