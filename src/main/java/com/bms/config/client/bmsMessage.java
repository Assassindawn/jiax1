package com.bms.config.client;

import com.alibaba.fastjson.JSON;
import com.bms.mapper.login.LoginMapper;
import com.bms.service.bms.bmsService;
import com.bms.service.login.loginService;
import com.google.gson.JsonParser;
import org.eclipse.paho.client.mqttv3.MqttCallback;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.couchbase.CouchbaseProperties;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

@Component
public class bmsMessage {
@Resource
private bmsService bmsService;

    private static final Logger log= LoggerFactory.getLogger(MqttCallback.class);

    private static String  nowdate="yyyyMMddHHmmss";


    public  boolean toBms2(MqttMessage message)  throws IOException {
        log.info("信息存储至数据库");

        String msg= new String(message.getPayload());
        Map maps = (Map)JSON.parse(msg);
        bmsService.innterData3(maps);
        return true;
    }
    public  boolean toBms3(MqttMessage message)  throws IOException {
        log.info("历史信息存储至数据库");

        String msg= new String(message.getPayload());
        Map maps = (Map)JSON.parse(msg);
        bmsService.innterHistoryData(maps);
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






}



