package cn.com.njust.controller.home;

import cn.com.njust.entity.Notice;
import cn.com.njust.service.NoticeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @Classname cn.com.njust.controller.home
 * @Date 2022/3/1 11:27 上午
 * @Created by Zhang Shuheng
 * @Description
 */
@Controller
public class NoticeController {
    @Autowired
    private NoticeService noticeService;
    @RequestMapping("/notice/{noticeId}")
    public String getNoticeDetail(@PathVariable("noticeId") Integer noticeId, Model model){
        Notice notice = noticeService.getNoticeById(noticeId);
        model.addAttribute("notice",notice);
        return "home/page/noticeDetail";

    }

}
