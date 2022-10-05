package com.bms.config.client;

import com.bms.pojo.MqttProperties;
import com.bms.pojo.enums.QosEnum;
import org.eclipse.paho.client.mqttv3.*;
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

@Component
public class EmqClient {
    private static final Logger log= LoggerFactory.getLogger(EmqClient.class);
    private  IMqttClient mqttClient;

    @Autowired
    private MqttProperties mqttProperties;
    @Autowired
    private  MessageCallback mattCallback;



//    这个注解为启动开启下面这个方法
    @PostConstruct
    public  void  init() throws MqttException {
        MqttClientPersistence mqttClientPersistence=new MemoryPersistence();
     try {
         mqttClient = new MqttClient(
                 mqttProperties.getBrokerUrl(),
                 mqttProperties.getClientID(),
                 mqttClientPersistence
         );
         log.info("客户端初始化成功");
     }catch (MqttException e){
         log.error("客户端初始化失败");
     }
        connect(mqttProperties.getUsername(),mqttProperties.getPassword());
        //subscribe("Device1",QosEnum.Qos0);
        subscribe("Device2",QosEnum.Qos0);
        subscribe("Device3",QosEnum.Qos0);
        subscribe("Device4",QosEnum.Qos0);
        subscribe("device7",QosEnum.Qos0);
        subscribe("$SYS/brokers/emqx@127.0.0.1/clients/ADMIN1/connected",QosEnum.Qos0);
        subscribe("$SYS/brokers/emqx@127.0.0.1/clients/ADMIN1/disconnected",QosEnum.Qos0);

    }

    public void connect(String username,String password)
    {
        MqttConnectOptions options=new MqttConnectOptions();
        options.setAutomaticReconnect(true);
        options.setUserName(username);
        options.setPassword(password.toCharArray());
        options.setCleanSession(true);

        mqttClient.setCallback(mattCallback);
        try {
          mqttClient.connect(options);
            System.out.println("mqtt客户端连接服务端成功");
        } catch (Exception e) {
           log.error("mqtt客户端连接服务端失败");
        }

    }

//    断开连接，这个注解为关闭时开启下面这个方法
    @PreDestroy
    public void disconnect(){
        try {
            mqttClient.disconnect();

        } catch (MqttException e) {
            log.error("断开连接产生异常",e.getMessage());
        }
    }

//    重新连接
    public void reConnect(){
        try {
            mqttClient.reconnect();
        } catch (MqttException e) {
           log.error("重连失败",e.getMessage());
        }
    }

//    发布消息
    public void publish(String topic, String msg, QosEnum qos, boolean retain){
        MqttMessage mqttMessage=new MqttMessage();
        mqttMessage.setPayload(msg.getBytes());
        mqttMessage.setQos(qos.value());
        mqttMessage.setRetained(retain);
        try {
            mqttClient.publish(topic,mqttMessage);
        } catch (MqttException e) {
            log.error("发布信息失败",e.getMessage());
        }
    }

//    订阅主题
    public void subscribe(String topicFilers,QosEnum qos){

        try {
            mqttClient.subscribe(topicFilers,qos.value());
        } catch (MqttException e) {
            log.error("订阅主题失败",e.getMessage());
        }
    }

//    取消订阅
    public void unsubscribe(String topicFilers){
        try {
            mqttClient.unsubscribe(topicFilers);
        } catch (MqttException e) {
            log.error("取消订阅失败",e.getMessage());
        }
    }



}
