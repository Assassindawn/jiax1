package com.bms.service.Mqtt.impl;

import com.bms.config.client.EmqClient;
import com.bms.pojo.enums.QosEnum;
import com.bms.service.Mqtt.AsyncService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.Date;

@Slf4j
@Service
public class AsyncServiceImpl implements AsyncService {
    @Resource
    private EmqClient emqClient;
    private static String  nowdate="yyyyMMddHHmmss";


    //异步多线程调用
    @Async("asyncServiceExecutor")
    public void executeAsync() {
        log.info("start executeAsync");
        try{
            Date now = new Date();
            SimpleDateFormat dateFormat = new SimpleDateFormat(nowdate);
            String Str = dateFormat.format(now);
            emqClient.publish("keepAlive", "time:"+Str, QosEnum.Qos0, false);
            log.info("设备已上线，返回时间");
        }catch (Exception e){
            e.printStackTrace();
        }

        log.info("end executeAsync");
    }

    //异步多线程调用
    @Async("asyncServiceExecutor")
    public void executeAsync2() {
        log.info("start executeAsync");
        try{

        }catch (Exception e){
            e.printStackTrace();
        }

        log.info("end executeAsync");
    }



    }