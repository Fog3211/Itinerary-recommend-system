package com.qianfeng.dao;

import java.util.List;

import com.qianfeng.entity.Station;
import com.qianfeng.entity.Subway;

public interface IStationDao {
	List<Station> findAllStationByName(String staname);
	Station findStationByName(String staname);
	List<Station> findAllStationByCity(String stacity);
	String getCityName(String ssta);
	String[] getLocalName(String sta);
	List<Station> findAllStopByCity(String city);
	List<Station> findAllStopByProvince(String province);
	List<Station> findAllStop();
}
