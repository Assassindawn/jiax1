package com.bms.controller.demo;

import com.bms.pojo.BicycleMainProperties;
import com.bms.pojo.BmsProperties;
import com.bms.pojo.ElectricMachineryProperties;
import com.bms.service.bms.bmsService;
import com.bms.util.Page;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequestMapping("/Bms")
public class bmsController {
    @Resource
    private bmsService bmsService;


    @RequestMapping("/toBmsindex")
    public String toBmsindex(HttpSession session) {
        return "/demo/Bms";
    }

    @RequestMapping("/toBms2")
    public String toBms2(HttpSession session) {
        return "/demo/BMS1";
    }

//查询1机的数据
    @RequestMapping("/toSelectBms")
    @ResponseBody
    public List<BmsProperties> toSelectBms(Page<BmsProperties> page , HttpServletRequest request){
        List<BmsProperties> allBms = bmsService.getAllBms(page,request);
        return allBms;
        }
//查询2机的数据
    @RequestMapping("/toSelectBms1")
    @ResponseBody
    public List<BmsProperties> toSelectBms1(Page<BmsProperties> page , HttpServletRequest request){
        List<BmsProperties> allBms = bmsService.getAllBms1(page,request);
        return allBms;
    }

    @RequestMapping("/toSelectBms2")
    @ResponseBody
    public List<BmsProperties> toSelectBms2(Page<BmsProperties> page , HttpServletRequest request){
        List<BmsProperties> allBms = bmsService.getAllBms2(page,request);
        return allBms;
    }

    @RequestMapping("/toSelectBms3")
    @ResponseBody
    public List<BicycleMainProperties> toSelectBms3(Page<BicycleMainProperties> page , HttpServletRequest request){
        List<BicycleMainProperties> allBms = bmsService.getAllBms3(page,request);
        return allBms;
    }


    @RequestMapping("/toSelectLast")
    @ResponseBody
    public List<BmsProperties> toSelectLast(Page<BmsProperties> page , HttpServletRequest request){
        List<BmsProperties> i=bmsService.getLast(page,request);
        return i;

    }
    @RequestMapping("/toSelectLast1")
    @ResponseBody
    public List<BmsProperties> toSelectLast1(Page<BmsProperties> page , HttpServletRequest request) {
        List<BmsProperties> i = bmsService.getLast1(page, request);
        return i;
    }
    @RequestMapping("/toSelectLast2")
    @ResponseBody
    public List<BmsProperties> toSelectLast2(Page<BmsProperties> page , HttpServletRequest request) {
        List<BmsProperties> i = bmsService.getLast2(page, request);
        return i;
    }

    @RequestMapping("/toSelectLastByTime")
    @ResponseBody
    public List<BmsProperties> toSelectLastByTime(Page<BmsProperties> page , HttpServletRequest request) {
        List<BmsProperties> i = bmsService.getDataByTime(page, request);
        return i;
    }

    @RequestMapping("/toSelectLastRpm")
    @ResponseBody
    public List<ElectricMachineryProperties> toSelectLastRpm(Page<BmsProperties> page , HttpServletRequest request) {
        List<ElectricMachineryProperties> i = bmsService.getRpm(page, request);
        return i;
    }
}
