package com.bms.controller.demo;

import com.bms.pojo.register;
import com.bms.service.login.loginService;

import com.bms.util.Page;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;


@Controller
@RequestMapping("/User")
public class UserController {


    @Resource
    private loginService loginService;

    @RequestMapping("/toUserindex")
    public String toUserindex(HttpSession session){
        return "/demo/User";
    }

    @RequestMapping("/toSelectUser")
    @ResponseBody
    public List<register>  toSelectUser(Page<register> page , HttpServletRequest request){
        List<register> register = loginService.queryAlluser(page,request);
        return register;
    }


    @RequestMapping("/todeleteUser")
    @ResponseBody
    public int  deleteUser(String uuid){
        int i = loginService.deleteUser(uuid);
        System.out.println("用户删除成功");
        return i;
    }

}
