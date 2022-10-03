package com.bms.service.login.impl;


import com.bms.mapper.login.LoginMapper;
import com.bms.pojo.register;
import com.bms.service.login.loginService;
import com.bms.util.Page;
import com.bms.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

@Service
public class loginServiceImpl implements loginService {


    @Autowired
    private LoginMapper loginMapper;

    public  List<register> queryAlluser(Page<register> page , HttpServletRequest request ) {
        Map<String, Object> params = page.getParams();
        String username= (String) params.get("username");
        List<register> list = loginMapper.queryAlluser(username);
        return list;
    }

    @Override
    public String queryusertoLogin(String username, String password) {
        String queryuuid = loginMapper.queryuuid(username, password);
        return queryuuid;
    }

    @Override
    public String queryusertousername(String username) {
        String queryuuid = loginMapper.queryusername(username);
        return queryuuid;
    }

    @Override
    public int updateUser(String uuid) {
        int i = loginMapper.updateUser(uuid);
        System.out.println("登录次数+1");
        return i;
    }

    @Override
    public int deleteUser(String uuid) {
        int i = loginMapper.deleteUser(uuid);
        return i;
    }

    @Override
    public int innterUser(String username,String password) {
        register register = new register();
        register.setUsername(username);
        register.setPassword(password);
        register.setUuid(StringUtils.getUUID());
        register.setVersion(0);
        register.setSerialnumber(0);
        int i = loginMapper.insertUser(register);
        return i;
    }
    @Override
    public int innterData(String data) {
        register register = new register();
        register.setPassword("555555555555");
        register.setUsername(data);
        register.setUuid(StringUtils.getUUID());
        register.setVersion(0);
        register.setSerialnumber(0);
        int i = loginMapper.insertUser(register);
        return i;
    }

    @Override
    public int innterData2(register register) {
        int i = loginMapper.insertUser(register);
        return i;
    }
}
