package com.bms.service.bms.lmpl;

import com.bms.mapper.bms.BmsMapper;
import com.bms.pojo.BicycleMainProperties;
import com.bms.pojo.BmsProperties;
import com.bms.pojo.ElectricMachineryProperties;
import com.bms.service.bms.bmsService;
import com.bms.util.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Service
public class bmsServicelmpl implements bmsService {
    private static final String  nowdate="yyyyMMddHHmmss";

    @Autowired
    private BmsMapper bmsMapper;
    @Override
    public int interHistoryData(Map map) {
        BicycleMainProperties bicycleMainProperties= getRfidCard().get(0);
        BmsProperties bmsProperties= BmsProperties.builder()
                .BicycleId(bicycleMainProperties.getBicycleId())
                .Temperature(map.get("Temperature").toString())
                .ElectricQuantity(map.get("ElectricQuantity").toString())
                .Light(map.get("Light").toString())
                .dateTime(map.get("dateTime").toString())
                .build();
         int i=bmsMapper.insertBms(bmsProperties);
        return i;
    }

    @Override
    public int UpdateData(Map map) {
        BicycleMainProperties bicycleMainProperties= BicycleMainProperties.builder()
                .BicycleId(map.get("RFID").toString())
                .build();
        int i=bmsMapper.insertBms2(bicycleMainProperties);


        return i;
    }

    @Override
    public void interData(Map map) {
        Date now = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat(nowdate);//可以方便地修改日期格式
        String Str = dateFormat.format(now);
        BicycleMainProperties bicycleMainProperties= getRfidCard().get(0);
        BmsProperties bmsProperties= BmsProperties.builder()
                .BicycleId(bicycleMainProperties.getBicycleId())
                .Temperature(map.get("Temperature").toString())
                .ElectricQuantity(map.get("ElectricQuantity").toString())
                .Light(map.get("Light").toString())
                .dateTime(Str)
                .build();

        bmsMapper.insertBms(bmsProperties);

    }
//s
    @Override
    public void interRpm(Map map) {
        Date now = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat(nowdate);//可以方便地修改日期格式
        String Str = dateFormat.format(now);
        BicycleMainProperties bicycleMainProperties= getRfidCard().get(0);
        ElectricMachineryProperties electricMachineryProperties= ElectricMachineryProperties.builder()
                .ElectricMachineryId("1")
                .BicycleId(bicycleMainProperties.getBicycleId())
                .rpm(map.get("rpm").toString())
                .dateTime(Str)
                .build();

        bmsMapper.insertRpm(electricMachineryProperties);

    }



    @Override
    public List<BmsProperties> getAllBms(Page<BmsProperties> page, HttpServletRequest request) {
        Map<String, Object> params = page.getParams();
        String BicycleId= (String) params.get("BicycleId");
        String dateTime= (String) params.get("dateTime");
        List<BmsProperties> allBms = bmsMapper.getAllBms(BicycleId,dateTime);
        return allBms;
    }

    @Override
    public List<BmsProperties> getAllBms1(Page<BmsProperties> page, HttpServletRequest request) {
        Map<String, Object> params = page.getParams();
        String BicycleId= (String) params.get("BicycleId");
        String dateTime= (String) params.get("dateTime");
        List<BmsProperties> allBms = bmsMapper.getAllBms1(BicycleId,dateTime);
        return allBms;
    }


    @Override
    public List<BmsProperties> getAllBms2(Page<BmsProperties> page, HttpServletRequest request) {
        Map<String, Object> params = page.getParams();
        String BicycleId= (String) params.get("BicycleId");
        String dateTime= (String) params.get("dateTime");
        List<BmsProperties> allBms = bmsMapper.getAllBms2(BicycleId,dateTime);
        return allBms;
    }

    @Override
    public List<BicycleMainProperties> getAllBms3(Page<BicycleMainProperties> page, HttpServletRequest request) {
        Map<String, Object> params = page.getParams();
        String BicycleId= (String) params.get("BicycleId");
        String BatteryId= (String) params.get("BatteryId");
        List<BicycleMainProperties> allBms = bmsMapper.getAllBms3(BicycleId,BatteryId);
        return allBms;
    }

    @Override
    public List<BmsProperties> getLast(Page<BmsProperties> page, HttpServletRequest request) {
        List<BmsProperties> i = bmsMapper.selectLast();
        return i;

    }

    @Override
    public List<BmsProperties> getLast1(Page<BmsProperties> page, HttpServletRequest request) {
        List<BmsProperties> i = bmsMapper.selectLast1();
        return i;
    }

    @Override
    public List<BmsProperties> getLast2(Page<BmsProperties> page, HttpServletRequest request) {
        return null;
    }

    @Override
    public List<BmsProperties> getDataByTime(Page<BmsProperties> page, HttpServletRequest request) {
        List<BmsProperties> i = bmsMapper.getDataByTime();
        return i;
    }

    @Override
    public List<BicycleMainProperties> getRfidCard() {


        List<BicycleMainProperties> i = bmsMapper.getRfidCard();
        return i;
    }
    @Override
    public List<ElectricMachineryProperties> getRpm(Page<BmsProperties> page, HttpServletRequest request) {


        List<ElectricMachineryProperties> i = bmsMapper.getRpm();
        return i;
    }



}
