package com.bms.controller.login;

import com.bms.service.login.loginService;
import com.bms.util.AppException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

@Controller
public class LoginController {



    @Resource
    private loginService loginService;

    @RequestMapping("/login")
    public String login(@RequestParam("login_name") String username,
                        @RequestParam("login_pwd") String password,
                        Model model, HttpSession session)  throws AppException {

        String s = loginService.queryusertoLogin(username, password);

        if(!"".equals(s)&&s!=null)
        {        session.setAttribute("login_name",username);
        model.addAttribute("msg","登录成功");
        loginService.updateUser(s);
        return "login";
        }
        model.addAttribute("msg","账号或者密码错误");
        return "/index";
    }

    @RequestMapping("/Toregisterbuild")
    public String register(@RequestParam("username") String username,
                           @RequestParam("password1") String password1,
                        @RequestParam("password2") String password2,
                        Model model, HttpSession session)  throws AppException {


        if(!password1.equals(password2))
        {
            model.addAttribute("msg","两次密码不相同");
            return "/register/Register";
        }
        String s = loginService.queryusertousername(username);
        if(!"".equals(s)&&s!=null)
        {
            model.addAttribute("msg","该用户名已存在");
            return "/register/Register";
        }
        loginService.innterUser(username,password1);
        model.addAttribute("msg","创建新用户成功请登录");
        return "/index";
    }

    @RequestMapping("/homepage")
    public String homepage(HttpSession session){
        return "/login";
    }

    @RequestMapping("/exit")
    public String eixt( HttpSession session){
        return "/index";
    }

    @RequestMapping("/Register")
    public String Register( HttpSession session){
        return "/register/Register";
    }


}
