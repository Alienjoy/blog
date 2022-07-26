package cn.com.njust.service;

import cn.com.njust.entity.Comment;
import com.github.pagehelper.PageInfo;

import java.util.HashMap;
import java.util.List;

/**
 * @Classname cn.com.njust.service
 * @Date 2022/2/24 2:32 下午
 * @Created by Zhang Shuheng
 * @Description
 */
public interface CommentService {
    //根据文章获取评论
    public List<Comment> getCommentByArticleId(Integer articleId);
    //添加评论
    public int insertComment(Comment comment);
    //根据查询条件获取评论
    public PageInfo<Comment> getCommentListByPage(Integer pageIndex, Integer pageSize, HashMap<String, Object> criteria);
    //根据commentId删除评论
    public int deleteCommentById(Integer commentId);
    //根据commentId获取评论
    public Comment getCommentById(Integer commentId);

    /**
     * 获得最近评论
     *
     * @param limit 查询数量
     * @return 列表
     */
    List<Comment> listRecentComment(Integer userId, Integer limit);
}
