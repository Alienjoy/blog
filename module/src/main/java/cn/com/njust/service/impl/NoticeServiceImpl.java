package cn.com.njust.service.impl;

import cn.com.njust.entity.Notice;
import cn.com.njust.mapper.NoticeMapper;
import cn.com.njust.service.NoticeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Classname cn.com.njust.service.impl
 * @Date 2022/3/1 11:16 上午
 * @Created by Zhang Shuheng
 * @Description
 */
@Service
@Slf4j
public class NoticeServiceImpl implements NoticeService {
    @Autowired
    private NoticeMapper noticeMapper;
    @Override
    public Notice getNoticeById(Integer noticeId) {
        try {
            Notice notice = noticeMapper.getNoticeById(noticeId);
            return notice;
        }catch (Exception e){
            e.printStackTrace();
            log.error("获取公告失败，失败原因为：{}",e);
        }
        return null;

    }

    @Override
    public List<Notice> getNotices() {
        try {
            List<Notice> notices = noticeMapper.getNotices();
            return notices;
        }catch (Exception e){
            e.printStackTrace();
            log.error("获取公告列表失败，失败原因为：{}",e);
        }
        return null;
    }
}
