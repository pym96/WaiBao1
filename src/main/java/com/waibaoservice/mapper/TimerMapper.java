package com.waibaoservice.mapper;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

// 为用户添加定时
@Repository
public interface TimerMapper {
    int addTimer(@Param("openid")String openid,
                 @Param("session_key")String session_key,
                 @Param("unionid")String unionId,
                 @Param("end_time")String dateStr);

    int removeTimer(@Param("openid")String openid,
                    @Param("session_key")String session_key,
                    @Param("unionid")String unionId);
}
