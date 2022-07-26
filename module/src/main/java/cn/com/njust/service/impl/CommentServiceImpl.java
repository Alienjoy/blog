package cn.com.njust.service.impl;

import cn.com.njust.entity.Article;
import cn.com.njust.entity.Comment;
import cn.com.njust.entity.User;
import cn.com.njust.mapper.ArticleMapper;
import cn.com.njust.mapper.CommentMapper;
import cn.com.njust.mapper.UserMapper;
import cn.com.njust.service.CommentService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;

/**
 * @Classname cn.com.njust.service.impl
 * @Date 2022/2/24 2:34 下午
 * @Created by Zhang Shuheng
 * @Description
 */
@Service
@Slf4j
public class CommentServiceImpl implements CommentService {
    @Autowired
    private CommentMapper commentMapper;
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private ArticleMapper articleMapper;


    @Override
    public List<Comment> getCommentByArticleId(Integer articleId) {
        try {
            List<Comment> commentList = commentMapper.getCommentByArticleId(articleId);
            for (Comment comment : commentList) {
                //System.out.println("ssssssss1"+comment);
                Integer commentUserId = comment.getCommentUserId();
                User user = userMapper.getUserById(commentUserId);
                //System.out.println("ssssssss2"+user);
                //设置评论者的昵称
                comment.setCommentAuthorName(user.getUserNickname());
                //设置评论者的头像
                comment.setCommentAuthorAvatar(user.getUserAvatar());
                //设置评论者的角色，暂且还没用到
                if("admin".equals(user.getUserRole())){
                    //1表示文章的作者发的评论。
                    comment.setCommentAuthorRole(1);
                }else {
                    //0表示普通用户
                    comment.setCommentAuthorRole(0);
                }
                //System.out.println("sssss3"+comment);
            }
            return commentList;
        }catch (Exception e){
            e.printStackTrace();
            log.error("获取评论信息失败，{}",e);
        }
        return null;
    }

    @Override
    public int insertComment(Comment comment) {
        try {
            commentMapper.insert(comment);
            return 1;

        }catch (Exception e){
            e.printStackTrace();
            log.error("添加评论失败，失败原因为：{}",e);
        }
        return 0;
    }

    @Override
    public PageInfo<Comment> getCommentListByPage(Integer pageIndex, Integer pageSize, HashMap<String, Object> criteria) {
        PageHelper.startPage(pageIndex, pageSize);
        List<Comment> commentList = null;
        try {
            commentList = commentMapper.getCommentsByCriteria(criteria);
            for (int i = 0; i < commentList.size(); i++) {
                Article article = articleMapper.getArticleById(commentList.get(i).getCommentArticleId());
                Comment comment=commentList.get(i);
                //设置评论的文章的标题
                comment.setCommentArticleTitle(article.getArticleTitle());

                Integer commentUserId = comment.getCommentUserId();
                User user = userMapper.getUserById(commentUserId);
                //System.out.println("ssssssss2"+user);
                //设置评论者的昵称
                comment.setCommentAuthorName(user.getUserNickname());
                //设置评论者的头像
                comment.setCommentAuthorAvatar(user.getUserAvatar());
                //设置评论者的用户名
                comment.setCommentUserName(user.getUserName());

            }
        } catch (Exception e) {
            e.printStackTrace();
            log.error("分页获得评论失败,pageIndex:{}, pageSize:{}, cause:{}", pageIndex, pageSize, e);
        }
        return new PageInfo<>(commentList);

    }

    @Override
    public int deleteCommentById(Integer commentId) {
        try {
            commentMapper.deleteCommentById(commentId);
            return 1;

        }catch (Exception e){
            e.printStackTrace();
            log.error("删除评论失败，失败原因为：{}",e);
        }

        return 0;
    }

    @Override
    public Comment getCommentById(Integer commentId) {
        try {
            Comment comment = commentMapper.getCommentById(commentId);
            return comment;


        }catch (Exception e){
            e.printStackTrace();
            log.error("获取评论失败，失败原因为：{}",e);
        }

        return null;
    }

    @Override
    public List<Comment> listRecentComment(Integer userId, Integer limit) {
        List<Comment> commentList = null;
        try {
            commentList = commentMapper.listRecentComment(userId, limit);
            for (int i = 0; i < commentList.size(); i++) {
                Article article = articleMapper.getArticleById( commentList.get(i).getCommentArticleId());
                commentList.get(i).setArticle(article);
            }
        } catch (Exception e) {
            e.printStackTrace();
            log.error("获得最新评论失败, limit:{}, cause:{}", limit, e);
        }
        return commentList;
    }
}
