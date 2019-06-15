package com.qianfeng.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.qianfeng.entity.Flight;

public interface IFlightDao {

	Flight findFlightByFlyNum(String transportnum);

	List<Flight> findAllFlightBySadressAndEadress(@Param("sadress") String sadress,@Param("eadress")  String eadress);

}
