package com.waibaoservice.utils.MqttUtils;

import org.apache.commons.lang3.StringUtils;
import org.eclipse.paho.client.mqttv3.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.HashMap;
import java.util.Map;
import org.eclipse.paho.client.mqttv3.IMqttMessageListener;



/***
 *  MQTT 工具类
 * @author YiMing
 * @date 2023/2/22
 * @since 1.0.0
 */
public class MqttUtils {

    /**
     @Param MQTTHOST MQTT服务器端口号
     */
    private static String MQTTHOST = "test.ranye-iot.net";
    private static String MQTTPORT = "1883";

//    private static Logger logger = LoggerFactory.getLogger(MqttUtils.class);

    private MqttClient mqttClient;
    private MqttConnectOptions mqttConnectOptions;

    public void mqttPrintTest() throws MqttException {
        mqttClient = new MqttClient(MQTTHOST,MQTTPORT);

        System.out.println(mqttClient.getClientId());
        System.out.println("Finish connect");

        mqttClient.subscribe("MA/TEST",1);


    }

    public static void main(String[] args) {
        try {
            MqttUtils mqttUtils = new MqttUtils();
            mqttUtils.mqttPrintTest();
        } catch (MqttException e) {
            throw new RuntimeException(e);
        }
    }



    /**
     * 创建连接
     * @param configParams
     * @throws Exception
     */
//    public   void  createIotDataSource(Map<String, String> configParams) throws  Exception {
//        //访问ip：port  tcp://   or  ssl://
//        String host = configParams.get("test.ranye-iot.net");
//        //客户端id 保持唯一
//        String clientId = configParams.get("MQTT_CLIENTID");
//        //用户名
//        String userName = configParams.get("MQTT_USERNAME");
//        //密码
//        String password = configParams.get("MQTT_PASSWORD");
//        //ssl  验证时是双向验证还是单向验证
//        String sslType=configParams.get("MQTT_SSLTYPE");
//
//
//        //缓存两种模式 存在内存 文件  设置成null 缓存在内存中 最多缓存65535条信息
//        //ScheduledExecutorService 可以设置线程池大小 默认10；发布消息方法是异步的
//        this.mqttClient = new MqttClient(host,clientId,null);//new MqttDefaultFilePersistence()
//        mqttConnectOptions = new MqttConnectOptions();
//        // 设置是否清空session,这里如果设置为false表示服务器会保留客户端的连接记录，
//        // 这里设置为true表示每次连接到服务器都以新的身份连接
//        mqttConnectOptions.setCleanSession(false);
//        // mqttConnectOptions.sto
//        // 设置超时时间 s
//        mqttConnectOptions.setConnectionTimeout(20);
//        // 设置会话心跳时间
//        mqttConnectOptions.setKeepAliveInterval(10);
//        mqttConnectOptions.setAutomaticReconnect(true);//设置自动重连
//        //setWill方法，如果项目中需要知道客户端是否掉线可以调用该方法。设置最终端口的通知消息
//        // mqttConnectOptions.setWill("sec", "close".getBytes(), 2, true);
//        if("two".equals(sslType)){
//            //服务端证书路径
//            String rootCrtPath=configParams.get("MQTT_SSLROOT_CRTPATH");
//            //客户端证书路径
//            String clientCrtPath=configParams.get("MQTT_SSLCLIENT_CRTPATH");
//            //客户端密匙路径
//            String clientKeyPath=configParams.get("MQTT_SSLCLIENT_KEYPATH");
//            //密匙加密密码
//            String clientPassword=configParams.get("MQTT_SSLPASSWORD");
//            //ssl 协议版本 一般看mqtt服务端broker设置  不设置默认为TLSv1.1
//            String sslProtocol=configParams.get("MQTT_SSLPROTOCOL");
//            logger.info("sslProtocol======{}",sslProtocol);
//            mqttConnectOptions.setSocketFactory(SslUtil.getSocketFactory(rootCrtPath, clientCrtPath, clientKeyPath, clientPassword,sslProtocol));
//
//        }else if("one".equals(sslType)){
//            String rootCrtPath=configParams.get("MQTT_SSLROOT_CRTPATH");
//            String sslProtocol=configParams.get("MQTT_SSLPROTOCOL");
//
//            mqttConnectOptions.setSocketFactory(SslUtil.getSocketFactorySingle(rootCrtPath,sslProtocol));
//        }
//        if(StringUtils.isNotBlank(userName)){
//            mqttConnectOptions.setUserName(userName);
//        }
//        if (StringUtils.isNotBlank(password)){
//            mqttConnectOptions.setPassword(password.toCharArray());
//        }
//        //mqttConnectOptions.setWill();   //可以设置断线发送、接收消息
//        mqttClient.connect(mqttConnectOptions);
//        logger.info("mqtt 连接成功！！！");
//
//
//    }
//

