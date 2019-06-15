package com.qianfeng.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.accessibility.AccessibleRelation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.qianfeng.dao.IRailwayDao;
import com.qianfeng.dao.ISubwayDao;
import com.qianfeng.entity.Railway;
import com.qianfeng.entity.Subway;
import com.qianfeng.service.IRailwayService;
import com.qianfeng.service.ISubwayService;

import com.qianfeng.vo.SubwayMessages;


@Service
public class RailwayService implements IRailwayService{

	@Autowired
	private IRailwayDao railwayDao;

	@Override
	public List<Railway> findAllRailwayBySstaAndEsta(String ssta,String esta){
		System.out.println("findAllRailwayBySstaAndEsta**************");
		List<Railway> railwaymsg=new ArrayList<Railway>();
	
	try{
		List<Railway> railways=railwayDao.findAllRailwayBySstaAndEsta(ssta,esta);
		
		if(railways==null){
			throw new RuntimeException("未查询到地铁信息");
		}
		else {
			railwaymsg=railways;
		}
		
	}catch(Exception e){
		e.printStackTrace();
	}
	return railwaymsg;
	}

}

