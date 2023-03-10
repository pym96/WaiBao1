package com.example.waibaoservice;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.waibaoservice.mapper.UserMapper;
import com.waibaoservice.utils.WeiXinUtils.WeiXinRequestUtils;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;


@SpringBootTest(classes = WaiBao1ApplicationTests.class)
class WaiBao1ApplicationTests {

    @Test
    public void testDate() {
        Calendar cal = Calendar.getInstance();
        Date date=new Date();//现在的日期
        cal.setTime(date);
        int year=cal.get(Calendar.YEAR);//获取年
        int month = cal.get(Calendar.MONTH)+1;//获取月（月份从0开始，如果按照中国的习惯，需要加一）
        int day_moneth=cal.get(Calendar.DAY_OF_MONTH);//获取日（月中的某一天）
        int day_week=cal.get(Calendar.DAY_OF_WEEK);//获取一周内的某一天
        int hour = cal.get(Calendar.HOUR);
        int minute = cal.get(Calendar.MINUTE);
        System.out.println("现在是： "+year+"年"+month+"月"+day_moneth+"日,周"+day_week + "小时:" + hour + "分钟:" + minute);
    }

    @Test
    public void testWeiXinRequest() {
        String result = WeiXinRequestUtils.sendGet("jjjj");
        System.out.println(result);
    }

    @Test
    public void testToken() {
        //设置过期时间
        final long EXPIRE_DATE=30*60*100000;
        //token秘钥
        final String TOKEN_SECRET = "ZCEQIUBFKSJBFJH2020BQWE";
        try {
            //过期时间
            Date date = new Date(System.currentTimeMillis()+EXPIRE_DATE);
            //秘钥及加密算法
            Algorithm algorithm = Algorithm.HMAC256(TOKEN_SECRET);
            //设置头部信息
            Map<String,Object> header = new HashMap<>();
            header.put("typ","JWT");
            header.put("alg","HS256");
            //携带username，password信息，生成签名
            String token = JWT.create()
                    .withHeader(header)
                    .withClaim("username","djs")
                    .withClaim("password","123456").withExpiresAt(date)
                    .sign(algorithm);
            System.out.println(token);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
