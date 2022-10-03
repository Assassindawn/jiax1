//package com.bms.config.client;
//
//import com.alibaba.fastjson.JSON;
//import com.bms.pojo.enums.QosEnum;
//import com.bms.service.bms.bmsService;
//import org.apache.commons.codec.binary.Base64;
//import org.apache.qpid.jms.JmsConnection;
//import org.apache.qpid.jms.JmsConnectionListener;
//import org.apache.qpid.jms.message.JmsInboundMessageDispatch;
//import org.eclipse.paho.client.mqttv3.MqttException;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.stereotype.Component;
//
//import javax.annotation.PostConstruct;
//import javax.annotation.Resource;
//import javax.crypto.Mac;
//import javax.crypto.spec.SecretKeySpec;
//import javax.jms.*;
//import javax.naming.Context;
//import javax.naming.InitialContext;
//import java.net.URI;
//import java.util.*;
//import java.util.concurrent.ExecutorService;
//import java.util.concurrent.LinkedBlockingQueue;
//import java.util.concurrent.ThreadPoolExecutor;
//import java.util.concurrent.TimeUnit;
//
//@Component
//public class AmqpClient {
//    @Resource
//    private EmqClient emqClient;
//    @Resource
//    private com.bms.service.bms.bmsService bmsService;
//
//    private final static Logger logger = LoggerFactory.getLogger(AmqpClient.class);
//
//
//    //业务处理异步线程池，线程池参数可以根据您的业务特点调整，或者您也可以用其他异步方式处理接收到的消息。
//    private final static ExecutorService executorService = new ThreadPoolExecutor(
//        Runtime.getRuntime().availableProcessors(),
//        Runtime.getRuntime().availableProcessors() * 2, 60, TimeUnit.SECONDS,
//        new LinkedBlockingQueue(50000));
//
//
//
//    TimerTask timerTask = new TimerTask() {
//
//    };
//    // 计时器
//
//
//
//    @PostConstruct
//    public  void  init() throws  Exception {
//
//
//
//        while (true) {
//            emqClient.publish("/iot/1206/wsy", "{\"target\":\"beep\",\"value\":1}", QosEnum.Qos0, false);
//            logger.info("1233211234567");
//
//        }
//
//
//    }
//
//
//}
