package com.waibaoservice.service.impl;

import com.waibaoservice.mapper.TimerMapper;
import com.waibaoservice.pojo.Timer;
import com.waibaoservice.service.TimerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
        return false;
    }

    // 取消定时任务
    @Override
    public boolean cancelTimer(Timer timer) {
        return false;
    }
}
