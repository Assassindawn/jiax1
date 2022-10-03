package com.bms.service.login;


import com.bms.pojo.register;
import com.bms.util.Page;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public interface loginService {
    List<register> queryAlluser(Page<register> page , HttpServletRequest request);

    String queryusertoLogin(String username , String password);

    String queryusertousername(String username);

    int updateUser(String uuid);

    int deleteUser(String uuid);

    int innterUser(String username,String password);

    int innterData(String data);

    int innterData2(register register);

}
