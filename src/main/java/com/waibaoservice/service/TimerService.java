package com.waibaoservice.service;

import com.waibaoservice.pojo.Timer;

public interface TimerService {
    // 定时器任务
    boolean setTimer(Timer timer);
    boolean cancelTimer(Timer timer);
    String getEndTime(Timer timer);
}
