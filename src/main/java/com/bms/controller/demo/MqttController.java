package com.bms.controller.demo;

import com.bms.config.client.EmqClient;
import com.bms.pojo.enums.QosEnum;
import org.eclipse.paho.client.mqttv3.MqttCallback;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/Mqtt")
public class MqttController {
static boolean i=false;
static boolean k=false;
static boolean m=false;
static boolean n=false;
    @Autowired
    private EmqClient emqClient;

    private static final Logger log= LoggerFactory.getLogger(MqttController.class);


    @RequestMapping("/toRemote")
    public String toRemote(HttpSession session){
        return "/demo/Remote";
    }


    @RequestMapping("/getHot")
    @ResponseBody
    public Boolean HotController(HttpSession session){
        if(i==false) {
            emqClient.publish("/iot/1206/wsy", "{\"target\":\"hotfan\",\"value\":1}", QosEnum.Qos0, false);
            i=true;
            log.info("发送指令热机开");
        }
        else{
            emqClient.publish("/iot/1206/wsy", "{\"target\":\"hotfan\",\"value\":0}", QosEnum.Qos0, false);
            i=false;
            log.info("发送指令热机关");
        }


        return true;
    }

    @RequestMapping("/getCold")
    @ResponseBody
    public Boolean getCold(HttpSession session){
        if(k==false) {
            emqClient.publish("/iot/1206/wsy", "{\"target\":\"coldfan\",\"value\":1}", QosEnum.Qos0, false);
            k=true;
            log.info("发送指令冷机开");
        }
        else {
            emqClient.publish("/iot/1206/wsy", "{\"target\":\"coldfan\",\"value\":0}", QosEnum.Qos0, false);
            k = false;
            log.info("发送指令冷机关");
        }
        return true;
    }

    @RequestMapping("/getBattery")
    @ResponseBody
    public Boolean getBattery(HttpSession session){
        if(m==false) {
            emqClient.publish("/iot/1206/wsy", "{\"target\":\"battery\",\"value\":1}", QosEnum.Qos0, false);
            m=true;
            log.info("电池充电开");
        }
        else{
            emqClient.publish("/iot/1206/wsy", "{\"target\":\"battery\",\"value\":0}", QosEnum.Qos0, false);
            m=false;
            log.info("电池充电停止");
        }


        return true;
    }

    @RequestMapping("/getBeep")
    @ResponseBody
    public Boolean getBeep(HttpSession session){
        if(n==false) {
            emqClient.publish("/iot/1206/wsy", "{\"target\":\"beep\",\"value\":1}", QosEnum.Qos0, false);
            n=true;
            log.info("蜂鸣器开");
        }
        else{
            emqClient.publish("/iot/1206/wsy", "{\"target\":\"beep\",\"value\":0}", QosEnum.Qos0, false);
            n=false;
            log.info("蜂鸣器关");
        }


        return true;
    }




}
