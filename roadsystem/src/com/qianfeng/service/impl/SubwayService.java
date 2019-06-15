package com.qianfeng.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.accessibility.AccessibleRelation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.qianfeng.dao.ISubwayDao;


import com.qianfeng.entity.Subway;

import com.qianfeng.service.ISubwayService;

import com.qianfeng.vo.SubwayMessages;


@Service
public class SubwayService implements ISubwayService{

	@Autowired
	private ISubwayDao subwayDao;

	@Override
	public SubwayMessages findAll(){
		SubwayMessages submsg=new SubwayMessages();
	System.out.println("here**************");
	try{
		List<Subway> subs=subwayDao.findAll();
		
		if(subs==null){
			throw new RuntimeException("未查询到相关信息");
		}
		//System.out.println("here1**************");
		System.out.println(subs.get(8).getStas());
		submsg.setSubways(subs);
	
		
	}catch(Exception e){
		e.printStackTrace();
	}
	return submsg;
	}

	@Override
	public SubwayMessages findAllRoadBySta(String ssta,String esta) {
		// TODO Auto-generated method stub
		SubwayMessages submsg=new SubwayMessages();
		System.out.println("here**************");
		try{
			List<Subway> ssubs=subwayDao.findAllSubwayBySta(ssta);
			List<Subway> esubs=subwayDao.findAllSubwayBySta(esta);
			if(ssubs==null||esubs==null){
				throw new RuntimeException("未查询到相关信息");
			}
			
			//System.out.println("here1**************");
			System.out.println(ssubs.get(0).getStas());
			System.out.println(esubs.get(0).getStas());
			submsg.setSubways(ssubs);
		
			
		}catch(Exception e){
			e.printStackTrace();
		}
		return submsg;
	}

}

