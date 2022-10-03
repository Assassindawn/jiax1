package com.bms.mapper.bms;


import com.bms.pojo.BicycleMainProperties;
import com.bms.pojo.BmsProperties;

import org.apache.ibatis.annotations.Mapper;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface BmsMapper {

      int insertBms(BmsProperties bmsProperties);
      int insertBms2(BicycleMainProperties bicycleMainProperties);

      List<BmsProperties> getAllBms(@Param("BicycleId") String BicycleId, @Param("dateTime") String dateTime);
      List<BmsProperties> getAllBms1(@Param("BicycleId") String BicycleId, @Param("dateTime") String dateTime);
      List<BmsProperties> getAllBms2(@Param("BicycleId") String BicycleId, @Param("dateTime") String dateTime);
      List<BicycleMainProperties> getAllBms3(@Param("BicycleId") String BicycleId, @Param("BatteryId") String BatteryId);
      List<BmsProperties> queryAllBms();

      List<BmsProperties> selectLast();
      List<BmsProperties> selectLast1();
      List<BmsProperties> selectLast2();
      List<BmsProperties> getDataByTime();

      List<BicycleMainProperties> getRfidCard();



}
