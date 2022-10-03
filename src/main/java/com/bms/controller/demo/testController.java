package com.bms.controller.demo;

import com.bms.pojo.BmsProperties;
import com.bms.service.bms.bmsService;
import com.bms.util.Page;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
public class testController {
    @Resource
    private com.bms.service.bms.bmsService bmsService;


    @RequestMapping("/toSelectBms")
    @ResponseBody
    public List<BmsProperties> toSelectBms(Page<BmsProperties> page , HttpServletRequest request){
        List<BmsProperties> allBms = bmsService.getAllBms(page,request);
        return allBms;
    }
}
