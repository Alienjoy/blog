package cn.com.njust.entity;

import java.io.Serializable;
import lombok.Data;

/**
 * @Classname cn.com.njust.entity
 * @Date 2022/2/11 8:46 下午
 * @Created by Zhang Shuheng
 * @Description 
 */
@Data
public class Tag implements Serializable {
    /**
    * 标签ID
    */
    private Integer tagId;

    /**
    * 标签名称
    */
    private String tagName;

    private static final long serialVersionUID = 6170292090958752753L;

    public Tag(Integer tagId) {
        this.tagId = tagId;
    }
}