    /**
     * 完成连接时 主要用于断开连接时 重新订阅
     * 网上说 将 mqttConnectOptions.setCleanSession(false); 就可以继续接收，试了下没有效果，用的是开源版
     * 的emq测试的，不知道企业版是否有效
     *
     * testTopic/#   #多层通配符    +单层通配符
     * topicFilter
     * @param topicFilter
     * @param iotMqttMessageListener
     * @throws Exception
     */
//    public void setReSubscribe(String topicFilter, IMqttMessageListener iotMqttMessageListener) throws Exception {
//        //先订阅一次
//        mqttClient.subscribe(topicFilter,iotMqttMessageListener);
//        //断线重连
//        mqttClient.setCallback(new MqttCallbackExtended() {
//            @Override
//            public void connectComplete(boolean reconnect, String serverURI) {
//                if(reconnect){
//                    try {
//                        mqttClient.subscribe(topicFilter,iotMqttMessageListener);
//                        logger.info("mqtt重新建立连接后,topic={} 重新订阅！",topicFilter);
//                    } catch (MqttException e) {
//                        e.printStackTrace();
//                    }
//                }
//            }
//
//            @Override
//            public void connectionLost(Throwable cause) {
//
//            }
//
//            @Override
//            public void messageArrived(String topic, MqttMessage message) throws Exception {
//
//            }
//
//            @Override
//            public void deliveryComplete(IMqttDeliveryToken token) {
//
//            }
//        });
//    }

    /**
     * 取消订阅
     *
     * @param topics
     * @throws
     */
    public void unsubscribe(String[] topics)  throws Exception{
        mqttClient.unsubscribe(topics);


    }
    /**
     * 是否处于连接状态
     * @return
     */
    public boolean isConnected() {
        return mqttClient.isConnected();
    }


    /**
     * 发布数据
     * @param
     * @param data
     * @throws Exception
     */
//    public void publish( String publicLiveTopic, String data)   throws Exception{
//        MqttMessage mqttMessage = new MqttMessage();
//        mqttMessage.setPayload(data.getBytes("UTF-8"));
//        //QoS：发布消息的服务质量，即：保证消息传递的次数（消费者收到的次数）
//        //0：最多一次，即：<=1；每个消息只发一次，也不会缓存下来。
//        //1：至少一次，即：>=1；一直发送确保消费者至少收到一次，发送失败会缓存下来。
//        //2：一次，即：=1       一直发送确保消费者只能收到一次；发送失败会缓存下来 。
//        mqttMessage.setQos(1);
//        //消费者断开连接后是否接受离线消息
//        mqttMessage.setRetained(true);
//        mqttClient.publish(publicLiveTopic,mqttMessage);
//        logger.info("topic:{} send  dataSize {}kb ",publicLiveTopic,data.length()/1024.0);
//
//    }
//
//    /**
//     * 断开连接
//     */
//    public void destroy() {
//        try {
//            this.mqttClient.disconnect();
//            logger.info("mqtt 手动断开连接！！！");
//        } catch (MqttException e) {
//            //e.printStackTrace();
//            logger.error("手动断开连接报错error={}",e.getMessage());
//        }
//    }



    /**
     * 设置连接
     * @param args
     */
//    public static void main(String[] args) {
//        MqttUtils xbMqttDataUtil = new MqttUtils();
//        Map<String, String> configParams = new HashMap<>();
//        String host = "tcp://10.21.80.11:1883";
//        //String host="ssl://10.251.80.151:1883";
//        configParams.put("MQTT_HOST", host);
//        configParams.put("MQTT_CLIENTID", "random");
//        configParams.put("MQTT_USERNAME", "admin");
//        configParams.put("MQTT_PASSWORD", "123456");
////        configParams.put("MQTT_SSLTYPE","two");
////
////        configParams.put("MQTT_SSLROOT_CRTPATH","./root.crt");
////        configParams.put("MQTT_SSLCLIENT_CRTPATH","./client.crt");
////        configParams.put("MQTT_SSLCLIENT_KEYPATH","./client.key");
////        configParams.put("MQTT_SSLPASSWORD","123456");
////        configParams.put("MQTT_SSLPROTOCOL","TLSv1.1");
//
//
//        try {
//
//            xbMqttDataUtil.createIotDataSource(configParams);
//            MqttMessageListener mqttMessageListener = new MqttMessageListener();
//            xbMqttDataUtil.setReSubscribe("test/command/#", new MqttMessageListener());
//
//
//            while (true) {
//                xbMqttDataUtil.publish("test/", "hello mqtt!");
//                Thread.sleep(5000);
//            }
//
//
//        } catch (Exception e) {
//            e.printStackTrace();
//        }

//    }
}
