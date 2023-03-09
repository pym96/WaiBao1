package com.waibaoservice.controller;

import com.waibaoservice.pojo.Timer;
import com.waibaoservice.service.TimerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author DJS
 * Date create 23:57 2023/3/9
 * Modified By DJS
 **/

@RestController
@RequestMapping("/timer")
public class TimerController {

    @Autowired
    TimerService timerService;

    // 为用户添加定时器功能
    @PostMapping("/setTimer")
    @ResponseBody
    public boolean setTimer(@RequestBody Timer timer) {
        if (timer != null) {
            System.out.println(timer);
            return timerService.setTimer(timer);
        }
        return false;
    }

    // 为用户取消定时功能
    @PostMapping("/removeTimer")
    @ResponseBody
    public boolean removeTimer(@RequestBody Timer timer) {
        if (timer != null) {
            return timerService.cancelTimer(timer);
        }
        return false;
    }

}
