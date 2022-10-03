package com.bms.service.bms;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

import com.bms.pojo.BicycleMainProperties;
import com.bms.pojo.BmsProperties;
import com.bms.util.Page;

public interface bmsService {

    int innterHistoryData(Map map);
    int UpdateData(Map map);
    int innterData3(Map map);
    List<BmsProperties> getAllBms(Page<BmsProperties> page , HttpServletRequest request);
    List<BmsProperties> getAllBms1(Page<BmsProperties> page , HttpServletRequest request);

    List<BmsProperties> getAllBms2(Page<BmsProperties> page , HttpServletRequest request);
    List<BicycleMainProperties> getAllBms3(Page<BicycleMainProperties> page , HttpServletRequest request);

    List<BmsProperties> getLast(Page<BmsProperties> page, HttpServletRequest request);
    List<BmsProperties> getLast1(Page<BmsProperties> page, HttpServletRequest request);
    List<BmsProperties> getLast2(Page<BmsProperties> page, HttpServletRequest request);
    List<BmsProperties> getDataByTime(Page<BmsProperties> page, HttpServletRequest request);
    List<BicycleMainProperties> getRfidCard();
}
