package com.bms.mapper.login;


import com.bms.pojo.register;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface LoginMapper {



      List<register> queryAlluser(@Param("username") String username);

      String queryuuid(@Param("username") String username, @Param("password")String password);

      String queryusername(@Param("username") String username);

      int updateUser(@Param("uuid") String uuid);

      int deleteUser(@Param("uuid") String uuid);

      int insertUser(register register);

}
