package com.waibaoservice.utils.WeiXinUtils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;


/**
 * @author DJS
 * Date create 21:13 2023/3/9
 * Modified By DJS
 **/

public class WeiXinRequestUtils {
    private static final String appId = "wx935e54762f136094";
    private static final String appSecret = "f647aa91285a8a64efc3d70e621c0154";
    private static final String grant_type = "authorization_code";

    private WeiXinRequestUtils() {}

    // 发送GET请求
    public static String sendGet(String js_code) {
        if (js_code == null) {
            System.out.println("js_code is null");
            return null;
        }
        String url = "https://api.weixin.qq.com/sns/jscode2session?appid="+ appId + "&secret=" + appSecret + "&js_code="+ js_code+ "&grant_type=" + grant_type ;
        String result = null;
        System.out.println("url : " + url);
        try {
            // 与微信小程序后台进行连接
            URL realUrl = new URL(url);
            final URLConnection connection = realUrl.openConnection();
            connection.connect();
            // 获取字符输入流
            BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            // 读取数据
            String line;
            StringBuilder builder = new StringBuilder();
            while ((line = reader.readLine()) != null) {
                builder.append(line);
            }
            result = builder.toString();
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }
}
