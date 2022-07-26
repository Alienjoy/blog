package cn.com.njust.mapper;

import cn.com.njust.entity.Notice;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @Classname cn.com.njust.mapper
 * @Date 2022/3/1 11:13 上午
 * @Created by Zhang Shuheng
 * @Description
 */
@Mapper
public interface NoticeMapper {
    /**
     * delete by primary key
     *
     * @param noticeId primaryKey
     * @return deleteCount
     */
    int deleteById(Integer noticeId);

    /**
     * insert record to table
     *
     * @param record the record
     * @return insert count
     */
    int insert(Notice record);

    /**
     * select by primary key
     *
     * @param noticeId primary key
     * @return object by primary key
     */
    Notice getNoticeById(Integer noticeId);


    /**
     *
     */
    List<Notice> getNotices();
}