package com.example.waibaoservice;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.waibaoservice.utils.WeiXinUtils.WeiXinRequestUtils;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;


@SpringBootTest(classes = WaiBao1ApplicationTests.class)
class WaiBao1ApplicationTests {

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
