package com.qianfeng.dao;

import java.util.List;


import com.qianfeng.entity.Subway;

public interface ISubwayDao {
	List<Subway> findAll();
	List<Subway> findAllSubwayBySta(String sta);
	//List<Subway> findAllSubwayBySubNum(String subnum);
	Subway findSubwayBySubNum(String transportnum);
	

}
