package com.waibaoservice.service.impl;

import com.waibaoservice.mapper.TimerMapper;
import com.waibaoservice.pojo.Timer;
import com.waibaoservice.service.TimerService;
import com.waibaoservice.timertask.TimerTask;
import com.waibaoservice.utils.DateUtils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * @author DJS
 * Date create 0:12 2023/3/10
 * Modified By DJS
 **/

@Service
public class TimerServiceImpl implements TimerService {
    @Autowired
    private TimerMapper mapper;

    public TimerServiceImpl() {}

    // 设置定时任务
    @Override
    public boolean setTimer(Timer timer) {
        // 获取任务结束的时间字符串
        final String positive_time = timer.getPositive_time();
        String endTime = DateUtils.getEndTimeStr(new Date(), positive_time, ":");
        System.out.println(endTime);
        // 将定时器任务添加到数据库, 如果数据库中本身存在任务，需要更新
        Timer t = mapper.selectTimerByOpenId(timer.getOpenid());
        if (t == null) {
            int ret = mapper.addTimer(
                timer.getOpenid(),
                timer.getSession_key(),
                timer.getUnionid(),
                endTime
            );
            if (ret == 1) System.out.println("添加定时任务成功");
            else System.out.println("添加定时任务失败");
        }
        else {
            // 更新定时任务
            int ret = mapper.updateTimerByOpenId(
                timer.getOpenid(),
                endTime
            );
            if (ret == 1) System.out.println("更新定时任务成功");
            else System.out.println("更新定时任务失败");
        }
        // 更新缓存

        return true;
    }

    // 取消定时任务
    @Override
    public boolean cancelTimer(Timer timer) {

        return false;
    }

    // 小程序登录时获取timer结束时间， 如果没有设置时间返回"no task"
    @Override
    public String getEndTime(Timer timer) {
        return null;
    }
}
