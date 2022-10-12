package com.bms.config.client;

import com.alibaba.fastjson.JSON;
import com.bms.service.bms.bmsService;
import org.eclipse.paho.client.mqttv3.MqttCallback;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.Map;

@Component
public class bmsMessage {
@Resource
private bmsService bmsService;

    private static final Logger log= LoggerFactory.getLogger(MqttCallback.class);

    private static String  nowdate="yyyyMMddHHmmss";


    public  boolean toBms1(MqttMessage message)  throws IOException {
        log.info("信息存储至数据库");

        String msg= new String(message.getPayload());
        Map maps = (Map)JSON.parse(msg);
        bmsService.interData(maps);
        return true;
    }
    public  boolean toBms2(MqttMessage message)  throws IOException {
        log.info("历史信息存储至数据库");

        String msg= new String(message.getPayload());
        Map maps = (Map)JSON.parse(msg);
        bmsService.interHistoryData(maps);
        return true;
    }


    //RFID卡信息
    public  boolean toBms4(MqttMessage message)  throws IOException {
        log.info("RFID信息存储至数据库");

        String bmsmsg= new String(message.getPayload());
        Map maps = (Map)JSON.parse(bmsmsg);
        bmsService.UpdateData(maps);

        return true;
    }

    //电机信息
    public  boolean toBms5(MqttMessage message)  throws IOException {
        log.info("电机信息存储至数据库");

        String bmsmsg= new String(message.getPayload());
        Map maps = (Map)JSON.parse(bmsmsg);
        bmsService.interRpm(maps);

        return true;
    }
    //上线
    public  boolean toBms6(MqttMessage message)  throws IOException {

        String bmsmsg= new String(message.getPayload());
        Map maps = (Map)JSON.parse(bmsmsg);
        bmsService.interUp(maps);

        return true;
    }
    //信息
    public  boolean toBms7(MqttMessage message)  throws IOException {

        String bmsmsg= new String(message.getPayload());
        Map maps = (Map)JSON.parse(bmsmsg);
        bmsService.interDown(maps);

        return true;
    }





}



