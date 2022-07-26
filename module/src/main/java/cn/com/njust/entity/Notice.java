package cn.com.njust.entity;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * @Classname cn.com.njust.entity
 * @Date 2022/3/1 11:13 上午
 * @Created by Zhang Shuheng
 * @Description 
 */
@Data
public class Notice implements Serializable {
    /**
    * 公告ID
    */
    private Integer noticeId;

    /**
    * 公告标题
    */
    private String noticeTitle;

    /**
    * 内容
    */
    private String noticeContent;

    /**
    * 创建时间
    */
    private Date noticeCreateTime;

    private static final long serialVersionUID = 3487389972521346012L;
}