package com.waibaoservice.mapper;

import com.waibaoservice.pojo.Timer;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

// 为用户添加定时
@Repository
public interface TimerMapper {
    int addTimer(@Param("openid")String openid,
                 @Param("session_key")String session_key,
                 @Param("unionid")String unionId,
                 @Param("end_time")String dateStr);

    int removeTimer(@Param("openid")String openid);
    int updateTimerByOpenId(@Param("openid")String openid,
                    @Param("end_time")String dateStr);

    List<Timer> selectAllTimer();
    Timer selectTimerByOpenId(@Param("openid")String openid);
    Timer selectAll();
}
