package cn.com.njust.mapper;

import cn.com.njust.entity.Comment;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.HashMap;
import java.util.List;

/**
 * @Classname cn.com.njust.mapper
 * @Date 2022/2/24 9:55 上午
 * @Created by Zhang Shuheng
 * @Description 
 */
@Mapper
public interface CommentMapper {

    List<Comment> getCommentByArticleId(Integer articleId);
    /**
     * delete by primary key
     * @param commentId primaryKey
     * @return deleteCount
     */
    int deleteCommentById(Integer commentId);

    /**
     * insert record to table
     * @param record the record
     * @return insert count
     */
    int insert(Comment record);

    List<Comment> getCommentsByCriteria(HashMap<String,Object> criteria);

    //获取comment根据id
    Comment getCommentById(Integer commandId);


    /**
     * 获得最近评论
     *
     * @param limit 查询数量
     * @return 列表
     */
    List<Comment> listRecentComment(@Param(value = "userId") Integer userId,
                                    @Param(value = "limit") Integer limit);
}