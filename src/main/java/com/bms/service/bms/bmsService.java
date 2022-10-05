package com.bms.service.bms;

import com.bms.pojo.BicycleMainProperties;
import com.bms.pojo.BmsProperties;
import com.bms.pojo.ElectricMachineryProperties;
import com.bms.pojo.EmqClientProperties;
import com.bms.util.Page;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

public interface bmsService {

    int interHistoryData(Map map);
    int UpdateData(Map map);
    void interData(Map map);
    void interRpm(Map map);
    void interUp(Map map);
    void interDown(Map map);


    List<BmsProperties> getAllBms(Page<BmsProperties> page , HttpServletRequest request);
    List<BmsProperties> getAllBms1(Page<BmsProperties> page , HttpServletRequest request);
    List<BmsProperties> getAllBms2(Page<BmsProperties> page , HttpServletRequest request);
    List<BicycleMainProperties> getAllBms3(Page<BicycleMainProperties> page , HttpServletRequest request);
    List<EmqClientProperties> getAllEmq(Page<EmqClientProperties> page , HttpServletRequest request);

    List<BmsProperties> getLast(Page<BmsProperties> page, HttpServletRequest request);
    List<BmsProperties> getLast1(Page<BmsProperties> page, HttpServletRequest request);
    List<BmsProperties> getLast2(Page<BmsProperties> page, HttpServletRequest request);
    List<BmsProperties> getDataByTime(Page<BmsProperties> page, HttpServletRequest request);
    List<BicycleMainProperties> getRfidCard();
    List<ElectricMachineryProperties> getRpm(Page<BmsProperties> page, HttpServletRequest request);

}
