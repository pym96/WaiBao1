package com.waibaoservice.timertask;

import com.waibaoservice.mapper.TimerMapper;
import com.waibaoservice.pojo.Timer;
import com.waibaoservice.utils.BeanUtils.SpringContextUtils;
import com.waibaoservice.utils.DateUtils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * @author DJS
 * Date create 3:33 2023/3/10
 * Modified By DJS
 **/

// 任务类定时操作
public class TimerTask implements Runnable{
    public static List<Timer> timers;
    public static boolean loopCondition = true;
    public static boolean mapperCondition = false;

    // 获取mapper实例
    TimerMapper timerMapper = SpringContextUtils.getBean(TimerMapper.class);

    public TimerTask() {}

    @Override
    public void run() {
        while (loopCondition) {
            // 保证一次加载
            if (timerMapper != null && !mapperCondition) {
                timers = timerMapper.selectAllTimer();
                mapperCondition = false;
            }
            if (timers != null) {
                // 不断遍历找到符合条件的用户，推送消息
                for (Timer timer : timers) {
                    Date currentDate = new Date();
                    Date endDate = DateUtils.parseDateStr(timer.getEnd_time());
                    if (currentDate.after(endDate)) {
                        synchronized (this) {
                            // 更新数据库
                            int ret = timerMapper.removeTimer(timer.getOpenid());
                            if (ret == 1) System.out.println("定时任务结束, openid:" + timer.getOpenid());
                            else {
                                // 如果没有数据受影响，说明数据不一致，需要更新
                                timers = timerMapper.selectAllTimer();
                                break;
                            }
                            // 更新缓存
                            timers = timerMapper.selectAllTimer();
                            // 向微信服务器发送请求，向用户推消息

                            // 提前结束循环, 避免更新时异常
                            break;
                        }
                    }
                }
            }
        }
    }
}

