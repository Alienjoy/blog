package cn.com.njust.service;

import cn.com.njust.entity.Notice;

import java.util.List;

/**
 * @Classname cn.com.njust.service
 * @Date 2022/3/1 11:15 上午
 * @Created by Zhang Shuheng
 * @Description
 */
public interface NoticeService {
    //根据id获取通知
    public Notice getNoticeById(Integer noticeId);
    //获取所有的通知
    public List<Notice> getNotices();

}
