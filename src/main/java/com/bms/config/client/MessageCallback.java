package com.bms.config.client;

import com.alibaba.fastjson.JSON;
import com.bms.service.Mqtt.AsyncService;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.MqttCallback;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.text.MessageFormat;
import java.util.HashMap;
import java.util.Map;

@Component
public class MessageCallback implements MqttCallback {
    @Resource
    private EmqClient emqClient;

    @Autowired
    private bmsMessage bmsMessage;

   @Autowired
    AsyncService asyncService;


    private static final Logger log= LoggerFactory.getLogger(MqttCallback.class);
    public static Map<String, Object> map = new HashMap<>();



//    丢失对服务端之后进行的回调
    @Override
    public void connectionLost(Throwable throwable) {
//    资源的清理，重连，可以运用Emqclient 里面的重连方法
        emqClient.reConnect();
        log.error("丢失对服务端连接");
    }

//    应用收到信息后触发的回调
    @Override
    public void messageArrived(String topic, MqttMessage message) throws Exception {


        String msg=new String(message.getPayload());
        Map maps = (Map) JSON.parse(msg);
        String theMsg = MessageFormat.format("{0}", msg);
        String clientId=  maps.get("clientid").toString();
        log.info("订阅者订阅到了消息,topic={},messageid={},qos={},Payliad={}",
                topic,
                message.getId(),
                message.getQos(),
                theMsg
        );


        //返回数据
        if(topic.equals("Device1")) {

            if(maps.get("Temperature")!=null && maps.get("ElectricQuantity")!=null) {
               bmsMessage.toBms1(message);
            }
        }


        //历史数据
        if(topic.equals("Device2")) {

            if (maps.get("Temperature") != null && maps.get("ElectricQuantity") != null) {
                bmsMessage.toBms2(message);
            }
        }


        //时间返回
        if(topic.equals("Device3")){
            asyncService.executeAsync();
        }


        //返回RfidMessage
        if(topic.equals("Device4")){
            bmsMessage.toBms4(message);

        }


        //返回电机信息
        if(topic.equals("device7")){
            bmsMessage.toBms5(message);

        }


        if (topic.endsWith("disconnected")) {
            bmsMessage.toBms6(message);
            log.info("客户端已掉线：{}",clientId);
        } else {
            bmsMessage.toBms7(message);
            log.info("客户端已上线：{}",clientId);
        }




    }


//    发布者发布完成之后产生的回调
    @Override
    public void deliveryComplete(IMqttDeliveryToken token) {
        String[] topics = token.getTopics();
        String bmsmsg= null;
        try {
            bmsmsg = new String(token.getMessage().getPayload());
        } catch (MqttException e) {
            e.printStackTrace();
        }

        log.info("消息发布完成,topic={},message={}",topics,bmsmsg);

    }


    private String extractCommandData(JsonObject jsonObject, String dataPart) {
        JsonElement element = jsonObject.get(dataPart);
        if (element != null) {
            return element.getAsString();
        } else {
            return null;
        }
    }


}
