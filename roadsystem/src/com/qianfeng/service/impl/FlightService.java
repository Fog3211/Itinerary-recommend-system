package com.qianfeng.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.accessibility.AccessibleRelation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.qianfeng.dao.IFlightDao;
import com.qianfeng.dao.IRailwayDao;
import com.qianfeng.dao.ISubwayDao;
import com.qianfeng.entity.Flight;
import com.qianfeng.entity.Railway;
import com.qianfeng.entity.Subway;
import com.qianfeng.service.IFlightService;
import com.qianfeng.service.IRailwayService;
import com.qianfeng.service.ISubwayService;

import com.qianfeng.vo.SubwayMessages;


@Service
public class FlightService implements IFlightService{

	@Autowired
	private IFlightDao flightDao;

	@Override
	public List<Flight> findAllFlightBySadressAndEadress(String sadress,String eadress){
		System.out.println("findAllRailwayBySstaAndEsta**************");
		List<Flight> flightmsg=new ArrayList<Flight>();
	
	try{
		List<Flight> flightways=flightDao.findAllFlightBySadressAndEadress(sadress,eadress);
		
		if(flightways==null){
			throw new RuntimeException("未查询到航班信息");
		}
		else {
			flightmsg=flightways;
		}
		
	}catch(Exception e){
		e.printStackTrace();
	}
	return flightmsg;
	}

}

