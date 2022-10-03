package com.bms.service;

import com.bms.config.client.EmqClient;
import com.bms.pojo.enums.QosEnum;
import org.apache.log4j.LogManager;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Logger;
@Service
public class MqttService {
    @Resource
    private EmqClient emqClient;
    private static String  nowdate="yyyyMMddHHmmss";


    @Async("asyncServiceExecutor")
    public void MqttTime(){

        Date now = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat(nowdate);//可以方便地修改日期格式
        String Str = dateFormat.format(now);
        emqClient.publish("keepAlive", "time:"+Str, QosEnum.Qos0, false);
        System.out.println("返回时间");
    }
}
